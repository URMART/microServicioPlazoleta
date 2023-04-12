package com.pragma.powerup.application.dto.pedidoplatos.request;

import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Platos;
import lombok.Data;

@Data
public class PedidoPlatoRequestDto {
    private Long idPedidosPlatos;
    private Pedido idPedido;
    private Platos idPlato;
    private Integer cantidad;


}
