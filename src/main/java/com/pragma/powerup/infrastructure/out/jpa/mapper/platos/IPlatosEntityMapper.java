package com.pragma.powerup.infrastructure.out.jpa.mapper.platos;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IPlatosEntityMapper {

    PlatosEntity toPlatosEntity(Platos platos);
    Platos toPlatosModel(PlatosEntity  platosEntity);
    List<Platos> toPlatosModelList(List<PlatosEntity> platosEntityList);
    List<Platos> toPlatoModelListPa(Page<PlatosEntity> platosEntityListPage);
}
