package com.pragma.powerup.infrastructure.out.jpa.mapper.categoria;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICategoriaEntityMapper {

    CategoriaEntity toCategoriaEntity(Categoria categoria);
    Categoria toCategoriaModel(CategoriaEntity  categoriaEntity);
    List<Categoria> toCategoriaModelList(List<CategoriaEntity> categoriaEntityList);
}
