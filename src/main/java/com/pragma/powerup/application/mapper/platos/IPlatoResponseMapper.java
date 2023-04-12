package com.pragma.powerup.application.mapper.platos;


import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.domain.model.Platos;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoResponseMapper {

    PlatoResponseDto toPlatosDto(Platos plato);

    List<PlatoResponseDto> toPlatosDtoList(List<Platos> platosModelList);

    Platos toPlatoModel(PlatoResponseDto platoResponseDto);
}
