package com.example.autentication.otp;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TwilioService twilioService;

    public void registerUser(String phoneNumber) {
        // Generate OTP
        String otp = generateOtp();
        // Set OTP expiration time (e.g., 5 minutes from now)
        LocalDateTime otpExpiration = LocalDateTime.now().plusMinutes(5);

        // Save user to the database
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setOtp(otp);
        user.setOtpExpiration(otpExpiration);
        userRepository.save(user);

        // Send OTP via SMS
        twilioService.sendSms(phoneNumber, "Your OTP is: " + otp);
    }

//    public void verifyOtp(String phoneNumber, String otp) {
//        // Get user by phone number
//        User user = userRepository.findByPhoneNumber(phoneNumber);
//
//        if (user != null && user.getOtp().equals(otp) && user.getOtpExpiration().isAfter(LocalDateTime.now())) {
//            // OTP is valid, perform user verification logic
//            // ...
//      
//        } else {
//            // OTP is invalid or expired, handle error
//            // ...
//        }
//		return false;
//    }
    
    public boolean verifyOtp(String phoneNumber, String otp) {
        // Get user by phone number
        User user = userRepository.findByPhoneNumber(phoneNumber);

//        if (user != null && user.getOtp().equals(otp) && user.getOtpExpiration().isAfter(LocalDateTime.now())) {
        if (user != null && user.getOtp() != null && user.getOtp().equals(otp) && user.getOtpExpiration().isAfter(LocalDateTime.now())) {
            // OTP is valid, perform user verification logic
            // ...

            // Mark OTP as used
            user.setOtp(null);
            user.setOtpExpiration(null);
            userRepository.save(user);

            return true;
        } else {
            // OTP is invalid or expired, handle error
            return false;
        }
    }


    private String generateOtp() {
        // Generate random OTP (e.g., 6 digits)
        return String.format("%06d", new Random().nextInt(999999));
    }
}