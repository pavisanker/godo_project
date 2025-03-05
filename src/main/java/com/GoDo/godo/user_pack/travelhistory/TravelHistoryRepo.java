package com.GoDo.godo.user_pack.travelhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelHistoryRepo extends JpaRepository<TravelHistoryModel,String> {

//    List<TravelHistoryModel> findByOwnerId(String userId);

//    List<TravelHistoryModel> findByOwnerIdOrCustomerIdContaining(String ownerId, String customerId);

    List<TravelHistoryModel> findByOwnerId(String userId);

    List<TravelHistoryModel> findByCustomerIdContaining(String userId1);

    List<TravelHistoryModel> findByDeliveryCustomerIdContaining(String userId);
}
