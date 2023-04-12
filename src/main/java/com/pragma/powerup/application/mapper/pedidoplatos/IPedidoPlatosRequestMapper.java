package com.pragma.powerup.application.mapper.pedidoplatos;

import com.pragma.powerup.application.dto.pedido.request.PedidoRequestDto;
import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestDto;
import com.pragma.powerup.domain.model.PedidosPlatos;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoPlatosRequestMapper {

    PedidosPlatos toPedidosPlatos(PedidoPlatoRequestDto pedidoPlatoRequestDto);

}
