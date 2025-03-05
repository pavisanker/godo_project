package com.GoDo.godo.user_pack.otp;

import com.GoDo.godo.utilities.CustomIdGenerator;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "godo_login")
public class OtpModel {
    @Id
    @Column(name = "userId", unique = true)
    private String userId;

    @Column(name = "phoneNumber", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "dateCreated", updatable = false)
    private LocalDateTime dateCreated=LocalDateTime.now();

    @Column(name = "dateUpdated")
    private LocalDateTime dateUpdated=LocalDateTime.now();

    @Column(name = "sessionId", unique = true)
    private String sessionId;

    @PrePersist
    public void generateId() {
        if (this.userId == null || this.userId.isEmpty()) {
            this.userId = CustomIdGenerator.generateCustomId();
        }
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
