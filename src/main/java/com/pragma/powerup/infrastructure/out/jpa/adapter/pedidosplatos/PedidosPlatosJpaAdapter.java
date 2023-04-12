package com.pragma.powerup.infrastructure.out.jpa.adapter.pedidosplatos;

import com.pragma.powerup.domain.model.PedidosPlatos;
import com.pragma.powerup.domain.spi.pedidosplatos.IPedidosPlatosPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.pedidosplatos.IPedidosPlatosMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.pedidosplatos.PedidosPlatosRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class PedidosPlatosJpaAdapter implements IPedidosPlatosPersistencePort {

    private final IPedidosPlatosMapper pedidosPlatosMapper;

    private final PedidosPlatosRepository pedidosPlatosRepository;


    @Override
    public void savePedidosPlatos(PedidosPlatos pedidosPlatos) {
        System.out.println("pedidosPlatos  estoy en el adapter = " + pedidosPlatos);

       pedidosPlatosRepository.save(pedidosPlatosMapper.toPedidoPlatosEntity(pedidosPlatos));

        System.out.println("pedidosPlatosMapper.toPedidoPlatosEntity(pedidosPlatos) = " + pedidosPlatosMapper.toPedidoPlatosEntity(pedidosPlatos));
        
    }





    @Override
    public PedidosPlatos findById(Long id) {
        return pedidosPlatosMapper.toPedidosPlatosModel(pedidosPlatosRepository.findById(id).orElseThrow()) ;
    }

    @Override
    public List<PedidosPlatos> findAll() {
        return pedidosPlatosMapper.toPedidosPlatosModelList(pedidosPlatosRepository.findAll());
    }
}
