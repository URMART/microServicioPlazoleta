package com.pragma.powerup.infrastructure.out.jpa.adapter.pedido;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.spi.pedido.IPedidoPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.pedido.IPedidoEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.pedido.IPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PedidoJpaAdapter implements IPedidoPersistencePort {

    private final IPedidoEntityMapper pedidoEntityMapper;
    private final IPedidoRepository pedidoRepository;

    @Override
    public Pedido savePedido(Pedido pedido) {

       PedidoEntity pedidoPrueba = pedidoRepository.save(pedidoEntityMapper.toPedidoEntity(pedido));
        System.out.println("pedidoPrueba  en adapter= " + pedidoPrueba);
        return pedidoEntityMapper.toPedidoModel(pedidoPrueba);
    }

    @Override
    public Pedido findById(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow();
        return pedidoEntityMapper.toPedidoModel(pedido);
    }

    @Override
    public Pedido findPedidoCliente(Long idCliente, Estados estado) {
        return pedidoEntityMapper.toPedidoModel(pedidoRepository.findPedidoCliente(idCliente, estado));
    }

    @Override
    public List<Pedido> findAllPedidosPendientesPaginados(
            int page, int size, Estados estado, Long idRestaurante, Long idChef) {
        Pageable pageable = PageRequest.of(page, size);

        return pedidoEntityMapper
                .toPedidoModelListPage(
                        pedidoRepository.findAllPedidosPendientesPaginados(estado,idRestaurante,idChef,pageable)
                );
    }

    @Override
    public void DeletePedido(Long idPedido) {
        pedidoRepository.deleteById(idPedido);
    }


}
