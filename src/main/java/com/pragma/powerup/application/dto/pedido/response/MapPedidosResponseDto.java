package com.pragma.powerup.application.dto.pedido.response;

import com.pragma.powerup.application.dto.pedidoplatos.response.PedidoPlatoResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class MapPedidosResponseDto {


    private PedidoResponseDto pedido;
    private List<PedidoPlatoResponseDto> plato;
}
