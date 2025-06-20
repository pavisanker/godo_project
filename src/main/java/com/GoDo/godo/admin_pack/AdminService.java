package com.GoDo.godo.admin_pack;

import com.GoDo.godo.user_pack.otp.OtpModel;
import com.GoDo.godo.user_pack.otp.OtpRepo;
import com.GoDo.godo.user_pack.profile.*;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingModel;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingRepo;
import com.GoDo.godo.user_pack.travelhistory.TravelHistoryModel;
import com.GoDo.godo.user_pack.travelhistory.TravelHistoryRepo;
import com.GoDo.godo.user_pack.travelroute.TravelRouteModel;
import com.GoDo.godo.user_pack.travelroute.TravelRouteRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private TravelHistoryRepo travelHistoryRepo;

    @Autowired
    private TravelRouteRepo travelRouteRepo;

    @Autowired
    private TravelBookingRepo travelBookingRepo;


    public ResponseEntity<?> getVehicles() {
        List<VehicleModel> vehicles = vehicleRepo.findAll();
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(vehicles);
    }

    public ResponseEntity<?> getVehicleCount() {
        long count = vehicleRepo.count(); // Directly get the count of rows
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getUserCount() {
        long count = profileRepo.count(); // Directly get the count of rows
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getHistory() {
        List<TravelHistoryModel> history = travelHistoryRepo.findAll();
        if (history.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(history);
    }

    public ResponseEntity<?> getUsers() {
        List<ProfileModel> profiles = profileRepo.findAll();
        List<OtpModel> otps = otpRepo.findAll();

        if (profiles.isEmpty() || otps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        // Create a map of phoneNumber -> userId from OtpModel
        Map<String, String> phoneToUserId = otps.stream()
                .collect(Collectors.toMap(OtpModel::getPhoneNumber, OtpModel::getUserId)); // Adjust method names if needed

        List<ProfileDTO> dtoList = profiles.stream()
                .map(profile -> {
                    ProfileDTO dto = new ProfileDTO();
                    dto.setPhoneNumber(profile.getPhoneNumber());
                    dto.setName(profile.getName());
                    dto.setAddress(profile.getAddress());
                    dto.setAge(profile.getAge());
                    dto.setGender(profile.getGender());
                    dto.setEmail(profile.getEmail());
                    dto.setDrivingLicense(profile.getDrivingLicense());
                    dto.setAadhar(profile.getAadhar());
                    dto.setLastUpdate(profile.getLastUpdate());

                    // Set userId from OtpModel based on phone number
                    String userId = phoneToUserId.get(profile.getPhoneNumber());
                    dto.setUserId(userId != null ? userId : ""); // Set empty or handle null as needed

                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    public ResponseEntity<?> getDrives() {
        List<TravelRouteModel> drives = travelRouteRepo.findAll();
        if (drives.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(drives);
    }
    public ResponseEntity<?> getRides() {
        List<TravelBookingModel> rides = travelBookingRepo.findAll();
        if (rides.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(rides);
    }

    public ResponseEntity<?> getDriveCount() {
        long count = travelRouteRepo.count(); // Directly get the count of rows
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getBookingCount() {
        long count = travelBookingRepo.count(); // Directly get the count of rows
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);

        return ResponseEntity.ok(response);
    }
}
