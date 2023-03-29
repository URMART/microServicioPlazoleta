package com.pragma.powerup.application.handler.restaurante;


import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;

import java.util.List;

public interface IRestauranteHandler {

    void saveRestaurante(RestauranteRequestDto restauranteRequestDto);

    List<RestauranteResponseDto> getAllRestaurantes();
}