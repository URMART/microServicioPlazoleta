package com.pragma.powerup.application.handler.restaurante;


import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseClienteDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.domain.model.Restaurante;

import java.util.List;

public interface IRestauranteHandler {

    void saveRestaurante(RestauranteRequestDto restauranteRequestDto);

    List<RestauranteResponseDto> getAllRestaurantes();

    RestauranteResponseDto findByNombre(String nombre);

    List<RestauranteResponseClienteDto> getAllRestaurantesPaginados(int offset, int count);

    RestauranteResponseDto findById(Long id);


}