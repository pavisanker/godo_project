package com.GoDo.godo.utilities_pack.id;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle_status")
public class VehicleStatusModel {

    @Id
    @Column(name = "statusId", unique = true)
    private Integer statusId;

    @Column(name = "statusName",unique = true)
    private String statusName;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
