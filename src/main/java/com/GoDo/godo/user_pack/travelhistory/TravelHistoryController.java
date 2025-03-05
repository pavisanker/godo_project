package com.GoDo.godo.user_pack.travelhistory;

import com.GoDo.godo.user_pack.travelroute.TravelRouteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})

@RestController
@RequestMapping(path = "api/godo")
public class TravelHistoryController {
    @Autowired
    private TravelHistoryService travelHistoryService;

    @PostMapping(path = "/history")
    public ResponseEntity<?> travelHistory(@RequestParam String session, @RequestParam String routeId) {
        try {
            return (travelHistoryService.travelHistory(session, routeId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/viewDriveHistory")
    public ResponseEntity<?> viewDriveHistory(@RequestParam String session) {
        try{
            return (travelHistoryService.viewDriveHistory(session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/viewRideHistory")
    public ResponseEntity<?> viewRideHistory(@RequestParam String session) {
        try{
            return (travelHistoryService.viewRideHistory(session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/viewDeliveryHistory")
    public ResponseEntity<?> viewDeliveryHistory(@RequestParam String session) {
        try{
            return (travelHistoryService.viewDeliveryHistory(session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
