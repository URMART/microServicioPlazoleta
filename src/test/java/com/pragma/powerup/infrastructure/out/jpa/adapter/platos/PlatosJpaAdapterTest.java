package com.pragma.powerup.infrastructure.out.jpa.adapter.platos;

import com.pragma.powerup.domain.model.*;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.platos.IPlatosEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.platos.IPlatosrepository;
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

import static org.mockito.BDDMockito.given;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlatosJpaAdapterTest {

    @Mock
    private IPlatosrepository platosrepository;

    @Mock
    private IPlatosEntityMapper platosEntityMapper;
    @InjectMocks
    private PlatosJpaAdapter platosJpaAdapter;

    private PlatosEntity platosEntity;
    private Platos plato;
    private Restaurante restaurante;
    private Categoria categoria;

    private RestauranteEntity restauranteEntity;
    private CategoriaEntity categoriaEntity;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
        restaurante.setId(1L);
        categoria = new Categoria();
        categoria.setId(1L);

        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(1L);

        categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(1L);



        plato = new Platos();
        plato.setId(1L);
        plato.setNombre("pastas");
        plato.setDescripcion("pastas doria");
        plato.setPrecio(20000L);
        plato.setUrlImagen("http://www.img.com");
        plato.setActivo(true);
        plato.setRestaurante(restaurante);
        plato.setCategoria(categoria);

        platosEntity = new PlatosEntity();
        platosEntity.setId(1L);
        platosEntity.setNombre("pastas");
        platosEntity.setDescripcion("pastas doria");
        platosEntity.setPrecio(20000L);
        platosEntity.setUrlImagen("http://www.img.com");
        platosEntity.setActivo(true);
        platosEntity.setRestaurante(restauranteEntity);
        platosEntity.setCategoria(categoriaEntity);


    }

    @Test
    void savePlato() {


        when(platosrepository.save(platosEntity)).thenReturn(platosEntity);
        when(platosEntityMapper.toPlatosEntity(plato)).thenReturn(platosEntity);


        platosJpaAdapter.savePlato(plato);

        verify(platosrepository,timeout(1)).save(platosEntity);


    }

    @Test
    void findByNombre() {

        when(platosrepository.findByNombre("pastas")).thenReturn(platosEntity);
        when(platosEntityMapper.toPlatosModel(platosEntity)).thenReturn(plato);

        Platos platoEncontrado =  platosJpaAdapter.findByNombre("pastas");

        assertEquals(platoEncontrado.getNombre(),plato.getNombre());

    }

    @Test
    void findById() {

        when(platosEntityMapper.toPlatosModel(platosEntity)).thenReturn(plato);
        when(platosrepository.findById(1L)).thenReturn(Optional.of(platosEntity));

        Optional<Platos> platoEncontrado = Optional.of(platosJpaAdapter.findById(1L));

        assertEquals(platoEncontrado.get().getId(),plato.getId());
    }

    @Test
    void getAllPlatosPaginadosPorRestaurante() {

        List<Platos> platosList = new ArrayList<>();
        platosList.add(plato);


        List<PlatosEntity> platosEntityList = new ArrayList<>();
        platosEntityList.add(platosEntity);

        Pageable pageable = PageRequest.of(0, 4);


        Page<PlatosEntity> platosEntityListPage = new PageImpl<>(platosEntityList);


        when(platosrepository.getAllPlatosPaginadosPorRestaurante(plato.getRestaurante().getNombre(),pageable))
                .thenReturn(platosEntityListPage);

        given(platosEntityMapper.toPlatoModelListPa(platosEntityListPage)).willReturn(platosList);


        List<Platos> platosEncontrados = platosJpaAdapter
                .getAllPlatosPaginadosPorRestaurante(plato.getRestaurante().getNombre(),0,4);

        assertEquals(platosEncontrados.size(),platosList.size());
    }
}