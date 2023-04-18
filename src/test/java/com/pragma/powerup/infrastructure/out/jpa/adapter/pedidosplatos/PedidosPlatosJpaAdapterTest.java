package com.pragma.powerup.infrastructure.out.jpa.adapter.pedidosplatos;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.PedidosPlatos;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidosPlatosEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.pedidosplatos.IPedidosPlatosMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.pedidosplatos.PedidosPlatosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.BDDMockito.willDoNothing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidosPlatosJpaAdapterTest {


    @Mock
    private  IPedidosPlatosMapper pedidosPlatosMapper;

    @Mock
    private  PedidosPlatosRepository pedidosPlatosRepository;

    @InjectMocks
    private  PedidosPlatosJpaAdapter pedidosPlatosJpaAdapter;


    private Pedido pedido;
    private Platos plato;

    private PedidoEntity pedidoEntity;
    private PlatosEntity platoEntity;

    private PedidosPlatos pedidosPlatos;
    private PedidosPlatosEntity pedidosPlatosEntity;


    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);

        pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(1L);

        plato = new Platos();
        plato.setId(1L);

        platoEntity = new PlatosEntity();
        platoEntity.setId(1L);

        pedidosPlatos = new PedidosPlatos();
        pedidosPlatos.setIdPedidosPlatos(1L);
        pedidosPlatos.setIdPedido(pedido);
        pedidosPlatos.setIdPlato(plato);
        pedidosPlatos.setCantidad(1);

        pedidosPlatosEntity = new PedidosPlatosEntity();
        pedidosPlatosEntity.setIdPedidosPlatos(1L);
        pedidosPlatosEntity.setIdPedido(pedidoEntity);
        pedidosPlatosEntity.setIdPlato(platoEntity);
        pedidosPlatosEntity.setCantidad(1);




    }

    @Test
    void savePedidosPlatos() {

        when(pedidosPlatosRepository.save(pedidosPlatosEntity)).thenReturn(pedidosPlatosEntity);
        when(pedidosPlatosMapper.toPedidoPlatosEntity(pedidosPlatos)).thenReturn(pedidosPlatosEntity);


        pedidosPlatosJpaAdapter.savePedidosPlatos(pedidosPlatos);

        verify(pedidosPlatosRepository,timeout(1)).save(pedidosPlatosEntity);

    }

    @Test
    void findById() {

        when(pedidosPlatosMapper.toPedidosPlatosModel(pedidosPlatosEntity)).thenReturn(pedidosPlatos);
        when(pedidosPlatosRepository.findById(1L)).thenReturn(Optional.of(pedidosPlatosEntity));

        Optional<PedidosPlatos> pedidosPlatoEncontrado = Optional.of(pedidosPlatosJpaAdapter.findById(1L));

        assertEquals(pedidosPlatoEncontrado.get().getIdPedidosPlatos(),pedidosPlatos.getIdPedidosPlatos());
    }

    @Test
    void findAll() {
        List<PedidosPlatosEntity> pedidosPlatosEntityList = new ArrayList<>();
        pedidosPlatosEntityList.add(pedidosPlatosEntity);

        List<PedidosPlatos> pedidosPlatosList = new ArrayList<>();
        pedidosPlatosList.add(pedidosPlatos);

        when(pedidosPlatosRepository.findAll(pedido.getId())).thenReturn(pedidosPlatosEntityList);
        when(pedidosPlatosMapper.toPedidosPlatosModelList(pedidosPlatosEntityList)).thenReturn(pedidosPlatosList);


        List<PedidosPlatos> pedidosPlatosEncontrados = pedidosPlatosJpaAdapter.findAll(pedido.getId());

        assertEquals(pedidosPlatosEncontrados.size(),pedidosPlatosList.size());
    }

    @Test
    void findAllPedidosPendientesPaginados() {

        List<PedidosPlatosEntity> pedidosPlatosEntityList = new ArrayList<>();
        pedidosPlatosEntityList.add(pedidosPlatosEntity);

        List<PedidosPlatos> pedidosPlatosList = new ArrayList<>();
        pedidosPlatosList.add(pedidosPlatos);

        Pageable pageable = PageRequest.of(0, 4);

        Page<PedidosPlatosEntity> pedidoPlatosEntityListPage = new PageImpl<>(pedidosPlatosEntityList);

        when(pedidosPlatosRepository
                .findAllPedidosPendientesPaginados(Estados.PENDIENTE,1L,pageable))
                .thenReturn(pedidoPlatosEntityListPage);

        when(pedidosPlatosMapper.toPedidoPlatosModelListPage(pedidoPlatosEntityListPage))
                .thenReturn(pedidosPlatosList);

        List<PedidosPlatos> pedidosPlatosEncontrados = pedidosPlatosJpaAdapter
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,1L);

        assertEquals(pedidosPlatosEncontrados.size(),pedidosPlatosList.size());


    }

    @Test
    void deletePedidoPlato() {

        willDoNothing().given(pedidosPlatosRepository).deleteById(1L);

        pedidosPlatosJpaAdapter.DeletePedidoPlato(1L);

        verify(pedidosPlatosRepository,times(1)).deleteById(1L);
    }
}