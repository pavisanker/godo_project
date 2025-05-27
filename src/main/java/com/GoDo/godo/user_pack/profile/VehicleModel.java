package com.GoDo.godo.user_pack.profile;

import com.GoDo.godo.utilities_pack.CustomIdGenerator;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "godo_vehicle")
public class VehicleModel {

    @Id
    @Column(name = "vehicleId",unique = true)
    private String vehicleId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "vehicleType")
    private String vehicleType;

    @Column(name = "vehicleName")
    private String vehicleName;

    @Column(name = "registrationNumber",unique = true)
    private String registrationNumber;

    @Column(name = "chassisNumber",unique = true)
    private String chassisNumber;

    @Column(name = "rcBook",nullable = false)
    private String rcBook;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "status")
    private String status;


    @PrePersist
    public void generateId() {
        if (this.vehicleId == null || this.vehicleId.isEmpty()) {
            this.vehicleId = CustomIdGenerator.generateCustomId();
        }
    }


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getRcBook() {
        return rcBook;
    }

    public void setRcBook(String rcBook) {
        this.rcBook = rcBook;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
