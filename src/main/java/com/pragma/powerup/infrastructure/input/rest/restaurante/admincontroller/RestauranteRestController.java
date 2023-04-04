package com.pragma.powerup.infrastructure.input.rest.restaurante.admincontroller;

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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurante/auth/admin")
@RequiredArgsConstructor
public class RestauranteRestController {

    private final IRestauranteHandler restauranteHandler;

    private final UsuariosClient usuariosClient;


    @Operation(summary = "añadir un nuevo restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Void> saveRestaurante(@Valid @RequestBody RestauranteRequestDto restauranteRequestDto) {
        Usuarios usuarios = usuariosClient.findById(restauranteRequestDto.getIdPropietario());

        if (usuarios!= null) {
            restauranteHandler.saveRestaurante(restauranteRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/createPropietario")
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Void> savePropietario(@Valid @RequestBody Usuarios  usuarios) {

        Rol rol = usuariosClient.findByNombre("ROLE_PROPIETARIO");
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