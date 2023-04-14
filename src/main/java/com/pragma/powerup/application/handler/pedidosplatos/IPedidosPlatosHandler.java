package com.pragma.powerup.application.handler.pedidosplatos;

import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestDto;
import com.pragma.powerup.application.dto.pedidoplatos.response.PedidoPlatoResponseDto;
import com.pragma.powerup.domain.model.Estados;
import java.util.List;

public interface IPedidosPlatosHandler {
    void savePedidosPlatos(PedidoPlatoRequestDto pedido);

    PedidoPlatoResponseDto findById(Long id);

    List<PedidoPlatoResponseDto> findAll(Long idPedido);
    List<PedidoPlatoResponseDto> findAllPedidosPendientesPaginados(int page, int size, Estados estado, Long idRestaurante);
    void eliminarPedidoPlato(Long idPedido);
}
