package com.example.autentication.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String phoneNumber) {
        userService.registerUser(phoneNumber);
        return ResponseEntity.ok("User registered successfully");
    }

//    @PostMapping("/verify")
//    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
//        userService.verifyOtp(phoneNumber, otp);
//        return ResponseEntity.ok("true");
//    }
    
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        boolean verified = userService.verifyOtp(phoneNumber, otp);
        userService.verifyOtp(phoneNumber, otp);
        if (verified) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.badRequest().body("false");
        }
    }
}


