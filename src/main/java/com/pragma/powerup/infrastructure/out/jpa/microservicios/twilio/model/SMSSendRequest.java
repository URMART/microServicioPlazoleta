package com.pragma.powerup.infrastructure.out.jpa.microservicios.twilio.model;

import lombok.Data;

@Data
public class SMSSendRequest {

    private String numeroDestino;
    private String smsMessage;


}
