package com.pragma.powerup.application.mapper.pedidoplatos;

import com.pragma.powerup.application.dto.categoria.response.CategoriaResponseDto;
import com.pragma.powerup.application.dto.pedidoplatos.response.PedidoPlatoResponseDto;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.PedidosPlatos;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoPlatosResponseMapper {

    PedidoPlatoResponseDto toPedidoPlatosDto(PedidosPlatos pedidosPlatos);
    PedidosPlatos toPedidosPlatosModel(PedidoPlatoResponseDto pedidoPlatoResponseDto);
    List<PedidoPlatoResponseDto> toPedidoPlatoResponseDtoList(List<PedidosPlatos> pedidosPlatosaModelList);



}
