package com.pragma.powerup.application.dto.pedido.request;

import com.pragma.powerup.domain.model.Estados;
import lombok.Data;

import java.util.List;


@Data
public class PedidoRequestAsignarChefDto {
    private List<Long> idPedido;
    private Estados estado;
}
