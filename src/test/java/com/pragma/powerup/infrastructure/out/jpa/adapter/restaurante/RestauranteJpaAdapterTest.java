package com.pragma.powerup.infrastructure.out.jpa.adapter.restaurante;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.restaurante.IRestauranteEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.restaurante.IRestauranteRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestauranteJpaAdapterTest {
    @Mock
    private  IRestauranteRepository restauranteRepository;
    @Mock
    private  IRestauranteEntityMapper restauranteEntityMapper;
    @InjectMocks
    private RestauranteJpaAdapter restauranteJpaAdapter;

    private Restaurante restaurante;
    private RestauranteEntity restauranteEntity;

    @BeforeEach
    void setUp() {

        restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNombre("el manjar");
        restaurante.setDireccion("cr6a 55");
        restaurante.setIdPropietario(1L);
        restaurante.setTelefono("2233223");
        restaurante.setUrlLogo("www.logo.com");
        restaurante.setNit(22343L);


        restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(1L);
        restauranteEntity.setNombre("el manjar");
        restauranteEntity.setDireccion("cr6a 55");
        restauranteEntity.setIdPropietario(1L);
        restauranteEntity.setTelefono("2233223");
        restauranteEntity.setUrlLogo("www.logo.com");
        restauranteEntity.setNit(22343L);



    }

    @Test
    void saveRestaurante() {
        when(restauranteRepository.save(restauranteEntity)).thenReturn(restauranteEntity);
        when(restauranteEntityMapper.toRestauranteEntity(restaurante)).thenReturn(restauranteEntity);
        when(restauranteEntityMapper.toRestauranteModel(restauranteEntity)).thenReturn(restaurante);

        Restaurante restauranteARetornar = restauranteJpaAdapter.saveRestaurante(restaurante);

        assertEquals(restauranteARetornar.getId(),restaurante.getId());

    }

    @Test
    void getAllRestaurantes() {
        List<Restaurante> restauranteList = new ArrayList<>();
        restauranteList.add(restaurante);

        List<RestauranteEntity> restauranteEntityList = new ArrayList<>();
        restauranteEntityList.add(restauranteEntity);

        when(restauranteRepository.findAll()).thenReturn(restauranteEntityList);
        when(restauranteEntityMapper.toRestauranteModelList(restauranteEntityList)).thenReturn(restauranteList);

        List<Restaurante> restaurantesARetornar = restauranteJpaAdapter.getAllRestaurantes();

        assertEquals(restaurantesARetornar.size(),restauranteList.size());



    }

    @Test
    void getAllRestaurantesPaginados() {

        List<Restaurante> restauranteList = new ArrayList<>();
        restauranteList.add(restaurante);

        List<RestauranteEntity> restauranteEntityList = new ArrayList<>();
        restauranteEntityList.add(restauranteEntity);


        Pageable pageable = PageRequest.of(0, 4);


        Page<RestauranteEntity> restauranteEntityListPage = new PageImpl<>(restauranteEntityList);


        when(restauranteRepository.findAllRestaurantesConPaginacion(pageable))
                .thenReturn(restauranteEntityListPage);

        given(restauranteEntityMapper.toRestauranteModelListPa(restauranteEntityListPage)).willReturn(restauranteList);


        List<Restaurante> restaurantesEncontrados = restauranteJpaAdapter
                .getAllRestaurantesPaginados(0,4);

        assertEquals(restaurantesEncontrados.size(),restauranteList.size());
    }

    @Test
    void findById() {

        when(restauranteEntityMapper.toRestauranteModel(restauranteEntity)).thenReturn(restaurante);
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restauranteEntity));

        Optional<Restaurante> restauranteEncontrado = Optional.of(restauranteJpaAdapter.findById(1L));

        assertEquals(restauranteEncontrado.get().getId(),restaurante.getId());
    }

    @Test
    void findByNombre() {

        when(restauranteRepository.findByNombre("el manjar")).thenReturn(restauranteEntity);
        when(restauranteEntityMapper.toRestauranteModel(restauranteEntity)).thenReturn(restaurante);

        Restaurante restauranteEncontrado =  restauranteJpaAdapter.findByNombre("el manjar");

        assertEquals(restauranteEncontrado.getNombre(),restaurante.getNombre());
    }
}