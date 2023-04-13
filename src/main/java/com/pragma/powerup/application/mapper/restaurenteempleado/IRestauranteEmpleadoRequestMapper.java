package com.pragma.powerup.application.mapper.restaurenteempleado;

import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.application.dto.restauranteempleado.request.RestauranteEmpleadoRequestDto;
import com.pragma.powerup.domain.model.RestauranteEmpleado;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteEmpleadoRequestMapper {
    RestauranteEmpleado toRestauranteEmpleado(RestauranteEmpleadoRequestDto restauranteEmpleadoRequestDto);
}
