package com.pragma.powerup.infrastructure.out.jpa.adapter.pedido;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.pedido.IPedidoEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.pedido.IPedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PedidoJpaAdapterTest {
    @InjectMocks
    private PedidoJpaAdapter pedidoJpaAdapter;
    @Mock
    private IPedidoEntityMapper pedidoEntityMapper;

    @Mock
    private IPedidoRepository pedidoRepository;

    private Pedido pedido;
    private PedidoEntity pedidoEntity;

    private Restaurante restaurante;
    private RestauranteEntity restauranteEntity;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
        restaurante.setId(1L);

        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(1L);

        pedido=new Pedido(1L, LocalDateTime.now(), Estados.PENDIENTE,restaurante,1L,1L);

        pedidoEntity=new PedidoEntity(1L, LocalDateTime.now(), Estados.PENDIENTE,restauranteEntity,1L,1L);


    }

    @Test
    void savePedido() {
        when(pedidoRepository.save(pedidoEntity)).thenReturn(pedidoEntity);
        when(pedidoEntityMapper.toPedidoEntity(pedido)).thenReturn(pedidoEntity);
        when(pedidoEntityMapper.toPedidoModel(pedidoEntity)).thenReturn(pedido);

        Pedido pedidoARetornar = pedidoJpaAdapter.savePedido(pedido);

        assertEquals(pedidoARetornar.getId(),pedido.getId());


    }

    @Test
    void findById() {
        when(pedidoEntityMapper.toPedidoModel(pedidoEntity)).thenReturn(pedido);
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoEntity));

        Optional<Pedido> pedidoEncontrado = Optional.of(pedidoJpaAdapter.findById(1L));

        assertEquals(pedidoEncontrado.get().getId(),pedido.getId());


    }

    @Test
    void findPedidoCliente() {
        when(pedidoEntityMapper.toPedidoModel(pedidoEntity)).thenReturn(pedido);
        when(pedidoRepository.findPedidoCliente(1L,Estados.PENDIENTE)).thenReturn(pedidoEntity);

        Pedido pedidoEncontrado = pedidoJpaAdapter.findPedidoCliente(1L,Estados.PENDIENTE);

        assertEquals(pedidoEncontrado.getIdCliente(),pedido.getIdCliente());

    }

    @Test
    void findAllPedidosPendientesPaginados() {

        List<Pedido> pedidosList = new ArrayList<>();
        pedidosList.add(pedido);


        List<PedidoEntity> pedidosEntityList = new ArrayList<>();
        pedidosEntityList.add(pedidoEntity);

        Pageable pageable = PageRequest.of(0, 4);


        Page<PedidoEntity> pedidoEntityListPage = new PageImpl<>(pedidosEntityList);


        when(pedidoRepository.findAllPedidosPendientesPaginados(Estados.PENDIENTE,1L,1L,pageable))
                .thenReturn(pedidoEntityListPage);

        given(pedidoEntityMapper.toPedidoModelListPage(pedidoEntityListPage)).willReturn(pedidosList);


        List<Pedido> pedidosEncontrados = pedidoJpaAdapter
                .findAllPedidosPendientesPaginados(0,4,Estados.PENDIENTE,1L,1L);

        assertEquals(pedidosEncontrados.size(),pedidosList.size());

    }

    @Test
    void deletePedido() {

        willDoNothing().given(pedidoRepository).deleteById(1L);

        pedidoJpaAdapter.DeletePedido(1L);

        verify(pedidoRepository,times(1)).deleteById(1L);
    }
}