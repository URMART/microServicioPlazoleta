package com.pragma.powerup.domain.api.pedidosplatos;

import com.pragma.powerup.domain.model.PedidosPlatos;

import java.util.List;

public interface IPedidosPlatosServicePort {

    void savePedidosPlatos(PedidosPlatos pedidosPlatos);

    PedidosPlatos findById(Long id);
    List<PedidosPlatos> findAll();



}
