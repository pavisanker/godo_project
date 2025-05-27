package com.GoDo.godo.utilities_pack.id;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gender_type")
public class GenderModel {

    @Id
    @Column(name = "genderId", unique = true)
    private Integer genderId;

    @Column(name = "genderName", unique = true)
    private String genderName;


    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
