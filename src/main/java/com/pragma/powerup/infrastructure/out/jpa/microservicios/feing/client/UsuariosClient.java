package com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.client;

import com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.modelsmicroservice.Rol;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.modelsmicroservice.Usuarios;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name="usuarios-service",path = "/api/v1/usuarios")
public interface UsuariosClient {
    @GetMapping("/id/{id}/auth/admin")
    public Usuarios findById(@PathVariable Long id);
    @GetMapping("/{nombre}/email/auth/admin")
    public Usuarios findByEmail(@PathVariable String nombre);

    @PostMapping()
    public ResponseEntity<Void> saveUsuario(@Valid @RequestBody Usuarios usuarios);

    @GetMapping("/{nombre}/auth/admin")
    public Rol findByNombre(@PathVariable String nombre);


}
