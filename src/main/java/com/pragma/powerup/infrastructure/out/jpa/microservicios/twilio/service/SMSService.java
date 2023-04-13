package com.pragma.powerup.infrastructure.out.jpa.microservicios.twilio.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class SMSService {

    @Value("${TWILIO.ACCOUNT.SID}")
    String ACCOUNT_SID;

    @Value("${TWILIO.ACCOUNT.TOKEN}")
    String ACCOUNT_TOKEN;

    @Value("${TWILIO.OUTGOING.SMS.NUMBER}")
    String OUTGOING_SMS_NUMBER;

    @PostConstruct
    private void setup(){
        Twilio.init(ACCOUNT_SID,ACCOUNT_TOKEN);
    }

    public String sendSms(String smsNumero,String smsMensaje)
    {
        Message message = Message.creator(
                new PhoneNumber(smsNumero),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsMensaje
        ).create();


        return  message.getStatus().toString();
    }


}