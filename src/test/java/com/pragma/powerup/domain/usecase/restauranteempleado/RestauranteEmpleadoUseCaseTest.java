package com.pragma.powerup.domain.usecase.restauranteempleado;

import com.pragma.powerup.application.handler.factory.FactoryRestauranteEmpleadoData;
import com.pragma.powerup.domain.model.RestauranteEmpleado;
import com.pragma.powerup.domain.spi.restauranteempleado.IRestauranteEmpleadoPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.timeout;


@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestauranteEmpleadoUseCaseTest {
    @InjectMocks
    private RestauranteEmpleadoUseCase restauranteEmpleadoUseCase;
    @Mock
    private IRestauranteEmpleadoPersistencePort restauranteEmpleadoPersistencePort;

    private RestauranteEmpleado restauranteEmpleado;
    @BeforeEach
    void setUp() {
        restauranteEmpleado = FactoryRestauranteEmpleadoData.getRestauranteEmpleadoData();

    }

    @Test
    void saveRestauranteEmpleado() {
        restauranteEmpleadoUseCase.saveRestauranteEmpleado(restauranteEmpleado);

        verify(restauranteEmpleadoPersistencePort,timeout(1))
                .saveRestauranteEmpleado(restauranteEmpleado);
    }

    @Test
    void findById() {

        when(restauranteEmpleadoPersistencePort.findById(1L)).thenReturn(restauranteEmpleado);

        RestauranteEmpleado restauranteEmpleadoEncontrado = restauranteEmpleadoUseCase.findById(1L);

        assertEquals(restauranteEmpleadoEncontrado.getId(),restauranteEmpleado.getId());
    }
}