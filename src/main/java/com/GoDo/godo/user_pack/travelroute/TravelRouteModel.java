package com.GoDo.godo.user_pack.travelroute;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
@Table(name = "godo_travel_route")
public class TravelRouteModel {

    @Id
    @Column(name = "routeId", unique = true)
    private String routeId;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "vehicleId")
    private String vehicleId;

    @Column(name = "vacancy")
    private Integer vacancy;

    @Column(name = "booked")
    private Integer booked;

    @Column(name = "delivery")
    private Integer delivery;

    @Column(name = "start",nullable = false)
    private String start;

    @Column(name = "destination",nullable = false)
    private String destination;

    @Column(name = "boardingTime", nullable = false)
    private LocalDateTime boardingTime ;

    @Column(name = "status",nullable = false)
    private String status;

    @Column(name = "capacity",nullable = false)
    private Integer capacity;

    @Column(name = "totalWeight")
    private  Integer totalWeight;


    private static final AtomicInteger COUNTER = new AtomicInteger(1);


    @PrePersist
    public void generateRouteId() {
        if (this.routeId == null || this.routeId.isEmpty()) {
            String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            int sequence = COUNTER.getAndIncrement();
            this.routeId = String.format("RDL-%s-%03d", datePart, sequence);
        }
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
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

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getVacancy() {
        return vacancy;
    }

    public void setVacancy(Integer vacancy) {
        this.vacancy = vacancy;
    }

    public Integer getBooked() {
        return booked;
    }

    public void setBooked(Integer booked) {
        this.booked = booked;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
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

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalDateTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }
}
