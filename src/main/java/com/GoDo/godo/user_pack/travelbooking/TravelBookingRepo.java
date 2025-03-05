package com.GoDo.godo.user_pack.travelbooking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelBookingRepo extends JpaRepository<TravelBookingModel,String> {
    List<TravelBookingModel> findByUserId(String userId);

    void deleteByRouteId(String routeId);

    List<TravelBookingModel> findByRouteId(String routeId);

    void deleteAllByUserId(String userId);

//    Optional<TravelBookingModel> findByUserId(String userId);
}
