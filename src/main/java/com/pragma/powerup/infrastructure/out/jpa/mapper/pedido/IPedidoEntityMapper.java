package com.pragma.powerup.infrastructure.out.jpa.mapper.pedido;


import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IPedidoEntityMapper {

    PedidoEntity toPedidoEntity(Pedido pedido);
    Pedido toPedidoModel(PedidoEntity  entity);
    List<Pedido> toPedidoModelList(List<PedidoEntity> pedidoEntityList);

    List<Pedido> toPedidoModelListPage(Page<PedidoEntity> pedidoEntityPageList);
}
