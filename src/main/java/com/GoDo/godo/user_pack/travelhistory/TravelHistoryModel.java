package com.GoDo.godo.user_pack.travelhistory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "godo_history")
public class TravelHistoryModel {
    @Id
    @Column(name = "travelId", unique = true)
    private String travelId;

    @Column(name = "ownerId", nullable = false)
    private String ownerId;

    @Column(name = "ownerPhoneNumber", nullable = false)
    private String ownerPhoneNumber;


    @Column(name = "vehicleId")
    private String vehicleId;

    @Column(name = "vacancy")
    private Integer vacancy;

    @Column(name = "booked")
    private Integer booked;

    @Column(name = "start",nullable = false)
    private String start;

    @Column(name = "destination",nullable = false)
    private String destination;

    @Column(name = "boardingTime", nullable = false)
    private LocalDateTime boardingTime ;

    @Column(name = "status",nullable = false)
    private String status;

    @Column(name = "customerId",nullable = false)
    private String customerId;

    @Column(name = "deliveryCustomerId")
    private String deliveryCustomerId;

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryCustomerId() {
        return deliveryCustomerId;
    }

    public void setDeliveryCustomerId(String deliveryCustomerId) {
        this.deliveryCustomerId = deliveryCustomerId;
    }
}
