package com.example.otp.Otp.service;

import com.example.otp.Otp.model.OtpRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    // In-memory map to store OTPs and their expiry times (you can replace it with a DB in a real application)
    private Map<String, OtpRequest> otpStore = new HashMap<>();
    private static final long OTP_EXPIRY_TIME = TimeUnit.MINUTES.toMillis(5);  // OTP validity: 5 minutes

    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generates a 6-digit OTP
        return String.valueOf(otp);
    }

    public void saveOtp(String phoneOrEmail, String otp) {
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setPhoneNumberOrEmail(phoneOrEmail);
        otpRequest.setOtp(otp);
        otpStore.put(phoneOrEmail, otpRequest);

        // Set the OTP expiry time
        new Thread(() -> {
            try {
                Thread.sleep(OTP_EXPIRY_TIME);
                otpStore.remove(phoneOrEmail);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public boolean validateOtp(String phoneOrEmail, String otp) {
        OtpRequest storedOtpRequest = otpStore.get(phoneOrEmail);
        if (storedOtpRequest != null && storedOtpRequest.getOtp().equals(otp)) {
            return true; // OTP is valid
        }
        return false; // OTP is invalid or expired
    }
}
