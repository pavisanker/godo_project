package com.GoDo.godo.user_pack.travelroute;

import com.GoDo.godo.user_pack.deliverybooking.DeliveryBookingModel;
import com.GoDo.godo.user_pack.deliverybooking.DeliveryBookingRepo;
import com.GoDo.godo.user_pack.otp.OtpModel;
import com.GoDo.godo.user_pack.otp.OtpRepo;
import com.GoDo.godo.user_pack.otp.OtpService;
import com.GoDo.godo.user_pack.profile.ProfileRepo;
import com.GoDo.godo.user_pack.profile.VehicleModel;
import com.GoDo.godo.user_pack.profile.VehicleRepo;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingModel;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingRepo;
import com.GoDo.godo.utilities_pack.FareCalculator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TravelRouteService {
    @Autowired
    private TravelRouteRepo travelRouteRepo;

    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private TravelBookingRepo travelBookingRepo;

    @Autowired
    private DeliveryBookingRepo deliveryBookingRepo;

    @Autowired
    private OtpService otpService;

    public String phoneNumber;
    public String userId;



    public ResponseEntity<?> travelRoute(TravelRouteModel travelRouteModel,String session,String vehicleId){

        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            phoneNumber = optionalOtpModel.get().getPhoneNumber();
            sessionId = optionalOtpModel.get().getSessionId();
            userId=optionalOtpModel.get().getUserId();
        }

        //Manual sessionId
//        String sessionId = "68ac196c-4f42-42f0-b792-591ecfed81bb";

        if(session.equals(sessionId)){
//
            travelRouteModel.setUserId(userId);
            travelRouteModel.setVehicleId(vehicleId);
            travelRouteModel.setPhoneNumber(phoneNumber);
            travelRouteModel.setVacancy(travelRouteModel.getVacancy());
            travelRouteModel.setBooked(0);
            travelRouteModel.setDelivery(0);
            travelRouteModel.setTotalWeight(0);
            travelRouteModel.setStatus(1);
            travelRouteModel.setStart(travelRouteModel.getStart());
            travelRouteModel.setDestination(travelRouteModel.getDestination());
            travelRouteModel.setBoardingTime(travelRouteModel.getBoardingTime());
//            Long distance = travelRouteModel.getDistance()*1000;
            travelRouteModel.setDistance(travelRouteModel.getDistance());




            Optional<VehicleModel> vehicleModelOptional = vehicleRepo.findById(vehicleId);
            vehicleModelOptional.ifPresent(vehicleModel -> travelRouteModel.setCapacity(vehicleModel.getCapacity()));

            long distance = travelRouteModel.getDistance();
            if(vehicleModelOptional.isPresent()){
                int capacity = vehicleModelOptional.get().getCapacity();
                long calculatedAmount = FareCalculator.calculateAmount(distance, capacity);

                travelRouteModel.setAmount(calculatedAmount);
            }
            else{
                int capacity = 10;
                long calculatedAmount = FareCalculator.calculateAmount(distance, capacity);

                travelRouteModel.setAmount(calculatedAmount);

            }


        }

        travelRouteRepo.save(travelRouteModel);
        return new ResponseEntity<>(travelRouteModel, HttpStatus.OK);
    }

    public ResponseEntity<?> viewDrives(String session){
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
            List<TravelRouteModel> drives = travelRouteRepo.findByUserId(userId);
            if (drives.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            return ResponseEntity.ok(drives);
        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> updateDrive(TravelRouteModel travelRouteModel, String routeId) {

        Optional<TravelRouteModel> travelRouteModelOptional = travelRouteRepo.findById(routeId);
        if(travelRouteModelOptional.isPresent()){
            travelRouteModel.setStart(travelRouteModel.getStart());
            travelRouteModel.setDestination(travelRouteModel.getDestination());
            travelRouteModel.setBoardingTime(travelRouteModel.getBoardingTime());
            travelRouteModel.setVacancy(travelRouteModel.getVacancy());
        }
        travelRouteRepo.save(travelRouteModel);
        return new ResponseEntity<>(travelRouteModel,HttpStatus.OK);
    }

    @Transactional
    public void deleteDrive(String routeId) {
        try {
            deliveryBookingRepo.deleteByRouteId(routeId);
            travelBookingRepo.deleteByRouteId(routeId); // Delete related bookings first
            travelRouteRepo.deleteById(routeId); // Then delete the drive itself
        } catch (Exception e) {
            throw new RuntimeException("Error deleting drive with ID: " + routeId, e);
        }
    }

    public Map<String, Object> getBookingDetails(String routeId) {
        List<TravelBookingModel> passengers = travelBookingRepo.findByRouteId(routeId);
        List<DeliveryBookingModel> deliveries = deliveryBookingRepo.findByRouteId(routeId);

        // Convert passengers to response format with user lookup (name + phone)
        List<Map<String, String>> passengerList = passengers.stream()
                .map(p -> {
                    return profileRepo.findById(p.getPhoneNumber())
                            .map(user -> Map.of(
                                    "name", user.getName(),
                                    "phone", user.getPhoneNumber(),
                                    "passengerCount",p.getPassengerCount().toString()
                            ))
                            .orElse(Map.of(
                                    "name", "Unknown",
                                    "phone", "N/A",
                                    "passengerCount","-"
                            ));
                })
                .collect(Collectors.toList());

        // Convert deliveries to response format with user lookup (receiver name + phone)
        List<Map<String, String>> deliveryList = deliveries.stream()
                .map(d -> {
                    return profileRepo.findById(d.getPhoneNumber())
                            .map(user -> Map.of(
                                    "receiverName", user.getName(),
                                    "phone", user.getPhoneNumber(),
                                    "weight",d.getWeight().toString()
                            ))
                            .orElse(Map.of(
                                    "receiverName", "Unknown",
                                    "phone", "N/A",
                                    "weight","-"
                            ));
                })
                .collect(Collectors.toList());

        // Response map
        Map<String, Object> response = new HashMap<>();
        response.put("passengers", passengerList);
        response.put("deliveries", deliveryList);

        return response;
    }


    public ResponseEntity<?> updateStatus(String routeId) {
        Optional<TravelRouteModel> optionalTravelRouteModel = travelRouteRepo.findById(routeId);
        List<TravelBookingModel> travelBookingModels = travelBookingRepo.findByRouteId(routeId);

        if (optionalTravelRouteModel.isPresent()) {
            TravelRouteModel route = optionalTravelRouteModel.get();
            route.setStatus(3); // Assuming status is an integer field
            route.setVacancy(0);

                for (TravelBookingModel bookingModel : travelBookingModels) {
                    bookingModel.setStatus(3);
                }
                travelBookingRepo.saveAll(travelBookingModels);
            
            travelRouteRepo.save(route);
            return ResponseEntity.ok("Status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found.");
        }
    }
}



