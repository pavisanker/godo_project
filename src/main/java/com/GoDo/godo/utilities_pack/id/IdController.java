package com.GoDo.godo.utilities_pack.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})

@RestController
@RequestMapping("/api/godo")
public class IdController {

    @Autowired
    private IdService idService;

    @PostMapping(path = "/addGender")
    public ResponseEntity<?> addGender(@RequestBody GenderModel genderModel){

        try{
            return (idService.addGender(genderModel));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/viewGender")
    public  ResponseEntity<?> viewGender(){
        try{
            return (idService.viewGender());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/addVehicleStatus")
    public ResponseEntity<?> addVehicleStatus(@RequestBody VehicleStatusModel statusModel){

        try{
            return (idService.addVehicleStatus(statusModel));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/viewVehicleStatus")
    public  ResponseEntity<?> viewVehicleStatus(){
        try{
            return (idService.viewVehicleStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/addStatus")
    public ResponseEntity<?> addStatus(@RequestBody StatusModel statusModel){

        try{
            return (idService.addStatus(statusModel));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/viewStatus")
    public  ResponseEntity<?> viewStatus(){
        try{
            return (idService.viewStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/addVehicleType")
    public ResponseEntity<?> addVehicleType(@RequestBody VehicleTypeModel vehicleTypeModel){

        try{
            return (idService.addVehicleType(vehicleTypeModel));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/viewVehicleType")
    public  ResponseEntity<?> viewVehicleType(){
        try{
            return (idService.viewVehicleType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
