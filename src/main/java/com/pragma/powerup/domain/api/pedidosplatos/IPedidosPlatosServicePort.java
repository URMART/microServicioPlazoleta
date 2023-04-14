package com.pragma.powerup.domain.api.pedidosplatos;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.PedidosPlatos;

import java.util.List;

public interface IPedidosPlatosServicePort {

    void savePedidosPlatos(PedidosPlatos pedidosPlatos);

    PedidosPlatos findById(Long id);
    List<PedidosPlatos> findAll(Long idPedido);

    List<PedidosPlatos> findAllPedidosPendientesPaginados(int page, int size, Estados estado, Long idRestaurante);

    void DeletePedidoPlato(Long idPedido);



}
