package com.GoDo.godo.user_pack.profile;

import com.GoDo.godo.user_pack.otp.OtpModel;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})

@RestController
@RequestMapping(path = "/api/godo")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private VehicleRepo vehicleRepo;

    @PostMapping(path = "/profile")
    public ResponseEntity<?> createProfile(@RequestBody ProfileModel profileModel, @RequestParam String session){

        try{
            return (profileService.createProfile(profileModel,session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/viewProfile")
    public  ResponseEntity<?> viewProfile(@RequestParam String session){
        try{
            return (profileService.viewProfile(session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(path = "/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileModel profileModel, @RequestParam String session){
        try{
            return (profileService.updateProfile(profileModel,session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/addVehicle")
    public ResponseEntity<?> createProfile(@RequestPart String vehicleDetails ,@RequestParam String session, @RequestPart MultipartFile rcBook){

        try{
            return (profileService.addVehicle(vehicleDetails,session, rcBook));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> viewVehicle(@RequestParam String session) {
        try{
            return (profileService.viewVehicle(session));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/rcBook/{vehicleId}")
    public ResponseEntity<?> getRcBook(@PathVariable String vehicleId) {
        try{
            return (profileService.viewRcBook(vehicleId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/updateVehicle/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleModel vehicleModel, @PathVariable String vehicleId) {
        try{
            return profileService.updateVehicle(vehicleModel,vehicleId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteVehicle/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String vehicleId) {
        profileService.deleteVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAccount/{session}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String session) {
        profileService.deleteAccount(session);
        return ResponseEntity.noContent().build();
    }


}
