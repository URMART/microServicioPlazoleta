package com.pragma.powerup.domain.api.pedido;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;

import java.util.List;
import java.util.Optional;


public interface IPedidoServicePort {
    Pedido savePedido(Pedido pedido);
    Pedido findById(Long id);
    Pedido findPedidoCliente(Long idCliente, Estados estado );
}
