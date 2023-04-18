package com.pragma.powerup.application.handler.restaurante.imp;

import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseClienteDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.application.handler.factory.FactoryRestauranteData;
import com.pragma.powerup.application.mapper.restaurante.IRestauranteRequestMapper;
import com.pragma.powerup.application.mapper.restaurante.IRestauranteResponseMapper;
import com.pragma.powerup.domain.api.restaurante.IRestauranteServicePort;
import com.pragma.powerup.domain.model.Restaurante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestauranteHandlerImpTest {


    @InjectMocks
    private RestauranteHandlerImp restauranteHandlerImp;
    @Mock
    private  IRestauranteServicePort restauranteServicePort;
    @Mock
    private  IRestauranteRequestMapper restauranteRequestMapper;
    @Mock
    private  IRestauranteResponseMapper restauranteResponseMapper;

    private RestauranteRequestDto restauranteRequestDto;

    private RestauranteResponseDto restauranteResponseDto;
    private RestauranteResponseClienteDto restauranteResponseClienteDto;
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {

        restaurante = FactoryRestauranteData.restauranteData();
        restauranteResponseDto = FactoryRestauranteData.restauranteResponseDtoData();
        restauranteRequestDto = FactoryRestauranteData.restauranteRequestDtoData();
        restauranteResponseClienteDto = FactoryRestauranteData.restauranteResponseClienteDtoData();
    }

    @Test
    void saveRestaurante() {

        when(restauranteRequestMapper.toRestaurante(restauranteRequestDto)).thenReturn(restaurante);

        restauranteHandlerImp.saveRestaurante(restauranteRequestDto);

        verify(restauranteServicePort,timeout(1)).saveRestaurante(restaurante);

    }

    @Test
    void getAllRestaurantes() {

        List<Restaurante> restauranteList = new ArrayList<>();
        restauranteList.add(restaurante);

        List<RestauranteResponseDto> restauranteListDtoList = new ArrayList<>();
        restauranteListDtoList.add(restauranteResponseDto);

        when(restauranteResponseMapper.toRestauranteDtoList(restauranteList)).thenReturn(restauranteListDtoList);

        when(restauranteServicePort.getAllRestaurantes()).thenReturn(restauranteList);

        List<RestauranteResponseDto> restauranteListDtoListEncontrado = restauranteHandlerImp.getAllRestaurantes();

        assertEquals(restauranteListDtoListEncontrado.size(),restauranteList.size());


    }

    @Test
    void findByNombre() {
        when( restauranteResponseMapper.toRestauranteDto(restaurante)).thenReturn(restauranteResponseDto);

        when(restauranteServicePort.findByNombre(restauranteRequestDto.getNombre())).thenReturn(restaurante);

        RestauranteResponseDto restauranteEncontrado = restauranteHandlerImp
                .findByNombre(restauranteRequestDto.getNombre());

        assertEquals(restauranteEncontrado.getNombre(),restaurante.getNombre());
    }


    @Test
    void getAllRestaurantesPaginados() {

        List<Restaurante> restauranteList = new ArrayList<>();
        restauranteList.add(restaurante);

        List<RestauranteResponseClienteDto> restauranteClienteListDtoList = new ArrayList<>();
        restauranteClienteListDtoList.add(restauranteResponseClienteDto);

        when(restauranteResponseMapper.toRestauranteClienteDtoList(restauranteList)).thenReturn(restauranteClienteListDtoList);

        when(restauranteServicePort.getAllRestaurantesPaginados(0,4)).thenReturn(restauranteList);

        List<RestauranteResponseClienteDto> restauranteClienteListDtoListEncontrado = restauranteHandlerImp
                .getAllRestaurantesPaginados(0,4);

        assertEquals(restauranteClienteListDtoListEncontrado.size(),restauranteList.size());



    }

    @Test
    void findById() {

        when(restauranteResponseMapper.toRestauranteDto(restaurante)).thenReturn(restauranteResponseDto);
        when(restauranteServicePort.findById(restauranteRequestDto.getId())).thenReturn(restaurante);

        RestauranteResponseDto restauranteEncontrado = restauranteHandlerImp.findById(1L);
        assertEquals(restauranteEncontrado.getId(),restaurante.getId());
    }
}