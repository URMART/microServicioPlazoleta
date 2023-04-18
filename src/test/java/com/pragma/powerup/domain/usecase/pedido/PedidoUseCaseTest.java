package com.pragma.powerup.domain.usecase.pedido;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.domain.spi.pedido.IPedidoPersistencePort;
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


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidoUseCaseTest {
    @InjectMocks
    private  PedidoUseCase pedidoUseCase;
    @Mock
    private IPedidoPersistencePort pedidoPersistencePort;



    private Restaurante restaurante;
    private Pedido pedido;
    @BeforeEach
    void setUp() {

        restaurante = new Restaurante();
        restaurante.setId(1L);

        pedido=new Pedido(1L, LocalDateTime.now(), Estados.PENDIENTE,restaurante,1L,1L);
    }

    @Test
    void savePedido() {
        when(pedidoPersistencePort.savePedido(pedido)).thenReturn(pedido);

        Pedido pedidoARetornar = pedidoUseCase.savePedido(pedido);

        verify(pedidoPersistencePort).savePedido(pedido);

    }

    @Test
    void findById() {

        when(pedidoPersistencePort.findById(1L)).thenReturn(pedido);

        Pedido pedidoEncontrado = pedidoUseCase.findById(1L);

        assertEquals(pedidoEncontrado.getId(),pedido.getId());
        verify(pedidoPersistencePort).findById(pedido.getId());
    }

    @Test
    void findPedidoCliente() {
        when(pedidoPersistencePort.findPedidoCliente(1L,Estados.PENDIENTE)).thenReturn(pedido);

        Pedido pedidoEncontrado = pedidoUseCase.findPedidoCliente(1L,Estados.PENDIENTE);

        assertEquals(pedidoEncontrado.getIdCliente(),pedido.getIdCliente());
    }

    @Test
    void findAllPedidosPendientesPaginados() {

        List<Pedido> pedidosList = new ArrayList<>();
        pedidosList.add(pedido);

        when(pedidoPersistencePort
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,1L,1L))
                .thenReturn(pedidosList);

        List<Pedido> pedidosEncontrados = pedidoUseCase
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,1L,1L);

        assertEquals(pedidosEncontrados.size(),pedidosList.size());
    }

    @Test
    void deletePedido() {
        willDoNothing().given(pedidoPersistencePort).DeletePedido(1L);

        pedidoUseCase.DeletePedido(1L);

        verify(pedidoPersistencePort,times(1)).DeletePedido(1L);
    }
}