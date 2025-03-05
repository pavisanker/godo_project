package com.GoDo.godo.user_pack.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})

@RestController
@RequestMapping("/api/godo")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/otp")
    public ResponseEntity<?> registerUser(@RequestBody OtpModel otpModel) {
        return otpService.registerUser(otpModel);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestParam String session, @RequestParam String otp) {
        return otpService.verifyOtp(session,otp);
    }
}
