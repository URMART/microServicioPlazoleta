package com.pragma.powerup.application.handler.pedido.imp;

import com.pragma.powerup.application.dto.pedido.request.PedidoRequestDto;
import com.pragma.powerup.application.dto.pedido.response.PedidoResponseDto;
import com.pragma.powerup.application.handler.factory.FactoryPedidoData;
import com.pragma.powerup.application.mapper.pedido.IPedidoRequestMapper;
import com.pragma.powerup.application.mapper.pedido.IPedidoResponseMapper;
import com.pragma.powerup.domain.api.pedido.IPedidoServicePort;
import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Restaurante;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidoHandlerImpTest {

    @InjectMocks
    private  PedidoHandlerImp pedidoHandlerImp;
    @Mock
    private IPedidoServicePort pedidoServicePort;
    @Mock
    private  IPedidoRequestMapper pedidoRequestMapper;
    @Mock
    private  IPedidoResponseMapper pedidoResponseMapper;

    private PedidoResponseDto pedidoResponseDto;
    private PedidoRequestDto pedidoRequestDto;
    private Pedido pedido;
    private Restaurante restaurante;
    @BeforeEach
    void setUp() {
        restaurante = FactoryPedidoData.getRestauranteData();
        pedido= FactoryPedidoData.getPedidoData();
        pedidoResponseDto = FactoryPedidoData.getPedidoResponseDtoData();
        pedidoRequestDto = FactoryPedidoData.getPedidoRequestDtoData();

    }

    @Test
    void savePedido() {

        when(pedidoResponseMapper.toPedidoDto(pedido)).thenReturn(pedidoResponseDto);
        when(pedidoRequestMapper.toPedido(pedidoRequestDto)).thenReturn(pedido);
        when(pedidoServicePort.savePedido(pedido)).thenReturn(pedido);

        PedidoResponseDto pedidoARetornar = pedidoHandlerImp.savePedido(pedidoRequestDto);

        assertEquals(pedidoARetornar.getId(),pedido.getId());
    }

    @Test
    void findPedidoCliente() {


        when(pedidoServicePort.findPedidoCliente(1L,Estados.PENDIENTE)).thenReturn(pedido);
        when(pedidoResponseMapper.toPedidoDto(pedido)).thenReturn(pedidoResponseDto);
        PedidoResponseDto pedidoEncontrado = pedidoHandlerImp.findPedidoCliente(1L,Estados.PENDIENTE);

        assertEquals(pedidoEncontrado.getId(),pedido.getId());

    }

    @Test
    void findById() {

        when(pedidoResponseMapper.toPedidoDto(pedido)).thenReturn(pedidoResponseDto);
        when(pedidoServicePort.findById(1L)).thenReturn(pedido);

        PedidoResponseDto pedidoEncontrado = pedidoHandlerImp.findById(1L);

        assertEquals(pedidoEncontrado.getId(),pedido.getId());
    }

    @Test
    void findAllPedidosPendientesPaginados() {

        List<Pedido> pedidosList = new ArrayList<>();
        pedidosList.add(pedido);

        List<PedidoResponseDto> pedidoResponseDtosList = new ArrayList<>();
        pedidoResponseDtosList.add(pedidoResponseDto);


        when(pedidoServicePort
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,restaurante.getId(),1L))
                .thenReturn(pedidosList);

        when(pedidoResponseMapper.toPedidosDtoList(pedidosList)).thenReturn(pedidoResponseDtosList);

        List<PedidoResponseDto> pedidoResponseDtosListEncontrado = pedidoHandlerImp
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,restaurante.getId(),1L);

        assertEquals(pedidoResponseDtosListEncontrado.size(),pedidosList.size());

    }

    @Test
    void deletePedido() {
        willDoNothing().given(pedidoServicePort).DeletePedido(1L);

        pedidoHandlerImp.DeletePedido(1L);

        verify(pedidoServicePort,times(1)).DeletePedido(1L);

    }
}