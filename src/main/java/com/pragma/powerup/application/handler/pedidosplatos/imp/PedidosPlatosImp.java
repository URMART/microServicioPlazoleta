package com.pragma.powerup.application.handler.pedidosplatos.imp;

import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestDto;
import com.pragma.powerup.application.dto.pedidoplatos.response.PedidoPlatoResponseDto;
import com.pragma.powerup.application.handler.pedidosplatos.IPedidosPlatosHandler;
import com.pragma.powerup.application.mapper.pedidoplatos.IPedidoPlatosRequestMapper;
import com.pragma.powerup.application.mapper.pedidoplatos.IPedidoPlatosResponseMapper;
import com.pragma.powerup.domain.api.pedidosplatos.IPedidosPlatosServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class PedidosPlatosImp implements IPedidosPlatosHandler {

    private final IPedidosPlatosServicePort pedidosPlatosServicePort;
    private final IPedidoPlatosResponseMapper pedidoPlatosResponseMapper;
    private final IPedidoPlatosRequestMapper pedidoPlatosRequestMapper;

//mirar por este lado
    @Override
    public void savePedidosPlatos(PedidoPlatoRequestDto pedido) {
        pedidosPlatosServicePort.savePedidosPlatos(pedidoPlatosRequestMapper.toPedidosPlatos(pedido));
    }

    @Override
    public PedidoPlatoResponseDto findById(Long id) {
        return pedidoPlatosResponseMapper.toPedidoPlatosDto(pedidosPlatosServicePort.findById(id));
    }

    @Override
    public List<PedidoPlatoResponseDto> findAll() {
        return pedidoPlatosResponseMapper.toPedidoPlatoResponseDtoList(pedidosPlatosServicePort.findAll());
    }
}
