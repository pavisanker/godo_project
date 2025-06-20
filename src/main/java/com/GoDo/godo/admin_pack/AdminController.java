package com.GoDo.godo.admin_pack;

import com.GoDo.godo.user_pack.profile.ProfileService;
import com.GoDo.godo.user_pack.profile.VehicleModel;
import com.GoDo.godo.user_pack.profile.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        try {
            return adminService.getUsers(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }

    @GetMapping("/getVehicles")
    public ResponseEntity<?> getVehicles() {
        try {
            return adminService.getVehicles(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }

    @GetMapping("/getDrives")
    public ResponseEntity<?> getDrives() {
        try {
            return adminService.getDrives(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }
    @GetMapping("/getRides")
    public ResponseEntity<?> getRides() {
        try {
            return adminService.getRides(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }

    @GetMapping("/getHistory")
    public ResponseEntity<?> getHistory() {
        try {
            return adminService.getHistory(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }

    @GetMapping("/getVehicleCount")
    public ResponseEntity<?> getVehicleCount() {
        try {
            return adminService.getVehicleCount(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }

    @GetMapping("/getUserCount")
    public ResponseEntity<?> getUserCount() {
        try {
            return adminService.getUserCount(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }
    @GetMapping("/getDriveCount")
    public ResponseEntity<?> getDriveCount() {
        try {
            return adminService.getDriveCount(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }

    @GetMapping("/getBookingCount")
    public ResponseEntity<?> getBookingCount() {
        try {
            return adminService.getBookingCount(); // Corrected method call
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with a logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong!");
        }
    }

}
