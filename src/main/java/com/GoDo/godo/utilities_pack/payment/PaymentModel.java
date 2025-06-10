package com.GoDo.godo.utilities_pack.payment;

import com.GoDo.godo.utilities_pack.CustomIdGenerator;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
@Table(name = "godo_payment")
public class PaymentModel {

    @Id
    @Column(name = "paymentId", unique = true)
    private String paymentId;

    @Column(name = "customerId")
    private String customerId;

    @Column(name = "ownerId")
    private String ownerId;

    @Column(name = "routeId")
    private String routeId;

    @Column(name = "bookingId")
    private String bookingId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "paymentDate")
    private LocalDateTime paymentDate;



    // Razorpay-specific fields
    @Column(name = "razorpayOrderId")
    private String razorpayOrderId;


    @Column(name = "razorpay_payment_id")
    private String razorpay_payment_id;

    @Column(name = "razorpay_signature")
    private String razorpay_signature;


    @Column(name = "status")
    private String status; // CREATED, SUCCESS, FAILED

    public String getPaymentId() {
        return paymentId;
    }
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    @PrePersist
    public void generatePaymentId() {
        if (this.paymentId == null || this.paymentId.isEmpty()) {
            // Prefix "PAY", current date (yyyyMMdd), and counter (e.g., BKG-20250127-001)
            String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            int sequence = COUNTER.getAndIncrement();
            this.paymentId = String.format("PAY-%s-%03d", datePart, sequence);
        }
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpay_payment_id() {
        return razorpay_payment_id;
    }

    public void setRazorpay_payment_id(String razorpay_payment_id) {
        this.razorpay_payment_id = razorpay_payment_id;
    }

    public String getRazorpay_signature() {
        return razorpay_signature;
    }

    public void setRazorpay_signature(String razorpay_signature) {
        this.razorpay_signature = razorpay_signature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


   }
