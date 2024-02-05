package com.example.autentication.otp;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {
    private static final String TWILIO_ACCOUNT_SID = "AC05eb970ad07d185d8965c916de8b3a3d";
    private static final String TWILIO_AUTH_TOKEN = "3ada8b22bcc30230ea074a2047dd9eca";
    private static final String TWILIO_PHONE_NUMBER = "+15076773270";

    public void sendSms(String toPhoneNumber, String message) {
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
        Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(TWILIO_PHONE_NUMBER),
                message)
                .create();
    }
}
