package com.pragma.powerup.infrastructure.out.jpa.adapter.restauranteempleado;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.pragma.powerup.application.handler.factory.FactoryRestauranteEmpleadoData;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.model.RestauranteEmpleado;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEmpleadoEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.restauranteempleado.IRestauranteEmpleadoEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.restauranteempleado.IRestauranteEmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestauranteEmpleadoJpaAdapterTest {
    @Mock
    private IRestauranteEmpleadoRepository restauranteEmpleadoRepository;
    @Mock
    private IRestauranteEmpleadoEntityMapper restauranteEmpleadoEntityMapper;
    @InjectMocks
    private RestauranteEmpleadoJpaAdapter restauranteEmpleadoJpaAdapter;

    private RestauranteEmpleado restauranteEmpleado;
    private RestauranteEmpleadoEntity restauranteEmpleadoEntity;

    @BeforeEach
    void setUp() {
        restauranteEmpleado = FactoryRestauranteEmpleadoData.getRestauranteEmpleadoData();
        restauranteEmpleadoEntity = FactoryRestauranteEmpleadoData.getRestauranteEmpleadoEntityData();
    }

    @Test
    void saveRestauranteEmpleado() {


        when(restauranteEmpleadoRepository.save(restauranteEmpleadoEntity)).thenReturn(restauranteEmpleadoEntity);
        when(restauranteEmpleadoEntityMapper.toRestauranteEmpleadoEntity(restauranteEmpleado)).thenReturn(restauranteEmpleadoEntity);


        restauranteEmpleadoJpaAdapter.saveRestauranteEmpleado(restauranteEmpleado);

        verify(restauranteEmpleadoRepository,timeout(1)).save(restauranteEmpleadoEntity);


    }

    @Test
    void findById() {

        when(restauranteEmpleadoEntityMapper.toRestauranteEmpleadoModel(restauranteEmpleadoEntity)).thenReturn(restauranteEmpleado);
        when(restauranteEmpleadoRepository.findById(1L)).thenReturn(Optional.of(restauranteEmpleadoEntity));

        Optional<RestauranteEmpleado> restauranteEmpleadoEncontrado = Optional.of(restauranteEmpleadoJpaAdapter.findById(1L));

        assertEquals(restauranteEmpleadoEncontrado.get().getId(),restauranteEmpleado.getId());
    }
}