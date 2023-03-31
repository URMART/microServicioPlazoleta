package com.pragma.powerup.infrastructure.out.jpa.microservicios.client;

import com.pragma.powerup.infrastructure.out.jpa.microservicios.modelsmicroservice.Usuarios;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="usuarios-service",path = "/api/v1/usuarios")
public interface UsuariosClient {
    @GetMapping("/id/{id}/auth/admin")
    public Usuarios findById(@PathVariable Long id);
}
