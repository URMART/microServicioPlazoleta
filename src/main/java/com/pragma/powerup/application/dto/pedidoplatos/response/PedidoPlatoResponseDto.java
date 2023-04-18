package com.pragma.powerup.application.dto.pedidoplatos.response;

import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Platos;
import lombok.Data;

@Data
public class PedidoPlatoResponseDto {
    private Long idPedidosPlatos;

    private Pedido idPedido;

    private Platos idPlato;
    private Integer cantidad;
}
