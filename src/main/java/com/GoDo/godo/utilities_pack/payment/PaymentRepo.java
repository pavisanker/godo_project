package com.GoDo.godo.utilities_pack.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<PaymentModel, String> {

    PaymentModel findByRazorpayOrderId(String razorpay_order_id);

    @Query("SELECT ut FROM PaymentModel ut WHERE ut.customerId = :customerId AND ut.status = :status")
    Optional<PaymentModel> findByCustomerIdAndPaymentStatus(@Param("customerId") String customerId, @Param("status") String status);

    @Query("SELECT ut FROM PaymentModel ut WHERE ut.customerId = :customerId")
    List<PaymentModel> findByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT ut FROM PaymentModel ut WHERE ut.customerId = :customerId AND ut.status = :status")
    List<PaymentModel> findByCustomerIdAndStatus(@Param("customerId") String customerId, @Param("status") String status);

    @Query(value = "SELECT * FROM payment_model WHERE customer_id = :customerId AND status = :status ORDER BY payment_date DESC LIMIT 1", nativeQuery = true)
    Optional<PaymentModel> findByLatestPaymentByCustomerId(@Param("customerId") String customerId, @Param("status") String status);

    List<PaymentModel> findAllByOrderByPaymentDateDesc();
}
