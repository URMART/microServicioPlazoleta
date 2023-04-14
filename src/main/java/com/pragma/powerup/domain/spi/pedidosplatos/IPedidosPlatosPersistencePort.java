package com.pragma.powerup.domain.spi.pedidosplatos;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.PedidosPlatos;

import java.util.List;

public interface IPedidosPlatosPersistencePort {
    void savePedidosPlatos(PedidosPlatos pedidosPlatos);

    PedidosPlatos findById(Long id);
    List<PedidosPlatos> findAll(Long idPedido);
    List<PedidosPlatos> findAllPedidosPendientesPaginados(int page, int size, Estados estado, Long idRestaurante);
    void DeletePedidoPlato(Long idPedido);
}
