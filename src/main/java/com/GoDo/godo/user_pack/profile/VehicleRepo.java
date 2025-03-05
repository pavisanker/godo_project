package com.GoDo.godo.user_pack.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepo extends JpaRepository<VehicleModel,String> {
    List<VehicleModel> findByUserId(String userId);

    void deleteAllByUserId(String userId);
}
