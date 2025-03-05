package com.GoDo.godo.user_pack.deliverybooking;

import com.GoDo.godo.user_pack.travelbooking.TravelBookingModel;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})
@RestController
@RequestMapping(path = "/api/godo")
public class DeliveryBookingController {

    @Autowired
    private DeliveryBookingService deliveryBookingService;

    @PostMapping(path = "/deliveryBooking")
    public ResponseEntity<?> deliveryBooking(@RequestBody DeliveryBookingModel deliveryBookingModel, @RequestParam String session){
        try{
            return (deliveryBookingService.deliveryBooking(deliveryBookingModel,session));
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(path = "/deliveryRoutes")
    public ResponseEntity<?>deliveryRoutes(@RequestParam String start,@RequestParam String destination, @RequestParam Integer weight){
        try{
            return deliveryBookingService.deliveryRoutes(start, destination, weight);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/viewDelivery")
    public ResponseEntity<?> viewDelivery(@RequestParam String session) {
        try{
            return (deliveryBookingService.viewDelivery(session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
    @PutMapping("/updateDelivery/{bookingId}")
    public ResponseEntity<?> updateDelivery(@RequestBody DeliveryBookingModel deliveryBookingModel, @PathVariable String bookingId) {
        try{
            return deliveryBookingService.updateDelivery(deliveryBookingModel,bookingId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteDelivery/{bookingId}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable String bookingId) {
        deliveryBookingService.deleteDelivery(bookingId);
        return ResponseEntity.noContent().build();
    }
}
