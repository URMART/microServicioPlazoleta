package com.pragma.powerup.application.mapper.restaurenteempleado;


import com.pragma.powerup.application.dto.restauranteempleado.response.RestauranteEmpleadoResponseDto;

import com.pragma.powerup.domain.model.RestauranteEmpleado;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteEmpleadoResponseMapper {


    RestauranteEmpleadoResponseDto toRestauranteEmpleadoDto(RestauranteEmpleado restauranteEmpleadoModel);
    RestauranteEmpleado toRestauranteEmpleadoModel(RestauranteEmpleadoResponseDto restauranteEmpleadoResponseDto);
}
