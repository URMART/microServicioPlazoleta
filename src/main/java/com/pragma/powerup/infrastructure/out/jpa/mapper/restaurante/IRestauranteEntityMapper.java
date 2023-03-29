package com.pragma.powerup.infrastructure.out.jpa.mapper.restaurante;

import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestauranteEntityMapper {

    RestauranteEntity toRestauranteEntity(Restaurante restaurante);
    Restaurante toRestauranteModel(RestauranteEntity restauranteEntity);
    List<Restaurante> toRestauranteModelList(List<RestauranteEntity> restauranteEntityList);
}