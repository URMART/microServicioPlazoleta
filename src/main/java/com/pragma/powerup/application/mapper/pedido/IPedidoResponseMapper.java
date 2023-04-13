package com.pragma.powerup.application.mapper.pedido;


import com.pragma.powerup.application.dto.pedido.request.PedidoRequestDto;
import com.pragma.powerup.application.dto.pedido.response.PedidoResponseDto;

import com.pragma.powerup.domain.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoResponseMapper {
    PedidoResponseDto toPedidoDto(Pedido pedido);
    List<PedidoResponseDto> toPedidosDtoList(List<Pedido> pedidosModelList);

    Pedido toPedidoModel(PedidoResponseDto pedidoResponseDto);
    PedidoRequestDto toPedidoRequestDto(PedidoResponseDto pedidoResponseDto);



}
