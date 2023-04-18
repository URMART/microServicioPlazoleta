package com.pragma.powerup.application.handler.factory;

import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseClienteDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;

public class FactoryRestauranteData {



    public  static Restaurante restauranteData(){
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNombre("el manjar");
        restaurante.setDireccion("cr6a 55");
        restaurante.setIdPropietario(1L);
        restaurante.setTelefono("2233223");
        restaurante.setUrlLogo("www.logo.com");
        restaurante.setNit(22343L);

        return  restaurante;
    }

    public  static RestauranteResponseDto restauranteResponseDtoData(){
        RestauranteResponseDto  restauranteResponseDto = new RestauranteResponseDto();
        restauranteResponseDto.setId(1L);
        restauranteResponseDto.setNombre("el manjar");
        restauranteResponseDto.setDireccion("cr6a 55");
        restauranteResponseDto.setIdPropietario(1L);
        restauranteResponseDto.setTelefono("2233223");
        restauranteResponseDto.setUrlLogo("www.logo.com");
        restauranteResponseDto.setNit(22343L);


        return restauranteResponseDto;
    }

    public  static RestauranteRequestDto restauranteRequestDtoData(){

        RestauranteRequestDto  restauranteRequestDto = new RestauranteRequestDto();
        restauranteRequestDto.setId(1L);
        restauranteRequestDto.setNombre("el manjar");
        restauranteRequestDto.setDireccion("cr6a 55");
        restauranteRequestDto.setIdPropietario(1L);
        restauranteRequestDto.setTelefono("2233223");
        restauranteRequestDto.setUrlLogo("www.logo.com");
        restauranteRequestDto.setNit(22343L);


        return  restauranteRequestDto;
    }



    public static  RestauranteResponseClienteDto restauranteResponseClienteDtoData(){

        RestauranteResponseClienteDto restauranteResponseClienteDto = new RestauranteResponseClienteDto();
        restauranteResponseClienteDto.setNombre("el manjar");
        restauranteResponseClienteDto.setUrlLogo("www.logo.com");

        return restauranteResponseClienteDto;
    }

    public  static RestauranteEntity restauranteEntityData(){
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setId(1L);
        restaurante.setNombre("el manjar");
        restaurante.setDireccion("cr6a 55");
        restaurante.setIdPropietario(1L);
        restaurante.setTelefono("2233223");
        restaurante.setUrlLogo("www.logo.com");
        restaurante.setNit(22343L);

        return  restaurante;
    }
}
