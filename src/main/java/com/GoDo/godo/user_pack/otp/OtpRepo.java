package com.GoDo.godo.user_pack.otp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepo extends JpaRepository<OtpModel,String> {
    Optional<OtpModel> findByPhoneNumber(String phoneNumber);

    Optional<OtpModel> findBySessionId(String sessionId);
}
