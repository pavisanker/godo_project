package com.GoDo.godo.utilities_pack.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdService {

    @Autowired
    private GenderRepo genderRepo;

    @Autowired
    private VehicleStatusRepo vehicleStatusRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private VehicleTypeRepo vehicleTypeRepo;

    public ResponseEntity<?> addGender(GenderModel genderModel){

        try{
            genderModel.setGenderId(genderModel.getGenderId());
            genderModel.setGenderName(genderModel.getGenderName());
        }catch (Exception e){
            e.printStackTrace();
        }

        genderRepo.save(genderModel);
        return new ResponseEntity<>(genderModel, HttpStatus.OK);
    }

    public ResponseEntity<?> viewGender(){

            List<GenderModel> genderModelList = genderRepo.findAll();
            if (!genderModelList.isEmpty()) {
                return new ResponseEntity<>(genderModelList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity<?> addVehicleStatus(VehicleStatusModel vehicleStatusModel){

        try{
            vehicleStatusModel.setStatusId(vehicleStatusModel.getStatusId());
            vehicleStatusModel.setStatusName(vehicleStatusModel.getStatusName());
        }catch (Exception e){
            e.printStackTrace();
        }

        vehicleStatusRepo.save(vehicleStatusModel);
        return new ResponseEntity<>(vehicleStatusModel, HttpStatus.OK);
    }

    public ResponseEntity<?> viewVehicleStatus(){

            List<VehicleStatusModel> vehicleStatusModelList = vehicleStatusRepo.findAll();
            if (!vehicleStatusModelList.isEmpty()) {
                return new ResponseEntity<>(vehicleStatusModelList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
    }
    public ResponseEntity<?> addStatus(StatusModel statusModel){

        try{
            statusModel.setStatusId(statusModel.getStatusId());
            statusModel.setStatusName(statusModel.getStatusName());
        }catch (Exception e){
            e.printStackTrace();
        }

        statusRepo.save(statusModel);
        return new ResponseEntity<>(statusModel, HttpStatus.OK);
    }

    public ResponseEntity<?> viewStatus(){

            List<StatusModel> statusModelList = statusRepo.findAll();
            if (!statusModelList.isEmpty()) {
                return new ResponseEntity<>(statusModelList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
    }

    public ResponseEntity<?> addVehicleType(VehicleTypeModel vehicleTypeModel){

        try{
            vehicleTypeModel.setVehicleTypeId(vehicleTypeModel.getVehicleTypeId());
            vehicleTypeModel.setVehicleTypeName(vehicleTypeModel.getVehicleTypeName());
        }catch (Exception e){
            e.printStackTrace();
        }

        vehicleTypeRepo.save(vehicleTypeModel);
        return new ResponseEntity<>(vehicleTypeModel, HttpStatus.OK);
    }

    public ResponseEntity<?> viewVehicleType(){

            List<VehicleTypeModel> vehicleTypeModelList = vehicleTypeRepo.findAll();
            if (!vehicleTypeModelList.isEmpty()) {
                return new ResponseEntity<>(vehicleTypeModelList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
    }


}

