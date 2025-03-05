package com.GoDo.godo.user_pack.travelroute;

import com.GoDo.godo.user_pack.deliverybooking.DeliveryBookingModel;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})

@RestController
@RequestMapping(path = "/api/godo")
public class TravelRouteController {


    @Autowired
    private TravelRouteService travelRouteService;

    @PostMapping(path = "/route")
    public ResponseEntity<?> travelRoute(@RequestBody TravelRouteModel travelRouteModel, @RequestParam String session, @RequestParam String vehicleId) {
        try {
            return (travelRouteService.travelRoute(travelRouteModel,session, vehicleId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/viewDrives")
    public ResponseEntity<?> viewDrives(@RequestParam String session) {
        try{
            return (travelRouteService.viewDrives(session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/updateDrive/{routeId}")
    public ResponseEntity<?> updateDrive(@RequestBody TravelRouteModel travelRouteModel, @PathVariable String routeId) {
        try{
            return travelRouteService.updateDrive(travelRouteModel,routeId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteDrive/{routeId}")
    public ResponseEntity<Void> deleteDrive(@PathVariable String routeId) {
        travelRouteService.deleteDrive(routeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bookings/{routeId}")
    public ResponseEntity<Map<String, Object>> getBookingDetails(@PathVariable String routeId) {
        Map<String, Object> bookingDetails = travelRouteService.getBookingDetails(routeId);
        return ResponseEntity.ok(bookingDetails);
    }



}