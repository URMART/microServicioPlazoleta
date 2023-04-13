package com.pragma.powerup.application.handler.restaurante.imp;

import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseClienteDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.application.handler.restaurante.IRestauranteHandler;
import com.pragma.powerup.application.mapper.restaurante.IRestauranteRequestMapper;
import com.pragma.powerup.application.mapper.restaurante.IRestauranteResponseMapper;
import com.pragma.powerup.domain.api.restaurante.IRestauranteServicePort;
import com.pragma.powerup.domain.model.Restaurante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestauranteHandlerImp implements IRestauranteHandler {

    private final IRestauranteServicePort restauranteServicePort;
    private final IRestauranteRequestMapper restauranteRequestMapper;
    private final IRestauranteResponseMapper restauranteResponseMapper;



    @Override
    public void saveRestaurante(RestauranteRequestDto restauranteRequestDto) {
        Restaurante restauranteModel = restauranteRequestMapper.toRestaurante(restauranteRequestDto);
        restauranteServicePort.saveRestaurante(restauranteModel);
    }

    @Override
    public List<RestauranteResponseDto> getAllRestaurantes() {
        return restauranteResponseMapper.toRestauranteDtoList(restauranteServicePort.getAllRestaurantes());
    }


    @Override
    public RestauranteResponseDto findByNombre(String nombre) {
        return restauranteResponseMapper.toRestauranteDto(restauranteServicePort.findByNombre(nombre));
    }

    @Override
    public List<RestauranteResponseClienteDto> getAllRestaurantesPaginados(int offset, int count) {
        return restauranteResponseMapper.toRestauranteClienteDtoList(restauranteServicePort.getAllRestaurantesPaginados(offset, count));
    }

    @Override
    public RestauranteResponseDto findById(Long id) {
        return restauranteResponseMapper.toRestauranteDto(restauranteServicePort.findById(id));
    }
}