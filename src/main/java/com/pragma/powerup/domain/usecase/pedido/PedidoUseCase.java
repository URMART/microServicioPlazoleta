package com.pragma.powerup.domain.usecase.pedido;

import com.pragma.powerup.domain.api.pedido.IPedidoServicePort;
import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.spi.pedido.IPedidoPersistencePort;

public class PedidoUseCase implements IPedidoServicePort {

    private final IPedidoPersistencePort pedidoPersistencePort;

    public PedidoUseCase(IPedidoPersistencePort pedidoPersistencePort) {
        this.pedidoPersistencePort = pedidoPersistencePort;
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        System.out.println("pedido  en use case = " + pedido);
       return pedidoPersistencePort.savePedido(pedido);
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoPersistencePort.findById(id);
    }


    @Override
    public Pedido findPedidoCliente(Long idCliente, Estados estado) {
        return pedidoPersistencePort.findPedidoCliente(idCliente,estado);
    }




}
