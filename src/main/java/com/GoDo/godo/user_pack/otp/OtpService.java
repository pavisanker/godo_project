package com.GoDo.godo.user_pack.otp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class OtpService {

    @Autowired
    private OtpRepo otpRepo;

    @Value("${otp.api.key}")
    private String API_KEY;

    @Value("${otp.api.url}")
    private String BASE_URL;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String phoneNumber;
    public Boolean isNewUser = false;

    // Registers the user and sends OTP via SMS
    public ResponseEntity<?> registerUser(OtpModel otpModel) {

        try {
            phoneNumber = otpModel.getPhoneNumber();

            // Check if user already exists
            Optional<OtpModel> optionalOtpModel = otpRepo.findByPhoneNumber(phoneNumber);
            if (optionalOtpModel.isPresent()) {
                // Update existing user
                otpModel = optionalOtpModel.get();
                otpModel.setDateUpdated(LocalDateTime.now());
                isNewUser = false;
            } else {
                // Create new user entry
                isNewUser = true;
                otpModel.setPhoneNumber(phoneNumber);
                otpModel.setDateCreated(LocalDateTime.now());
                otpModel.setDateUpdated(LocalDateTime.now());
            }

            // Send OTP and get sessionId
            String sessionId = sendOtp(phoneNumber);
            if (sessionId == null) {
                return new ResponseEntity<>("Failed to send OTP", HttpStatus.BAD_REQUEST);
            }

            // Save sessionId and other details
            otpModel.setSessionId(sessionId);

            otpRepo.save(otpModel);
            Map<String, String> otpResponse = new HashMap<>();
            otpResponse.put("message", "OTP Sent Successfully");
            otpResponse.put("sessionId", sessionId);

            return new ResponseEntity<>(otpResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error sending OTP: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Sends OTP and extracts sessionId from API response
    public String sendOtp(String phoneNumber) {
        String url = BASE_URL + API_KEY + "/SMS/" + phoneNumber + "/AUTOGEN";
        Request request = new Request.Builder().url(url).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                return null;
            }
            // Parse JSON response to extract sessionId
            JsonNode jsonResponse = objectMapper.readTree(response.body().string());
            return jsonResponse.has("Details") ? jsonResponse.get("Details").asText() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Verifies OTP
    public ResponseEntity<?> verifyOtp(String session, String otp) {
        try {
            String sessionId = null;
            Optional<OtpModel> optionalOtpModel = otpRepo.findByPhoneNumber(phoneNumber);
            if(optionalOtpModel.isPresent()){
                sessionId = optionalOtpModel.get().getSessionId();
            }

            if (session.isEmpty()&&!session.equals(sessionId)) {
                return new ResponseEntity<>("Session ID not found for this phone number", HttpStatus.BAD_REQUEST);
            }

            // Verify OTP using sessionId
            String url = BASE_URL + API_KEY + "/SMS/VERIFY/" + session + "/" + otp;
            Request request = new Request.Builder().url(url).get().build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
                }
                Map<String, Object> verifyResponse = new HashMap<>();
                verifyResponse.put("message", "OTP Verified Successfully");
                verifyResponse.put("sessionId", session);
                verifyResponse.put("isNewUser",isNewUser);
                return new ResponseEntity<>(verifyResponse, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error verifying OTP: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
