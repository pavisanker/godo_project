package com.GoDo.godo.user_pack.profile;

import com.GoDo.godo.user_pack.otp.OtpModel;
import com.GoDo.godo.user_pack.otp.OtpRepo;
import com.GoDo.godo.user_pack.otp.OtpService;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingModel;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingRepo;
import com.GoDo.godo.user_pack.travelhistory.TravelHistoryRepo;
import com.GoDo.godo.user_pack.travelroute.TravelRouteRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private OtpService otpService;

    @Autowired
    private TravelBookingRepo travelBookingRepo;

    @Autowired
    private TravelHistoryRepo travelHistoryRepo;

    @Autowired
    private TravelRouteRepo travelRouteRepo;


    public String phoneNumber;
    public String userId;

    private static final String UPLOAD_DIR = "uploads/";




    //Profile creation
    public ResponseEntity<?> createProfile(ProfileModel profileModel,String session){

        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            phoneNumber = optionalOtpModel.get().getPhoneNumber();
            sessionId = optionalOtpModel.get().getSessionId();
        }

        //Manual sessionId
//        String sessionId = "73eb3744-5475-469a-b7aa-4223782823ab";


        if(session.equals(sessionId)){
            profileModel.setPhoneNumber(phoneNumber);
            profileModel.setName(profileModel.getName());
            profileModel.setAddress(profileModel.getAddress());
            profileModel.setAge(profileModel.getAge());
            profileModel.setGender(profileModel.getGender());
            profileModel.setDrivingLicense(profileModel.getDrivingLicense());
            profileModel.setEmail(profileModel.getEmail());
            profileModel.setAadhar(profileModel.getAadhar());
            profileModel.setLastUpdate(LocalDateTime.now());

        }

        profileRepo.save(profileModel);
        return new ResponseEntity<>(profileModel, HttpStatus.OK);
    }

    //viewProfile
    public ResponseEntity<?> viewProfile(String session){

        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            phoneNumber = optionalOtpModel.get().getPhoneNumber();
            sessionId = optionalOtpModel.get().getSessionId();
        }
        if(session.equals(sessionId)){

            Optional<ProfileModel> profileModelOptional = profileRepo.findById(phoneNumber);
            if (profileModelOptional.isPresent()) {
                ProfileModel profileModel = profileModelOptional.get();
                return new ResponseEntity<>(profileModel, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("No Profile",HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> updateProfile(ProfileModel profileModel,String session){

        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            phoneNumber = optionalOtpModel.get().getPhoneNumber();
            sessionId = optionalOtpModel.get().getSessionId();
        }

        //Manual sessionId
//        String sessionId = "2a0ad2da-094a-44cc-a55b-b047f56a870f";


        if(session.equals(sessionId)){
//            profileModel.setPhoneNumber(phoneNumber);
            profileModel.setName(profileModel.getName());
            profileModel.setAddress(profileModel.getAddress());
            profileModel.setAge(profileModel.getAge());
            profileModel.setGender(profileModel.getGender());
            profileModel.setDrivingLicense(profileModel.getDrivingLicense());
//            profileModel.setEmail(profileModel.getEmail());
            profileModel.setAadhar(profileModel.getAadhar());
            profileModel.setLastUpdate(LocalDateTime.now());

        }

        profileRepo.save(profileModel);
        return new ResponseEntity<>(profileModel, HttpStatus.OK);
    }


    //Add Vehicle
    public ResponseEntity<?> addVehicle(String vehicleDetails,String session,MultipartFile rcBook) throws IOException {



        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            phoneNumber = optionalOtpModel.get().getPhoneNumber();
            sessionId = optionalOtpModel.get().getSessionId();
        }

        //Manual sessionId
//        String sessionId = "2a0ad2da-094a-44cc-a55b-b047f56a870f";

        ObjectMapper objectMapper = new ObjectMapper();
        VehicleModel vehicleModel = objectMapper.readValue(vehicleDetails, VehicleModel.class);
        if(session.equals(sessionId)) {
            String fileName = UUID.randomUUID() + "_" + rcBook.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, rcBook.getBytes());



//            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setUserId(optionalOtpModel.get().getUserId());
            vehicleModel.setPhoneNumber(phoneNumber);
            vehicleModel.setRcBook(filePath.toString());

            vehicleModel.setVehicleType(vehicleModel.getVehicleType());
            vehicleModel.setVehicleName(vehicleModel.getVehicleName());
            vehicleModel.setRegistrationNumber(vehicleModel.getRegistrationNumber());
            vehicleModel.setChassisNumber(vehicleModel.getChassisNumber());
            vehicleModel.setCapacity(vehicleModel.getCapacity());
            vehicleModel.setStatus("Pending");
        }


        vehicleRepo.save(vehicleModel);
        return new ResponseEntity<>(vehicleModel, HttpStatus.OK);
    }

    public ResponseEntity<?> viewVehicle(String session){
        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            sessionId = optionalOtpModel.get().getSessionId();
            userId=optionalOtpModel.get().getUserId();
        }

        //Manual sessionId
//        String sessionId = "68ac196c-4f42-42f0-b792-591ecfed81bb";

        if(session.equals(sessionId)){
            List<VehicleModel> vehicles = vehicleRepo.findByUserId(userId);
            if (vehicles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            return ResponseEntity.ok(vehicles);
        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> viewRcBook(String vehicleId){
        Optional<VehicleModel> vehicle = vehicleRepo.findById(vehicleId);

        if (vehicle.isEmpty() || vehicle.get().getRcBook() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RC Book not found");
        }

        try {
            // Read the file path from the database (assuming it's stored as a String path)
            Path filePath = Paths.get(vehicle.get().getRcBook());
            byte[] fileData = Files.readAllBytes(filePath);

            // Detect file type
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream"; // Fallback if unknown
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType)) // Set correct MIME type
                    .body(fileData); // Return file bytes
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving file");
        }
    }

    public ResponseEntity<?> updateVehicle(VehicleModel vehicleModel, String vehicleId) {

        Optional<VehicleModel> vehicleModelOptional = vehicleRepo.findById(vehicleId);
        if(vehicleModelOptional.isPresent()){
            vehicleModel.setVehicleName(vehicleModel.getVehicleName());
            vehicleModel.setRegistrationNumber(vehicleModel.getRegistrationNumber());
        }
        vehicleRepo.save(vehicleModel);
        return new ResponseEntity<>(vehicleModel,HttpStatus.OK);
    }

    public void deleteVehicle(String vehicleId) {
        VehicleModel vehicleModel = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // Delete RC Book file if stored in filesystem
        if (vehicleModel.getRcBook() != null) {
            File file = new File(vehicleModel.getRcBook());
            if (file.exists()) {
                file.delete();  // Delete the RC Book file
            }
        }
        vehicleRepo.deleteById(vehicleId);
    }


    @Transactional
    public void deleteAccount(String session) {
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);

        if (optionalOtpModel.isEmpty()) {
            throw new RuntimeException("Session ID not found or invalid.");
        }

        OtpModel otpModel = optionalOtpModel.get();
        String sessionId = otpModel.getSessionId();
        String userId = otpModel.getUserId();
        String phoneNumber = otpModel.getPhoneNumber();

        if (!session.equals(sessionId)) {
            throw new RuntimeException("Session mismatch. Unauthorized request.");
        }

        try {
            // Delete bookings & routes associated with the user
            travelBookingRepo.deleteAllByUserId(userId);
            travelRouteRepo.deleteAllByUserId(userId);

            // Fetch all vehicles owned by the user
            List<VehicleModel> vehicles = vehicleRepo.findByUserId(userId);

            for (VehicleModel vehicle : vehicles) {
                if (vehicle.getRcBook() != null && !vehicle.getRcBook().isEmpty()) {
                    try {
                        Files.deleteIfExists(Path.of(vehicle.getRcBook())); // Delete RC Book file
                    } catch (IOException e) {
                        System.err.println("Error deleting RC Book file: " + vehicle.getRcBook()); // Log error
                    }
                }
            }

            // Delete all vehicles for the user
            vehicleRepo.deleteAllByUserId(userId);

            // Delete profile and OTP record
            profileRepo.deleteById(phoneNumber);
            otpRepo.deleteById(userId);

        } catch (Exception e) {
            throw new RuntimeException("Error deleting user: " + userId, e);
        }
    }



}
