package com.GoDo.godo.user_pack.travelhistory;

import com.GoDo.godo.user_pack.deliverybooking.DeliveryBookingModel;
import com.GoDo.godo.user_pack.deliverybooking.DeliveryBookingRepo;
import com.GoDo.godo.user_pack.otp.OtpModel;
import com.GoDo.godo.user_pack.otp.OtpRepo;
import com.GoDo.godo.user_pack.profile.VehicleModel;
import com.GoDo.godo.user_pack.profile.VehicleRepo;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingModel;
import com.GoDo.godo.user_pack.travelbooking.TravelBookingRepo;
import com.GoDo.godo.user_pack.travelroute.TravelRouteModel;
import com.GoDo.godo.user_pack.travelroute.TravelRouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TravelHistoryService {

    @Autowired
    private TravelHistoryRepo travelHistoryRepo;
    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private TravelBookingRepo travelBookingRepo;

    @Autowired
    private DeliveryBookingRepo deliveryBookingRepo;

    @Autowired
    private TravelRouteRepo travelRouteRepo;

    public String phoneNumber;
    public String userId;

    public ResponseEntity<?> travelHistory(String session,String routeId){

        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            phoneNumber = optionalOtpModel.get().getPhoneNumber();
            sessionId = optionalOtpModel.get().getSessionId();
            userId=optionalOtpModel.get().getUserId();
        }


        if(session.equals(sessionId)){
            TravelHistoryModel travelHistoryModel = new TravelHistoryModel();
            Optional<TravelRouteModel> travelRouteModelOptional = travelRouteRepo.findById(routeId);

            List<TravelBookingModel> travelBookingModelList = travelBookingRepo.findByRouteId(routeId);
            List<DeliveryBookingModel> deliveryBookingModelList = deliveryBookingRepo.findByRouteId(routeId);


            if(travelRouteModelOptional.isPresent()){
                travelHistoryModel.setTravelId(routeId);
                travelHistoryModel.setOwnerId(travelRouteModelOptional.get().getUserId());
                travelHistoryModel.setStart(travelRouteModelOptional.get().getStart());
                travelHistoryModel.setDestination(travelRouteModelOptional.get().getDestination());
                travelHistoryModel.setBoardingTime(travelRouteModelOptional.get().getBoardingTime());
                travelHistoryModel.setBooked(travelRouteModelOptional.get().getBooked());
                travelHistoryModel.setVacancy(travelRouteModelOptional.get().getVacancy());
                travelHistoryModel.setOwnerPhoneNumber(travelRouteModelOptional.get().getPhoneNumber());
                travelHistoryModel.setVehicleId(travelRouteModelOptional.get().getVehicleId());
                travelHistoryModel.setStatus("Completed");

                if (!deliveryBookingModelList.isEmpty()) {
                    String deliveryCustomerIds = deliveryBookingModelList.stream()
                            .map(DeliveryBookingModel::getUserId)
                            .collect(Collectors.joining(","));

                    travelHistoryModel.setDeliveryCustomerId(deliveryCustomerIds);
                }


                if(!travelBookingModelList.isEmpty()){
                    String customerIds = travelBookingModelList.stream()
                            .map(TravelBookingModel::getUserId)
                            .collect(Collectors.joining(","));

                    travelHistoryModel.setCustomerId(customerIds);
                }

            }


            travelHistoryRepo.save(travelHistoryModel);
        }

        return new ResponseEntity<>("History added", HttpStatus.OK);

    }

    public ResponseEntity<?> viewDriveHistory(String session){
        //Automatic sessionId
        String sessionId = null;
        Optional<OtpModel> optionalOtpModel = otpRepo.findBySessionId(session);
        if(optionalOtpModel.isPresent()){
            sessionId = optionalOtpModel.get().getSessionId();
            userId=optionalOtpModel.get().getUserId();
        }

        if(session.equals(sessionId)){

//            List<TravelHistoryModel> historyModels = travelHistoryRepo.findByOwnerIdOrCustomerIdContaining(userId, userId);

            List<TravelHistoryModel> historyModels = travelHistoryRepo.findByOwnerId(userId);

            if (historyModels.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            return ResponseEntity.ok(historyModels);
        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> viewRideHistory(String session){
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

            List<TravelHistoryModel> historyModels = travelHistoryRepo.findByCustomerIdContaining(userId);

//            List<TravelHistoryModel> historyModels = travelHistoryRepo.findByOwnerId(userId);

            if (historyModels.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            return ResponseEntity.ok(historyModels);
        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> viewDeliveryHistory(String session){
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

            List<TravelHistoryModel> historyModels = travelHistoryRepo.findByDeliveryCustomerIdContaining(userId);

//            List<TravelHistoryModel> historyModels = travelHistoryRepo.findByOwnerId(userId);

            if (historyModels.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            return ResponseEntity.ok(historyModels);
        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);

    }



}
