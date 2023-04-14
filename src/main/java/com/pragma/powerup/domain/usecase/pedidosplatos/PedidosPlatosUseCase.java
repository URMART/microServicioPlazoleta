package com.pragma.powerup.domain.usecase.pedidosplatos;

import com.pragma.powerup.domain.api.pedidosplatos.IPedidosPlatosServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.PedidosPlatos;
import com.pragma.powerup.domain.spi.pedidosplatos.IPedidosPlatosPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;

import java.util.List;

public class PedidosPlatosUseCase implements IPedidosPlatosServicePort {


    private final IPedidosPlatosPersistencePort platosPersistencePort;

    public PedidosPlatosUseCase(IPedidosPlatosPersistencePort platosPersistencePort) {
        this.platosPersistencePort = platosPersistencePort;
    }

    @Override
    public void savePedidosPlatos(PedidosPlatos pedidosPlatos) {

        Long idPedido = pedidosPlatos.getIdPedido().getId();
        Long idPlato =  pedidosPlatos.getIdPlato().getId();
        Integer cantidad = pedidosPlatos.getCantidad();

        if(pedidosPlatos.getIdPedido().getId() == null && pedidosPlatos.getIdPlato().getId() == null &&
                pedidosPlatos.getCantidad() == null) {
            throw  new DomainException("No hay Datos sufucientes ");
        }
        platosPersistencePort.savePedidosPlatos(pedidosPlatos);


    }

    @Override
    public PedidosPlatos findById(Long id) {
        return platosPersistencePort.findById(id);
    }

    @Override
    public List<PedidosPlatos> findAll(Long idPedido) {
        return platosPersistencePort.findAll(idPedido);
    }

    @Override
    public List<PedidosPlatos> findAllPedidosPendientesPaginados(int page, int size, Estados estado, Long idRestaurante) {
        return platosPersistencePort.findAllPedidosPendientesPaginados(page, size, estado, idRestaurante);
    }

    @Override
    public void DeletePedidoPlato(Long idPedido) {
        platosPersistencePort.DeletePedidoPlato(idPedido);
    }
}
