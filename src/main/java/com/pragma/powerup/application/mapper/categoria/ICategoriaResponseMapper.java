package com.pragma.powerup.application.mapper.categoria;

import com.pragma.powerup.application.dto.categoria.response.CategoriaResponseDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoriaResponseMapper {
    CategoriaResponseDto toCategoriaDto(Categoria categoriaModel);
    Categoria toCategoriaModel(CategoriaResponseDto categoriaResponseDto);
    List<CategoriaResponseDto> toCategoriaDtoList(List<Categoria> categoriaModelList);
}
