package com.pragma.powerup.application.mapper.restaurante;

import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseClienteDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteResponseMapper {
    RestauranteResponseDto toRestauranteDto(Restaurante responseModel);
    Restaurante toRestauranteModel(RestauranteResponseDto restauranteResponseDto);

    List<RestauranteResponseDto> toRestauranteDtoList(List<Restaurante> restauranteModelList);

    List<RestauranteResponseClienteDto> toRestauranteClienteDtoList(List<Restaurante> restauranteModelList);




}
