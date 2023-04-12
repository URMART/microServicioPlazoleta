package com.pragma.powerup.infrastructure.input.rest.restaurante.clientecontroller;

import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseClienteDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.application.handler.restaurante.IRestauranteHandler;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.client.UsuariosClient;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.modelsmicroservice.Rol;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.modelsmicroservice.Usuarios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/v1/restaurante/auth/cliente")
@RequiredArgsConstructor
public class ClienteController {


    private final IRestauranteHandler restauranteHandler;
    private final UsuariosClient usuariosClient;


    @Operation(summary = "listar restaurantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All objects returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestauranteResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/paginados")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<List<RestauranteResponseClienteDto>> getAllRestaurantesPaginados(@RequestParam("page") int page,
                                                                                           @RequestParam("size") int size) {
        return ResponseEntity.ok(restauranteHandler.getAllRestaurantesPaginados(page, size));
    }




    @PostMapping("/createCliente")
    public ResponseEntity<Void> saveCliente(@Valid @RequestBody Usuarios usuarios) {
        Rol rol = usuariosClient.findByNombre("ROLE_CLIENTE");
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
