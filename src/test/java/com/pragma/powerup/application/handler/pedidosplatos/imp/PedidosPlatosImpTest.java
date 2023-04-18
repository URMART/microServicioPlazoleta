package com.pragma.powerup.application.handler.pedidosplatos.imp;

import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestDto;
import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestGuardar;
import com.pragma.powerup.application.dto.pedidoplatos.response.PedidoPlatoResponseDto;
import com.pragma.powerup.application.handler.factory.FactoryPedidoData;
import com.pragma.powerup.application.handler.factory.FactoryPedidosPlatosData;
import com.pragma.powerup.application.mapper.pedidoplatos.IPedidoPlatosRequestMapper;
import com.pragma.powerup.application.mapper.pedidoplatos.IPedidoPlatosResponseMapper;
import com.pragma.powerup.domain.api.pedidosplatos.IPedidosPlatosServicePort;
import com.pragma.powerup.domain.model.*;
import org.joda.time.DateTime;
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
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidosPlatosImpTest {
    @InjectMocks
    private PedidosPlatosImp pedidosPlatosImp;
    @Mock
    private  IPedidosPlatosServicePort pedidosPlatosServicePort;
    @Mock
    private  IPedidoPlatosResponseMapper pedidoPlatosResponseMapper;
    @Mock
    private  IPedidoPlatosRequestMapper pedidoPlatosRequestMapper;

    private PedidoPlatoRequestDto pedidoPlatoRequestDto;

    private PedidoPlatoResponseDto pedidoPlatoResponseDto;

    private Pedido pedido;
    private Platos plato;
    private PedidosPlatos pedidosPlatos;

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = FactoryPedidoData.getRestauranteData();
        pedido = FactoryPedidosPlatosData.getPedidoData();
        plato = FactoryPedidosPlatosData.getPlatoData();
        pedidosPlatos = FactoryPedidosPlatosData.getPedidosPlatosData();
        pedidoPlatoRequestDto = FactoryPedidosPlatosData.pedidoPlatoRequestDtoData();
        pedidoPlatoResponseDto = FactoryPedidosPlatosData.pedidoPlatoResponseDtoData();


    }
    @Test
    void savePedidosPlatos() {
        when(pedidoPlatosRequestMapper.toPedidosPlatos(pedidoPlatoRequestDto)).thenReturn(pedidosPlatos);

        pedidosPlatosImp.savePedidosPlatos(pedidoPlatoRequestDto);

        verify(pedidosPlatosServicePort,timeout(1)).savePedidosPlatos(pedidosPlatos);
    }

    @Test
    void findById() {

        when(pedidoPlatosResponseMapper.toPedidoPlatosDto(pedidosPlatos)).thenReturn(pedidoPlatoResponseDto);
        when(pedidosPlatosServicePort.findById(1L)).thenReturn(pedidosPlatos);

        PedidoPlatoResponseDto pedidoPlatoResponseDtoEncontrado = pedidosPlatosImp.findById(1L);

        assertEquals(pedidoPlatoResponseDtoEncontrado.getIdPedidosPlatos(),pedidosPlatos.getIdPedidosPlatos());



    }

    @Test
    void findAll() {

        List<PedidosPlatos> pedidosPlatosList = new ArrayList<>();
        pedidosPlatosList.add(pedidosPlatos);

        List<PedidoPlatoResponseDto> pedidosPlatosListDto= new ArrayList<>();
        pedidosPlatosListDto.add(pedidoPlatoResponseDto);

        when(pedidoPlatosResponseMapper
                .toPedidoPlatoResponseDtoList(pedidosPlatosList)).thenReturn(pedidosPlatosListDto);

        when(pedidosPlatosServicePort
                .findAll(pedidoPlatoRequestDto.getIdPedidosPlatos())).thenReturn(pedidosPlatosList);

        List<PedidoPlatoResponseDto> pedidosPlatosListDtoEncontrado = pedidosPlatosImp
                .findAll(pedidoPlatoRequestDto.getIdPedidosPlatos());

        assertEquals(pedidosPlatosListDtoEncontrado.size(),pedidosPlatosList.size());
    }

    @Test
    void findAllPedidosPendientesPaginados() {
        List<PedidosPlatos> pedidosPlatosList = new ArrayList<>();
        pedidosPlatosList.add(pedidosPlatos);

        List<PedidoPlatoResponseDto> pedidosPlatosListDto= new ArrayList<>();
        pedidosPlatosListDto.add(pedidoPlatoResponseDto);

        when(pedidoPlatosResponseMapper.toPedidoPlatoResponseDtoList(pedidosPlatosList))
                .thenReturn(pedidosPlatosListDto);

        when(pedidosPlatosServicePort
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,restaurante.getId())).thenReturn(pedidosPlatosList);


        List<PedidoPlatoResponseDto> pedidosPlatosListDtoEncontrado= pedidosPlatosImp
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,restaurante.getId());

        assertEquals(pedidosPlatosListDtoEncontrado.size(),pedidosPlatosList.size());
    }

    @Test
    void eliminarPedidoPlato() {
        willDoNothing().given(pedidosPlatosServicePort).DeletePedidoPlato(1L);

        pedidosPlatosImp.eliminarPedidoPlato(1L);

        verify(pedidosPlatosServicePort,times(1)).DeletePedidoPlato(1L);
    }
}