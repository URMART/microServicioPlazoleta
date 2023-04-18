package com.pragma.powerup.domain.usecase.restaurante;

import com.pragma.powerup.application.handler.factory.FactoryRestauranteData;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.domain.spi.restaurante.IRestaurantePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestauranteUseCaseTest {

    @Mock
    private IRestaurantePersistencePort restaurantePersistencePort;
    @InjectMocks
    private RestauranteUseCase restauranteUseCase;

    private Restaurante restaurante;
    @BeforeEach
    void setUp() {
        restaurante = FactoryRestauranteData.restauranteData();
    }


    @Test
    void saveRestaurante() {

        when(restaurantePersistencePort.saveRestaurante(restaurante)).thenReturn(restaurante);

        restauranteUseCase.saveRestaurante(restaurante);

        verify(restaurantePersistencePort).saveRestaurante(restaurante);
    }

    @Test
    void getAllRestaurantes() {
        List<Restaurante> restauranteList = new ArrayList<>();
        restauranteList.add(restaurante);
        when(restaurantePersistencePort.getAllRestaurantes()).thenReturn(restauranteList);

        List<Restaurante> restaurantesARetornar = restauranteUseCase.getAllRestaurantes();

        assertEquals(restaurantesARetornar.size(),restauranteList.size());
    }

    @Test
    void getAllRestaurantesPaginados() {

        List<Restaurante> restauranteList = new ArrayList<>();
        restauranteList.add(restaurante);

        when(restaurantePersistencePort.getAllRestaurantesPaginados(0,4))
                .thenReturn(restauranteList);

        List<Restaurante> restaurantesEncontrados = restauranteUseCase
                .getAllRestaurantesPaginados(0,4);

        assertEquals(restaurantesEncontrados.size(),restauranteList.size());
    }

    @Test
    void findById() {


        when(restaurantePersistencePort.findById(1L)).thenReturn(restaurante);

        Restaurante restauranteEncontrado = restauranteUseCase.findById(1L);

        assertEquals(restauranteEncontrado.getId(),restaurante.getId());
    }

    @Test
    void findByNombre() {

        when(restaurantePersistencePort.findByNombre("el manjar")).thenReturn(restaurante);

        Restaurante restauranteEncontrado =  restauranteUseCase.findByNombre("el manjar");

        assertEquals(restauranteEncontrado.getNombre(),restaurante.getNombre());
    }
}