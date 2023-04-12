package com.pragma.powerup.infrastructure.input.rest.restaurante.propietariocontroller;

import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.application.handler.restaurante.IRestauranteHandler;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.client.UsuariosClient;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.modelsmicroservice.Rol;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.modelsmicroservice.Usuarios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurante/auth/propietario")
@RequiredArgsConstructor
public class PropietarioController {

    private final UsuariosClient usuariosClient;


    @PostMapping("/createEmpleado")
    @PreAuthorize("hasRole('ROLE_PROPIETARIO')")
    public ResponseEntity<Void> saveEmpleado(@Valid @RequestBody Usuarios  usuarios) {
        Rol rol = usuariosClient.findByNombre("ROLE_EMPLEADO");
        if(rol == null){return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);}

        try {
            usuarios.setRol(rol);
            usuariosClient.saveUsuario(usuarios);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {

            throw new NoDataFoundException();

        }

    }
}