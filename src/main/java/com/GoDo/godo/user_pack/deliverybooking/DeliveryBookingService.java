package com.GoDo.godo.user_pack.deliverybooking;

import com.GoDo.godo.user_pack.otp.OtpModel;
import com.GoDo.godo.user_pack.otp.OtpRepo;
import com.GoDo.godo.user_pack.otp.OtpService;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingModel;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingRepo;
import com.GoDo.godo.user_pack.travelroute.TravelRouteModel;
import com.GoDo.godo.user_pack.travelroute.TravelRouteRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryBookingService {

    @Autowired
    private DeliveryBookingRepo deliveryBookingRepo;

    @Autowired
    private TravelRouteRepo travelRouteRepo;

    @Autowired
    private OtpRepo otpRepo;


    public String userId;
    public String phoneNumber;


    public ResponseEntity<?> deliveryBooking(DeliveryBookingModel deliveryBookingModel, String session){

        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            phoneNumber = optionalOtpModel.get().getPhoneNumber();
            sessionId = optionalOtpModel.get().getSessionId();
            userId = optionalOtpModel.get().getUserId();
        }

        //Manual sessionId
//        String sessionId = "df1d3c82-b580-4a5a-8f95-184e32ac8a99";

        if(session.equals(sessionId)){

            Optional<TravelRouteModel> optionalRoute = travelRouteRepo.findById(deliveryBookingModel.getRouteId());

            if (optionalRoute.isPresent()) {
                TravelRouteModel travelRouteModel = optionalRoute.get(); // Get existing route
                Integer capacity = travelRouteModel.getCapacity();
                Integer currentWeight = travelRouteModel.getTotalWeight(); // Current booked weight
                Integer newWeight = deliveryBookingModel.getWeight();

                Integer maxWeight;
                if (capacity <3) {
                    maxWeight = 10;
                } else if (capacity < 6) {
                    maxWeight = 15;
                } else {
                    maxWeight = 20; // Default max weight for other capacities
                }

                if (currentWeight + newWeight > maxWeight) {
                    return new ResponseEntity<>("Exceeds vehicle weight capacity!", HttpStatus.BAD_REQUEST);
                }
                else{
                    deliveryBookingModel.setUserId(userId);
                    deliveryBookingModel.setPhoneNumber(phoneNumber);
                    String start = deliveryBookingModel.getStart();
                    String destination = deliveryBookingModel.getDestination();

                    deliveryBookingModel.setBookingTime(LocalDateTime.now());
                    deliveryBookingModel.setBoardingTime(deliveryBookingModel.getBoardingTime());
                    deliveryBookingModel.setRouteId(deliveryBookingModel.getRouteId());
                    deliveryBookingModel.setStart(start);
                    deliveryBookingModel.setDestination(destination);
                    deliveryBookingModel.setWeight(deliveryBookingModel.getWeight());
                    deliveryBookingModel.setStatus(7);
                    if (travelRouteModel.getVacancy().equals(travelRouteModel.getCapacity()-1)) {
                        travelRouteModel.setStatus(2);
                    }
                    travelRouteModel.setDelivery(travelRouteModel.getDelivery()+1);
                    travelRouteModel.setTotalWeight(newWeight+currentWeight);

                    // Save the updated route
                    travelRouteRepo.save(travelRouteModel);

                }
            }
        }

        deliveryBookingRepo.save(deliveryBookingModel);
        return new ResponseEntity<>(deliveryBookingModel, HttpStatus.OK);
    }


    public ResponseEntity<?> deliveryRoutes( String start, String destination, Integer weight) {
        List<TravelRouteModel> travelRoutes = travelRouteRepo.findByStartAndDestination(start, destination);
        

        List<TravelRouteModel> availableRoutes = travelRoutes.stream()
                .filter(route -> {
                    Integer maxWeight = getMaxWeightByCapacity(route.getCapacity()); // Get max weight by capacity
                    return (route.getTotalWeight() + weight) <= maxWeight; // Ensure total weight stays within limit
                })
                .collect(Collectors.toList());

        if (!availableRoutes.isEmpty()) {
            return new ResponseEntity<>(availableRoutes, HttpStatus.OK); // Return filtered list
        } else {
            return new ResponseEntity<>("No routes available with enough seats!", HttpStatus.NOT_FOUND);
        }
    }

    private Integer getMaxWeightByCapacity(Integer capacity) {
        if (capacity == 2) return 10;
        if (capacity == 3) return 15;
        return 20; // Default max weight for other capacities
    }

    public ResponseEntity<?> viewDelivery(String session){
        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            sessionId = optionalOtpModel.get().getSessionId();
            userId=optionalOtpModel.get().getUserId();
        }


        if(session.equals(sessionId)){
            List<DeliveryBookingModel> deliveries = deliveryBookingRepo.findByUserId(userId);
            if (deliveries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            return ResponseEntity.ok(deliveries);
        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

    }
//
    public ResponseEntity<?> updateDelivery(DeliveryBookingModel deliveryBookingModel, String bookingId) {

        Optional<DeliveryBookingModel> deliveryBookingModelOptional = deliveryBookingRepo.findById(bookingId);
        if(deliveryBookingModelOptional.isPresent()){
            deliveryBookingModel.setStart(deliveryBookingModel.getStart());
            deliveryBookingModel.setDestination(deliveryBookingModel.getDestination());
            deliveryBookingModel.setWeight(deliveryBookingModel.getWeight());
        }
        deliveryBookingRepo.save(deliveryBookingModel);
        return new ResponseEntity<>(deliveryBookingModel,HttpStatus.OK);
    }

    public void deleteDelivery(String bookingId) {
        Optional<DeliveryBookingModel> optionalDeliveryBookingModel = deliveryBookingRepo.findById(bookingId);

        if (optionalDeliveryBookingModel.isPresent()) {
            DeliveryBookingModel deliveryBookingModel = optionalDeliveryBookingModel.get(); // Get the existing booking

            Optional<TravelRouteModel> optionalRoute = travelRouteRepo.findById(deliveryBookingModel.getRouteId());

            if (optionalRoute.isPresent()) {
                TravelRouteModel travelRouteModel = optionalRoute.get(); // Get the existing route

                Integer weight = deliveryBookingModel.getWeight();
                travelRouteModel.setTotalWeight(weight + travelRouteModel.getTotalWeight());
                travelRouteModel.setDelivery(travelRouteModel.getDelivery()-1);

                if(travelRouteModel.getDelivery().equals(0)){
                    travelRouteModel.setStatus(1);
                }
                else{
                    travelRouteModel.setStatus(2);
                }

                // Save the updated route
                travelRouteRepo.save(travelRouteModel);
            }

            // Delete the booking after updating the route
            deliveryBookingRepo.deleteById(bookingId);
        } else {
            throw new EntityNotFoundException("Booking with ID " + bookingId + " not found.");
        }
    }
}
