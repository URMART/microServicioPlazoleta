package com.pragma.powerup.infrastructure.out.jpa.mapper.pedidosplatos;


import com.pragma.powerup.domain.model.PedidosPlatos;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidosPlatosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IPedidosPlatosMapper {

    PedidosPlatosEntity toPedidoPlatosEntity(PedidosPlatos pedidosPlatos);
    PedidosPlatos toPedidosPlatosModel(PedidosPlatosEntity  pedidosPlatosEntity);
    List<PedidosPlatos> toPedidosPlatosModelList(List<PedidosPlatosEntity> pedidosPlatosEntityList);

    List<PedidosPlatos> toPedidoPlatosModelListPage(Page<PedidosPlatosEntity> pedidosPlatosEntityPage);
}
