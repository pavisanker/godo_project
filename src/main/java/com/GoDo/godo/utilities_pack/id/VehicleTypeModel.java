package com.GoDo.godo.utilities_pack.id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle_type")
public class VehicleTypeModel {

    @Id
    @Column(name = "vehicleTypeId", unique = true)
    private Integer vehicleTypeId;

    @Column(name = "vehicleTypeName",unique = true)
    private String vehicleTypeName;

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }
}
