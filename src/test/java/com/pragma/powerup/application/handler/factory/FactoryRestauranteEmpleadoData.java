package com.pragma.powerup.application.handler.factory;

import com.pragma.powerup.application.dto.restauranteempleado.request.RestauranteEmpleadoRequestDto;
import com.pragma.powerup.application.dto.restauranteempleado.response.RestauranteEmpleadoResponseDto;
import com.pragma.powerup.domain.model.RestauranteEmpleado;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEmpleadoEntity;

public class FactoryRestauranteEmpleadoData {

    public static RestauranteEmpleado getRestauranteEmpleadoData(){

        RestauranteEmpleado restauranteEmpleado = new RestauranteEmpleado();
        restauranteEmpleado.setId(1L);
        restauranteEmpleado.setIdRestaurante(1L);
        restauranteEmpleado.setIdEmpleado(1L);


        return  restauranteEmpleado;
    }


    public  static RestauranteEmpleadoRequestDto restauranteEmpleadoRequestDtoData(){
        RestauranteEmpleadoRequestDto  restauranteEmpleadoRequestDto = new RestauranteEmpleadoRequestDto();
        restauranteEmpleadoRequestDto.setIdRestaurante(1L);
        restauranteEmpleadoRequestDto.setIdEmpleado(1L);

        return  restauranteEmpleadoRequestDto;

    }

    public static RestauranteEmpleadoResponseDto restauranteEmpleadoResponseDtoData(){
        RestauranteEmpleadoResponseDto  restauranteEmpleadoResponseDto = new RestauranteEmpleadoResponseDto();
        restauranteEmpleadoResponseDto.setId(1L);
        restauranteEmpleadoResponseDto.setIdRestaurante(1L);
        restauranteEmpleadoResponseDto.setIdEmpleado(1L);
        return  restauranteEmpleadoResponseDto;
    }

    public static RestauranteEmpleadoEntity getRestauranteEmpleadoEntityData(){

        RestauranteEmpleadoEntity restauranteEmpleado = new RestauranteEmpleadoEntity();
        restauranteEmpleado.setId(1L);
        restauranteEmpleado.setIdRestaurante(1L);
        restauranteEmpleado.setIdEmpleado(1L);


        return  restauranteEmpleado;
    }

}
