package com.GoDo.godo.utilities_pack.mapRoute;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})

@RestController
@RequestMapping("/api/godo")
public class GeocodingController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Map<String, String> memoryCache = new ConcurrentHashMap<>();

    @Autowired
    private WaypointCacheRepo cacheRepository;

    @GetMapping("/geocode")
    public ResponseEntity<String> geocode(@RequestParam String place) {
        try {
            String url = "https://nominatim.openstreetmap.org/search?format=json&q=" +
                    URLEncoder.encode(place, StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "GoDoMapClient/1.0");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class
            );

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Failed to fetch coordinates.\"}");
        }
    }

//    @GetMapping("/route-places")
//    public ResponseEntity<Object> getRoutePlaces(
//            @RequestParam double startLat,
//            @RequestParam double startLng,
//            @RequestParam double endLat,
//            @RequestParam double endLng) {
//
//        try {
//            // Step 1: Get Route from OSRM
//            String routeUrl = "https://router.project-osrm.org/route/v1/driving/" +
//                    startLng + "," + startLat + ";" + endLng + "," + endLat +
//                    "?overview=full&geometries=geojson";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("User-Agent", "GoDoMapClient/1.0");
//
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//            RestTemplate restTemplate = new RestTemplate();
//
//            ResponseEntity<String> routeResponse = restTemplate.exchange(
//                    routeUrl, HttpMethod.GET, entity, String.class);
//
//            JSONObject routeJson = new JSONObject(routeResponse.getBody());
//            JSONArray coordinates = routeJson.getJSONArray("routes")
//                    .getJSONObject(0)
//                    .getJSONObject("geometry")
//                    .getJSONArray("coordinates");
//
//            // Step 2: Loop through coordinates and reverse geocode every 10th point
//            Set<String> placeNames = new LinkedHashSet<>(); // maintains order, avoids duplicates
//
//            for (int i = 0; i < coordinates.length(); i += 10) {
//                JSONArray coord = coordinates.getJSONArray(i);
//                double lon = coord.getDouble(0);
//                double lat = coord.getDouble(1);
//
//                // Reverse geocode
//                String reverseUrl = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" +
//                        lat + "&lon=" + lon;
//
//                HttpEntity<String> reverseEntity = new HttpEntity<>(headers);
//                ResponseEntity<String> reverseResponse = restTemplate.exchange(
//                        reverseUrl, HttpMethod.GET, reverseEntity, String.class);
//
//                JSONObject reverseJson = new JSONObject(reverseResponse.getBody());
////                String displayName = reverseJson.optString("display_name");
////
////                if (displayName != null && !displayName.isEmpty()) {
////                    placeNames.add(displayName);
////                }
//                JSONObject address = reverseJson.optJSONObject("address");
//                String name = "";
//
//                if (address != null) {
//                    if (address.has("village")) name = address.getString("village");
//                    else if (address.has("suburb")) name = address.getString("suburb");
//                    else if (address.has("town")) name = address.getString("town");
//                    else if (address.has("neighbourhood")) name = address.getString("neighbourhood");
//                    else if (address.has("city")) name = address.getString("city");
//                    else if (address.has("county")) name = address.getString("county");
//                    else name = reverseJson.optString("display_name"); // fallback
//                }
//
//                if (!name.isEmpty()) {
//                    placeNames.add(name);
//                }
//
//            }
//
//            return ResponseEntity.ok(placeNames);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("error", "Failed to get route places."));
//        }
//    }

    @GetMapping("/route-places")
    public ResponseEntity<Object> getRoutePlaces(
            @RequestParam double startLat,
            @RequestParam double startLng,
            @RequestParam double endLat,
            @RequestParam double endLng) {

        try {
            String routeUrl = "https://router.project-osrm.org/route/v1/driving/" +
                    startLng + "," + startLat + ";" + endLng + "," + endLat +
                    "?overview=full&geometries=geojson";

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "GoDoMapClient/1.0");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> routeResponse = restTemplate.exchange(routeUrl, HttpMethod.GET, entity, String.class);
            JSONObject routeJson = new JSONObject(routeResponse.getBody());
            JSONArray coordinates = routeJson.getJSONArray("routes")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONArray("coordinates");

            Set<String> placeNames = new LinkedHashSet<>();

            for (int i = 0; i < coordinates.length(); i += 10) {
                JSONArray coord = coordinates.getJSONArray(i);
                double lon = coord.getDouble(0);
                double lat = coord.getDouble(1);

                String roundedKey = String.format("%.4f,%.4f", lat, lon);

                // Step 1: Check memory cache
                if (memoryCache.containsKey(roundedKey)) {
                    placeNames.add(memoryCache.get(roundedKey));
                    continue;
                }

                // Step 2: Check DB cache
                Optional<WaypointCache> existing = cacheRepository.findByLatAndLon(lat, lon);
                if (existing.isPresent()) {
                    String name = existing.get().getPlaceName();
                    memoryCache.put(roundedKey, name);
                    placeNames.add(name);
                    continue;
                }

                // Step 3: Call Nominatim
                String reverseUrl = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" +
                        lat + "&lon=" + lon;

                ResponseEntity<String> reverseResponse = restTemplate.exchange(reverseUrl, HttpMethod.GET, entity, String.class);
                JSONObject reverseJson = new JSONObject(reverseResponse.getBody());
                JSONObject address = reverseJson.optJSONObject("address");
                String name = "";

                if (address != null) {
                    if (address.has("village")) name = address.getString("village");
                    else if (address.has("suburb")) name = address.getString("suburb");
                    else if (address.has("town")) name = address.getString("town");
                    else if (address.has("neighbourhood")) name = address.getString("neighbourhood");
                    else if (address.has("city")) name = address.getString("city");
                    else if (address.has("county")) name = address.getString("county");
                    else name = reverseJson.optString("display_name");
                }

                if (!name.isEmpty()) {
                    placeNames.add(name);
                    memoryCache.put(roundedKey, name);
                    cacheRepository.save(new WaypointCache(lat, lon, name));
                }

                Thread.sleep(1000); // Respect rate limit
            }

            return ResponseEntity.ok(placeNames);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to get route places."));
        }
    }
}