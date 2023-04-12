package com.pragma.powerup.infrastructure.input.rest.platos.clientecontroller;

import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseClienteDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.application.handler.platos.IPlatosHandler;
import com.pragma.powerup.application.handler.restaurante.IRestauranteHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/restaurante/auth/cliente")
@RequiredArgsConstructor
public class ClienteControllerPlatos {

    private final IPlatosHandler platosHandler;

    @Operation(summary = "listar platos paginados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All objects returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestauranteResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/platos/{nombre}")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<List<PlatoResponseDto>> getAllPlatosPaginados(@PathVariable String nombre, @RequestParam("page") int page,
                                                                        @RequestParam("size") int size)
    {
        return ResponseEntity.ok(platosHandler.getAllPlatosPaginadosPorRestaurante(nombre,page, size));
    }
}
