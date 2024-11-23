package com.example.otp.Otp.model;

public class OtpRequest {

    private String phoneNumberOrEmail;
    private String otp;

    // Getters and Setters
    public String getPhoneNumberOrEmail() {
        return phoneNumberOrEmail;
    }

    public void setPhoneNumberOrEmail(String phoneNumberOrEmail) {
        this.phoneNumberOrEmail = phoneNumberOrEmail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
