package com.GoDo.godo.admin_pack;

import com.GoDo.godo.user_pack.profile.ProfileRepo;
import com.GoDo.godo.user_pack.profile.VehicleModel;
import com.GoDo.godo.user_pack.profile.VehicleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    public ResponseEntity<?> getVehicles() {
        List<VehicleModel> vehicles = vehicleRepo.findAll();
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(vehicles);
    }

}
