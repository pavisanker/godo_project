package com.GoDo.godo.user_pack.travelbooking;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
@Table(name = "godo_travel_booking")
public class TravelBookingModel {
    @Id
    @Column(name = "bookingId")
    private String bookingId;

    @Column(name = "userId", nullable = false)
    private String userId;

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

    @Column(name = "passengerCount",nullable = false)
    private Integer passengerCount;

    @Column(name = "status",nullable = false)
    private String status = "Pending";


    private static final AtomicInteger COUNTER = new AtomicInteger(1);


    @PrePersist
    public void generateBookingId() {
        if (this.bookingId == null || this.bookingId.isEmpty()) {
            // Prefix "BKG", current date (yyyyMMdd), and counter (e.g., BKG-20250127-001)
            String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            int sequence = COUNTER.getAndIncrement();
            this.bookingId = String.format("BKG-%s-%03d", datePart, sequence);
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

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

