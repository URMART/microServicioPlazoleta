package com.pragma.powerup.infrastructure.input.rest.platos;

import com.pragma.powerup.application.dto.categoria.response.CategoriaResponseDto;
import com.pragma.powerup.application.dto.plato.request.PlatoPutRequestDto;
import com.pragma.powerup.application.dto.plato.request.PlatoRequestDto;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.application.handler.categoria.ICategoriaHandler;
import com.pragma.powerup.application.handler.platos.IPlatosHandler;
import com.pragma.powerup.application.handler.restaurante.IRestauranteHandler;
import com.pragma.powerup.application.mapper.categoria.ICategoriaResponseMapper;
import com.pragma.powerup.application.mapper.platos.IPlatoRequestMapper;
import com.pragma.powerup.application.mapper.platos.IPlatoRequestResponseDto;
import com.pragma.powerup.application.mapper.restaurante.IRestauranteResponseMapper;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exceptionhandler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/platos")
@RequiredArgsConstructor
public class PlatosRestController {

    private final IPlatosHandler platosHandler;
    private final ICategoriaHandler categoriaHandler;
    private final IRestauranteHandler restauranteHandler;

    private final ICategoriaResponseMapper categoriaResponseMapper;
    private final IRestauranteResponseMapper restauranteResponseMapper;
    private final IPlatoRequestResponseDto platoRequestResponseDto;



    @Operation(summary = "añadir un nuevo plato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping("/auth/propietario")
    public ResponseEntity<Void> savePlato(@Valid @RequestBody PlatoRequestDto platoRequestDto) {

        RestauranteResponseDto restaurante = restauranteHandler.findByNombre(platoRequestDto.getRestaurante().getNombre());
        CategoriaResponseDto categoria = categoriaHandler.findByNombre(platoRequestDto.getCategoria().getNombre());
        if (categoria == null || restaurante == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            platoRequestDto.setCategoria(categoriaResponseMapper.toCategoriaModel(categoria));
            platoRequestDto.setRestaurante(restauranteResponseMapper.toRestauranteModel(restaurante));
            platosHandler.savePlato(platoRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "añadir un nuevo plato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PutMapping("/{nombre}/auth/propietario")
    public ResponseEntity<Void> putPlato(@Valid @PathVariable String nombre, @RequestBody PlatoPutRequestDto platoPutRequestDto) {


        PlatoResponseDto plato = platosHandler.findByNombre(nombre);

        if ( plato == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        plato.setDescripcion(platoPutRequestDto.getDescripcion());
        plato.setPrecio(platoPutRequestDto.getPrecio());

        platosHandler.savePlato(platoRequestResponseDto.toRequestDto(plato));

        return new ResponseEntity<>(HttpStatus.OK);



    }




}
