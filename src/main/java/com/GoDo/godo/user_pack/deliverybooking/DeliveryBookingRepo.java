package com.GoDo.godo.user_pack.deliverybooking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryBookingRepo extends JpaRepository<DeliveryBookingModel,String> {
    List<DeliveryBookingModel> findByUserId(String userId);

    List<DeliveryBookingModel> findByRouteId(String routeId);

    void deleteByRouteId(String routeId);
}
