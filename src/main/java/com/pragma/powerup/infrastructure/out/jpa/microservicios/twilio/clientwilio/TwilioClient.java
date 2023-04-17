package com.pragma.powerup.infrastructure.out.jpa.microservicios.twilio.clientwilio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="twilio-service",path = "/api/v1/sms")
public interface TwilioClient {

    @GetMapping("/pedidoListo")
    public String enviarSmsPedidoListo();
    @GetMapping("/noCancelarPedido")
    public String enviarSmsNoCancelarPedido();


}
