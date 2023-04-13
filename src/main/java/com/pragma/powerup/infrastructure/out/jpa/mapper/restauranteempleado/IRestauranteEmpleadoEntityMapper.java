package com.pragma.powerup.infrastructure.out.jpa.mapper.restauranteempleado;


import com.pragma.powerup.domain.model.RestauranteEmpleado;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEmpleadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestauranteEmpleadoEntityMapper {


    RestauranteEmpleadoEntity toRestauranteEmpleadoEntity(RestauranteEmpleado restauranteEmpleado);
    RestauranteEmpleado toRestauranteEmpleadoModel(RestauranteEmpleadoEntity restauranteEmpleadoEntity);
}
