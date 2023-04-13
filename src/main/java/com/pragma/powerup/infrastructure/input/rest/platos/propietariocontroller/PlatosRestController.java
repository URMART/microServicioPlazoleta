package com.pragma.powerup.infrastructure.input.rest.platos.propietariocontroller;

import com.pragma.powerup.application.dto.categoria.response.CategoriaResponseDto;
import com.pragma.powerup.application.dto.plato.request.PlatoPutHabilitadoRequest;
import com.pragma.powerup.application.dto.plato.request.PlatoPutRequestDto;
import com.pragma.powerup.application.dto.plato.request.PlatoRequestDto;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.application.handler.categoria.ICategoriaHandler;
import com.pragma.powerup.application.handler.platos.IPlatosHandler;
import com.pragma.powerup.application.handler.restaurante.IRestauranteHandler;
import com.pragma.powerup.application.mapper.categoria.ICategoriaResponseMapper;
import com.pragma.powerup.application.mapper.platos.IPlatoRequestResponseDto;
import com.pragma.powerup.application.mapper.restaurante.IRestauranteResponseMapper;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.client.UsuariosClient;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.modelsmicroservice.Usuarios;
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
@RequestMapping("/api/v1/platos/auth/propietario")
@RequiredArgsConstructor
public class PlatosRestController {

    private final IPlatosHandler platosHandler;
    private final ICategoriaHandler categoriaHandler;
    private final IRestauranteHandler restauranteHandler;

    private final ICategoriaResponseMapper categoriaResponseMapper;
    private final IRestauranteResponseMapper restauranteResponseMapper;
    private final IPlatoRequestResponseDto platoRequestResponseDto;

    private final UsuariosClient usuariosClient;





    @Operation(summary = "añadir un nuevo plato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PROPIETARIO')")
    public ResponseEntity<Void> savePlato(@Valid @RequestBody PlatoRequestDto platoRequestDto,@PathVariable Long id ) {
        Usuarios usuario = usuariosClient.findById(id);
        RestauranteResponseDto restaurante = restauranteHandler.findByNombre(platoRequestDto.getRestaurante().getNombre());
        CategoriaResponseDto categoria = categoriaHandler.findByNombre(platoRequestDto.getCategoria().getNombre());


        if (categoria == null || restaurante == null || usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(restaurante.getIdPropietario()== usuario.getId()){
            platoRequestDto.setCategoria(categoriaResponseMapper.toCategoriaModel(categoria));
            platoRequestDto.setRestaurante(restauranteResponseMapper.toRestauranteModel(restaurante));
            platosHandler.savePlato(platoRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);



    }

    @Operation(summary = "añadir un nuevo plato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PutMapping("/{id}/{nombre}")
    @PreAuthorize("hasRole('ROLE_PROPIETARIO')")
    public ResponseEntity<Void> putPlato(@Valid @PathVariable String nombre,@PathVariable("id") Long idPropietario ,@RequestBody PlatoPutRequestDto platoPutRequestDto) {
        Usuarios usuario = usuariosClient.findById(idPropietario);
        PlatoResponseDto plato = platosHandler.findByNombre(nombre);
        Long idPropietarioRestaurante = plato.getRestaurante().getIdPropietario();


        try{
            if ( plato == null || usuario == null  ){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            if(idPropietarioRestaurante == usuario.getId()){
                plato.setDescripcion(platoPutRequestDto.getDescripcion());
                plato.setPrecio(platoPutRequestDto.getPrecio());

                platosHandler.savePlato(platoRequestResponseDto.toRequestDto(plato));

                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        }catch (Exception e){ return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);}



    }
    @Operation(summary = "añadir un nuevo plato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Object already exists", content = @Content)
    })
    @PutMapping("/{id}/{nombreplato}/habilitar")
    @PreAuthorize("hasRole('ROLE_PROPIETARIO')")
    public ResponseEntity<Void> habilitarODeshabilitarPlato(@Valid @PathVariable String nombreplato,
                                                            @PathVariable("id") Long idPropietario ,
                                                            @RequestBody PlatoPutHabilitadoRequest platoPutHabilitadoRequest)
    {
        Usuarios usuario = usuariosClient.findById(idPropietario);
        PlatoResponseDto plato = platosHandler.findByNombre(nombreplato);
        Long idPropietarioRestaurante = plato.getRestaurante().getIdPropietario();


        try{
            if ( plato == null || usuario == null  ){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            if(idPropietarioRestaurante == usuario.getId()){
                plato.setActivo(platoPutHabilitadoRequest.getActivo());
                platosHandler.savePlato(platoRequestResponseDto.toRequestDto(plato));
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }catch (Exception e){ throw  new NoDataFoundException();}



    }




}
