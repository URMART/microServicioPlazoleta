package com.pragma.powerup.domain.usecase.pedidosplatos;

import com.pragma.powerup.application.handler.factory.FactoryPedidosPlatosData;
import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.PedidosPlatos;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.spi.pedidosplatos.IPedidosPlatosPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidosPlatosEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidosPlatosUseCaseTest {
    @InjectMocks
    private PedidosPlatosUseCase pedidosPlatosUseCase;
    @Mock
    private IPedidosPlatosPersistencePort pedidosPlatosPersistencePort;

    private Pedido pedido;
    private Platos plato;
    private PedidosPlatos pedidosPlatos;
    @BeforeEach
    void setUp() {
        pedido = FactoryPedidosPlatosData.getPedidoData();
        plato = FactoryPedidosPlatosData.getPlatoData();
        pedidosPlatos =FactoryPedidosPlatosData.getPedidosPlatosData();
    }

    @Test
    void savePedidosPlatos() {


        pedidosPlatosUseCase.savePedidosPlatos(pedidosPlatos);

        verify(pedidosPlatosPersistencePort,timeout(1)).savePedidosPlatos(pedidosPlatos);
    }

    @Test
    void findById() {


        when(pedidosPlatosPersistencePort.findById(1L)).thenReturn((pedidosPlatos));

        PedidosPlatos pedidosPlatoEncontrado = pedidosPlatosUseCase.findById(1L);

        assertEquals(pedidosPlatoEncontrado.getIdPedidosPlatos(),pedidosPlatos.getIdPedidosPlatos());
    }

    @Test
    void findAll() {

        List<PedidosPlatos> pedidosPlatosList = new ArrayList<>();
        pedidosPlatosList.add(pedidosPlatos);

        when(pedidosPlatosPersistencePort.findAll(pedido.getId())).thenReturn(pedidosPlatosList);

        List<PedidosPlatos> pedidosPlatosEncontrados = pedidosPlatosUseCase.findAll(pedido.getId());

        assertEquals(pedidosPlatosEncontrados.size(),pedidosPlatosList.size());

    }

    @Test
    void findAllPedidosPendientesPaginados() {
        List<PedidosPlatos> pedidosPlatosList = new ArrayList<>();
        pedidosPlatosList.add(pedidosPlatos);

        when(pedidosPlatosPersistencePort
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,1L))
                .thenReturn(pedidosPlatosList);


        List<PedidosPlatos> pedidosPlatosEncontrados = pedidosPlatosUseCase
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,1L);

        assertEquals(pedidosPlatosEncontrados.size(),pedidosPlatosList.size());
    }

    @Test
    void deletePedidoPlato() {

        willDoNothing().given(pedidosPlatosPersistencePort).DeletePedidoPlato(1L);

        pedidosPlatosUseCase.DeletePedidoPlato(1L);

        verify(pedidosPlatosPersistencePort,times(1)).DeletePedidoPlato(1L);
    }
}