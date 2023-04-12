package com.pragma.powerup.domain.spi.pedido;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface IPedidoPersistencePort {
    Pedido savePedido(Pedido pedido);
    Pedido findById(Long id);
    Pedido findPedidoCliente(Long idCliente, Estados estado);
}
