package com.GoDo.godo.admin_pack;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8081","http://172.20.4.53:8081/","http://192.168.1.10:8081/"})
@RestController
@RequestMapping(path = "/api/admin")
public class AdminLoginController {

    // Default admin credentials
    private final String defaultUsername = "admin";
    private final String setPassword = "admin123";

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (defaultUsername.equals(username)&&setPassword.equals(password)) {
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } else {
            return ResponseEntity.ok(Collections.singletonMap("success", false));
        }
    }
}
