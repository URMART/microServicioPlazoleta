package com.pragma.powerup.application.mapper.platos;

import com.pragma.powerup.application.dto.plato.request.PlatoPutRequestDto;
import com.pragma.powerup.application.dto.plato.request.PlatoRequestDto;
import com.pragma.powerup.domain.model.Platos;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoRequestMapper {

    Platos toPlatos(PlatoRequestDto platoRequestDto);
    Platos toPlatos(PlatoPutRequestDto platoPutRequestDto);
    PlatoPutRequestDto toPlatoPutRequestDto(Platos platos);
}
