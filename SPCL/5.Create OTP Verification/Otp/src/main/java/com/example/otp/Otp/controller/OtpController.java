package com.example.otp.Otp.controller;

import com.example.otp.Otp.model.OtpRequest;
import com.example.otp.Otp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/generate")
    public String generateOtp(@RequestBody String phoneNumberOrEmail) {
        String otp = otpService.generateOtp();
        otpService.saveOtp(phoneNumberOrEmail, otp);
        return "OTP sent to " + phoneNumberOrEmail;
    }

    @PostMapping("/validate")
    public String validateOtp(@RequestBody OtpRequest otpRequest) {
        boolean isValid = otpService.validateOtp(otpRequest.getPhoneNumberOrEmail(), otpRequest.getOtp());
        return isValid ? "OTP is valid!" : "OTP is invalid or expired.";
    }

    @PostMapping("/resend")
    public String resendOtp(@RequestBody String phoneNumberOrEmail) {
        String otp = otpService.generateOtp();
        otpService.saveOtp(phoneNumberOrEmail, otp);
        return "OTP resent to " + phoneNumberOrEmail;
    }
}
