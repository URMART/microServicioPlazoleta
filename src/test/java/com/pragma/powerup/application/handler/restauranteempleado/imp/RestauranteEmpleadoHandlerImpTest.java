package com.pragma.powerup.application.handler.restauranteempleado.imp;

import com.pragma.powerup.application.dto.restauranteempleado.request.RestauranteEmpleadoRequestDto;
import com.pragma.powerup.application.dto.restauranteempleado.response.RestauranteEmpleadoResponseDto;
import com.pragma.powerup.application.mapper.restaurenteempleado.IRestauranteEmpleadoRequestMapper;
import com.pragma.powerup.application.mapper.restaurenteempleado.IRestauranteEmpleadoResponseMapper;
import com.pragma.powerup.domain.api.restauranteempleado.IRestauranteEmpleadoServicePort;
import com.pragma.powerup.domain.model.RestauranteEmpleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestauranteEmpleadoHandlerImpTest {

    @InjectMocks
    private RestauranteEmpleadoHandlerImp restauranteEmpleadoHandlerImp;
    @Mock
    private  IRestauranteEmpleadoServicePort restauranteEmpleadoServicePort;
    @Mock
    private  IRestauranteEmpleadoRequestMapper restauranteEmpleadoRequestMapper;
    @Mock
    private  IRestauranteEmpleadoResponseMapper restauranteEmpleadoResponseMapper;

    private RestauranteEmpleadoResponseDto restauranteEmpleadoResponseDto;
    private RestauranteEmpleadoRequestDto restauranteEmpleadoRequestDto;

    private RestauranteEmpleado restauranteEmpleado;

    @BeforeEach
    void setUp() {
        restauranteEmpleado = new RestauranteEmpleado();
        restauranteEmpleado.setId(1L);
        restauranteEmpleado.setIdRestaurante(1L);
        restauranteEmpleado.setIdEmpleado(1L);

        restauranteEmpleadoRequestDto = new RestauranteEmpleadoRequestDto();
        restauranteEmpleado.setIdRestaurante(1L);
        restauranteEmpleado.setIdEmpleado(1L);

        restauranteEmpleadoResponseDto = new RestauranteEmpleadoResponseDto();
        restauranteEmpleadoResponseDto.setId(1L);
        restauranteEmpleadoResponseDto.setIdRestaurante(1L);
        restauranteEmpleadoResponseDto.setIdEmpleado(1L);
    }

    @Test
    void saveRestauranteEmpleado() {

        when(restauranteEmpleadoRequestMapper.toRestauranteEmpleado(restauranteEmpleadoRequestDto))
                .thenReturn(restauranteEmpleado);

        restauranteEmpleadoHandlerImp.saveRestauranteEmpleado(restauranteEmpleadoRequestDto);

        verify(restauranteEmpleadoServicePort,times(1))
                .saveRestauranteEmpleado(restauranteEmpleado);
    }

    @Test
    void findById() {

        when(restauranteEmpleadoResponseMapper.toRestauranteEmpleadoDto(restauranteEmpleado))
                .thenReturn(restauranteEmpleadoResponseDto);

        when(restauranteEmpleadoServicePort.findById(restauranteEmpleado.getId())).thenReturn(restauranteEmpleado);

        RestauranteEmpleadoResponseDto restauranteEmpleadoEncontrado = restauranteEmpleadoHandlerImp.findById(1L);

        assertEquals(restauranteEmpleadoEncontrado.getId(),restauranteEmpleado.getId());

    }
}