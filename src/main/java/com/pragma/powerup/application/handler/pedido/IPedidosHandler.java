package com.pragma.powerup.application.handler.pedido;

import com.pragma.powerup.application.dto.pedido.request.PedidoRequestDto;
import com.pragma.powerup.application.dto.pedido.response.PedidoResponseDto;
import com.pragma.powerup.domain.model.Estados;


import java.util.List;


public interface IPedidosHandler {

    PedidoResponseDto savePedido(PedidoRequestDto pedidoRequestDto);

    PedidoResponseDto findPedidoCliente(Long idCliente, Estados estado);

    PedidoResponseDto  findById(Long id);

    List<PedidoResponseDto> findAllPedidosPendientesPaginados(int page, int size, Estados estado, Long idRestaurante,Long idChef);

    void DeletePedido(Long idPedido);
}
