package com.GoDo.godo.user_pack.travelbooking;

import com.GoDo.godo.user_pack.otp.OtpModel;
import com.GoDo.godo.user_pack.otp.OtpRepo;
import com.GoDo.godo.user_pack.otp.OtpService;
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
public class TravelBookingService {

    @Autowired
    private TravelBookingRepo travelBookingRepo;

    @Autowired
    private TravelRouteRepo travelRouteRepo;

    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private OtpService otpService;

    public String userId;
    public String phoneNumber;




    public ResponseEntity<?> travelBooking(TravelBookingModel travelBookingModel,String session){

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


            Optional<TravelRouteModel> optionalRoute = travelRouteRepo.findById(travelBookingModel.getRouteId());

            if (optionalRoute.isPresent()) {
                TravelRouteModel travelRouteModel = optionalRoute.get(); // Get existing route
                Integer vacancy = travelRouteModel.getVacancy();

                // Check if there are enough seats
                if (vacancy < travelBookingModel.getPassengerCount()) {
                    return new ResponseEntity<>("Not enough available seats!", HttpStatus.BAD_REQUEST);
                }
                else{
                    travelBookingModel.setUserId(userId);
                    travelBookingModel.setPhoneNumber(phoneNumber);
                    String start = travelBookingModel.getStart();
                    String destination = travelBookingModel.getDestination();

                    travelBookingModel.setBookingTime(LocalDateTime.now());
                    travelBookingModel.setBoardingTime(travelBookingModel.getBoardingTime());
                    travelBookingModel.setRouteId(travelBookingModel.getRouteId());
                    travelBookingModel.setStart(start);
                    travelBookingModel.setDestination(destination);
                    travelBookingModel.setPassengerCount(travelBookingModel.getPassengerCount());
                    travelBookingModel.setStatus("Booked");
                }
                if(vacancy.equals(travelBookingModel.getPassengerCount())){
                    travelRouteModel.setStatus("Booked");
                }
                else {
                    travelRouteModel.setStatus("Progress");
                }

                // Update the existing route's vacancy,booked
                travelRouteModel.setVacancy(vacancy - travelBookingModel.getPassengerCount());
                travelRouteModel.setBooked(travelRouteModel.getBooked()+travelBookingModel.getPassengerCount());

                // Save the updated route
                travelRouteRepo.save(travelRouteModel);
            }


        }

        travelBookingRepo.save(travelBookingModel);
        return new ResponseEntity<>(travelBookingModel, HttpStatus.OK);
    }


    public ResponseEntity<?> viewRoutes( String start, String destination, Integer passengerCount) {
        List<TravelRouteModel> travelRoutes = travelRouteRepo.findByStartAndDestination(start, destination);

        List<TravelRouteModel> availableRoutes = travelRoutes.stream()
                .filter(route -> route.getVacancy() >= passengerCount)
                .collect(Collectors.toList());

        if (!availableRoutes.isEmpty()) {
            return new ResponseEntity<>(availableRoutes, HttpStatus.OK); // Return filtered list
        } else {
            return new ResponseEntity<>("No routes available with enough seats!", HttpStatus.NOT_FOUND);
        }
    }


//    public ResponseEntity<?> viewBookings(String userId) {
//        Optional<TravelBookingModel> travelBookingModels = travelBookingRepo.findByUserId(userId);
//
//        if (!travelBookingModels.isEmpty()) {
//            return new ResponseEntity<>(travelBookingModels, HttpStatus.OK); // Return all bookings
//        } else {
//            return new ResponseEntity<>("No routes found!", HttpStatus.NOT_FOUND);
//        }
//    }

    public ResponseEntity<?> viewRides(String session){
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
            List<TravelBookingModel> rides = travelBookingRepo.findByUserId(userId);
            if (rides.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            return ResponseEntity.ok(rides);
        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> updateRide(TravelBookingModel travelBookingModel, String bookingId) {

        Optional<TravelBookingModel> travelBookingModelOptional = travelBookingRepo.findById(bookingId);
        if(travelBookingModelOptional.isPresent()){
            travelBookingModel.setStart(travelBookingModel.getStart());
            travelBookingModel.setDestination(travelBookingModel.getDestination());
            travelBookingModel.setPassengerCount(travelBookingModel.getPassengerCount());
        }
        travelBookingRepo.save(travelBookingModel);
        return new ResponseEntity<>(travelBookingModel,HttpStatus.OK);
    }

    public void deleteRide(String bookingId) {
        Optional<TravelBookingModel> optionalBooking = travelBookingRepo.findById(bookingId);

        if (optionalBooking.isPresent()) {
            TravelBookingModel travelBookingModel = optionalBooking.get(); // Get the existing booking

            Optional<TravelRouteModel> optionalRoute = travelRouteRepo.findById(travelBookingModel.getRouteId());

            if (optionalRoute.isPresent()) {
                TravelRouteModel travelRouteModel = optionalRoute.get(); // Get the existing route

                Integer vacancy = travelRouteModel.getVacancy();
                travelRouteModel.setVacancy(vacancy + travelBookingModel.getPassengerCount());
                travelRouteModel.setBooked(travelRouteModel.getBooked()-travelBookingModel.getPassengerCount());

                if(travelRouteModel.getBooked().equals(0)){
                    travelRouteModel.setStatus("Pending");
                }
                else{
                    travelRouteModel.setStatus("Progress");
                }

                // Save the updated route
                travelRouteRepo.save(travelRouteModel);
            }

            // Delete the booking after updating the route
            travelBookingRepo.deleteById(bookingId);
        } else {
            throw new EntityNotFoundException("Booking with ID " + bookingId + " not found.");
        }
    }

}
