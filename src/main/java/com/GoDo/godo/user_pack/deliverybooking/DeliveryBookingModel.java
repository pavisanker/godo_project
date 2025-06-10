package com.GoDo.godo.user_pack.deliverybooking;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
@Table(name = "godo_delivery_booking")
public class DeliveryBookingModel {


        @Id
        @Column(name = "bookingId")
        private String bookingId;

        @Column(name = "userId", nullable = false)
        private String userId;

        @Column(name = "phoneNumber", nullable = false)
        private String phoneNumber;

        @Column(name = "start",nullable = false)
        private String start;

        @Column(name = "destination",nullable = false)
        private String destination;

        @Column(name = "bookingTime", nullable = false)
        private LocalDateTime bookingTime;

        @Column(name = "boardingTime", nullable = false)
        private LocalDateTime boardingTime;

        @Column(name = "routeId",nullable = false)
        private String routeId;

        @Column(name = "weight",nullable = false)
        private Integer weight;

        @Column(name = "status",nullable = false)
        private Integer status = 1;

        @Column(name = "paymentStatus",nullable = false)
        private Integer paymentStatus = 1;

        @Column(name = "paymentId")
        private String paymentId;

        private static final AtomicInteger COUNTER = new AtomicInteger(1);


        @PrePersist
        public void generateBookingId() {
            if (this.bookingId == null || this.bookingId.isEmpty()) {
                // Prefix "DEL", current date (yyyyMMdd), and counter (e.g., BKG-20250127-001)
                String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                int sequence = COUNTER.getAndIncrement();
                this.bookingId = String.format("DEL-%s-%03d", datePart, sequence);
            }
        }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalDateTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
