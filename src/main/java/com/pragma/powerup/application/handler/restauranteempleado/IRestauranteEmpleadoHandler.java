package com.pragma.powerup.application.handler.restauranteempleado;


import com.pragma.powerup.application.dto.restauranteempleado.request.RestauranteEmpleadoRequestDto;
import com.pragma.powerup.application.dto.restauranteempleado.response.RestauranteEmpleadoResponseDto;

public interface IRestauranteEmpleadoHandler {
    void saveRestauranteEmpleado(RestauranteEmpleadoRequestDto restauranteEmpleadoRequestDto);


    RestauranteEmpleadoResponseDto findById(Long id);
}
