package com.pragma.powerup.infrastructure.out.jpa.adapter.pedidosplatos;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.PedidosPlatos;
import com.pragma.powerup.domain.spi.pedidosplatos.IPedidosPlatosPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.pedidosplatos.IPedidosPlatosMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.pedidosplatos.PedidosPlatosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
@RequiredArgsConstructor
public class PedidosPlatosJpaAdapter implements IPedidosPlatosPersistencePort {

    private final IPedidosPlatosMapper pedidosPlatosMapper;

    private final PedidosPlatosRepository pedidosPlatosRepository;


    @Override
    public void savePedidosPlatos(PedidosPlatos pedidosPlatos) {
       pedidosPlatosRepository.save(pedidosPlatosMapper.toPedidoPlatosEntity(pedidosPlatos));

    }


    @Override
    public PedidosPlatos findById(Long id) {
        return pedidosPlatosMapper.toPedidosPlatosModel(pedidosPlatosRepository.findById(id).orElseThrow()) ;
    }

    @Override
    public List<PedidosPlatos> findAll(Long idPedido) {
        return pedidosPlatosMapper.toPedidosPlatosModelList(pedidosPlatosRepository.findAll(idPedido));
    }

    @Override
    public List<PedidosPlatos> findAllPedidosPendientesPaginados(int page, int size, Estados estado, Long idRestaurante)
    {
        Pageable pageable = PageRequest.of(page, size);

        return pedidosPlatosMapper.toPedidoPlatosModelListPage(
                pedidosPlatosRepository.findAllPedidosPendientesPaginados(estado, idRestaurante, pageable)
        );
    }

    @Override
    public void DeletePedidoPlato(Long idPedido) {
        pedidosPlatosRepository.deleteById(idPedido);
    }
}
