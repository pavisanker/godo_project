package com.GoDo.godo.user_pack.travelbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})
@RestController
@RequestMapping(path = "/api/godo")
public class TravelBookingController {

    @Autowired
    private TravelBookingService travelBookingService;

    @PostMapping(path = "/travelBooking")
    public ResponseEntity<?> travelBooking(@RequestBody TravelBookingModel travelBookingModel,@RequestParam String session){
        try{
            return (travelBookingService.travelBooking(travelBookingModel,session));
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(path = "/viewRoutes")
    public ResponseEntity<?>viewRoutes(@RequestParam String start,@RequestParam String destination, @RequestParam Integer passengerCount){
        try{
            return travelBookingService.viewRoutes(start, destination, passengerCount);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

//    @GetMapping(path = "/view")
//    public ResponseEntity<?>viewBookings(@RequestParam String userId){
//        try{
//            return travelBookingService.viewBookings(userId);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    @GetMapping("/viewRides")
    public ResponseEntity<?> viewRides(@RequestParam String session) {
        try{
            return (travelBookingService.viewRides(session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/updateRide/{bookingId}")
    public ResponseEntity<?> updateRide(@RequestBody TravelBookingModel travelBookingModel, @PathVariable String bookingId) {
        try{
            return travelBookingService.updateRide(travelBookingModel,bookingId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteRide/{bookingId}")
    public ResponseEntity<Void> deleteRide(@PathVariable String bookingId) {
        travelBookingService.deleteRide(bookingId);
        return ResponseEntity.noContent().build();
    }
}
