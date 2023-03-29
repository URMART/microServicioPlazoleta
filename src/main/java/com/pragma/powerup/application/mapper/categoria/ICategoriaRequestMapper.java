package com.pragma.powerup.application.mapper.categoria;

import com.pragma.powerup.application.dto.categoria.request.CategoriaRequestDto;
import com.pragma.powerup.application.dto.restaurante.request.RestauranteRequestDto;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoriaRequestMapper {
    Categoria toCategoria(CategoriaRequestDto categoriaRequestDto);
}
