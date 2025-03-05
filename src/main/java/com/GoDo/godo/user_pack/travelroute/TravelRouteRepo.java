package com.GoDo.godo.user_pack.travelroute;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRouteRepo extends JpaRepository<TravelRouteModel,String> {

    List<TravelRouteModel> findByStartAndDestination(String start, String destination);


    List<TravelRouteModel> findByUserId(String userId);

    void deleteAllByUserId(String userId);
}
