package com.pragma.powerup.application.handler.pedido.imp;

import com.pragma.powerup.application.dto.pedido.request.PedidoRequestDto;
import com.pragma.powerup.application.dto.pedido.response.PedidoResponseDto;

import com.pragma.powerup.application.handler.pedido.IPedidosHandler;
import com.pragma.powerup.application.mapper.pedido.IPedidoRequestMapper;
import com.pragma.powerup.application.mapper.pedido.IPedidoResponseMapper;

import com.pragma.powerup.domain.api.pedido.IPedidoServicePort;

import com.pragma.powerup.domain.model.Estados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoHandlerImp implements IPedidosHandler {

    private final IPedidoServicePort pedidoServicePort;
    private final IPedidoRequestMapper pedidoRequestMapper;
    private final IPedidoResponseMapper pedidoResponseMapper;



    @Override
    public PedidoResponseDto savePedido(PedidoRequestDto pedidoRequestDto) {
        System.out.println("pedidoRequestDto en handler = " + pedidoRequestDto);
        return  pedidoResponseMapper.
                toPedidoDto(pedidoServicePort.savePedido(pedidoRequestMapper.toPedido(pedidoRequestDto)));
    }

    @Override
    public PedidoResponseDto findPedidoCliente(Long idCliente, Estados estado) {
        return pedidoResponseMapper.toPedidoDto(pedidoServicePort.findPedidoCliente(idCliente,estado));
    }

    @Override
    public PedidoResponseDto findById(Long id) {
        return pedidoResponseMapper.toPedidoDto(pedidoServicePort.findById(id));
    }
}