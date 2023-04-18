package com.pragma.powerup.domain.usecase.platos;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.domain.spi.pedidosplatos.IPedidosPlatosPersistencePort;
import com.pragma.powerup.domain.spi.platos.IPlatosPersistencePort;
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
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlatosUseCaseTest {
    @InjectMocks
    private  PlatosUseCase platosUseCase;

    @Mock
    private IPlatosPersistencePort platosPersistencePort;


    private Platos plato;
    private Restaurante restaurante;
    private Categoria categoria;
    @BeforeEach
    void setUp() {

        restaurante = new Restaurante();
        restaurante.setId(1L);
        categoria = new Categoria();
        categoria.setId(1L);


        plato = new Platos();
        plato.setId(1L);
        plato.setNombre("pastas");
        plato.setDescripcion("pastas doria");
        plato.setPrecio(20000L);
        plato.setUrlImagen("http://www.img.com");
        plato.setActivo(true);
        plato.setRestaurante(restaurante);
        plato.setCategoria(categoria);

    }

    @Test
    void savePlato() {
        platosUseCase.savePlato(plato);
        verify(platosPersistencePort,timeout(1)).savePlato(plato);
    }

    @Test
    void findByNombre() {

        when(platosPersistencePort.findByNombre("pastas")).thenReturn(plato);


        Platos platoEncontrado =  platosUseCase.findByNombre("pastas");

        assertEquals(platoEncontrado.getNombre(),plato.getNombre());
    }

    @Test
    void findById() {


        when(platosPersistencePort.findById(1L)).thenReturn(plato);

        Platos platoEncontrado = platosUseCase.findById(1L);

        assertEquals(platoEncontrado.getId(),plato.getId());
    }

    @Test
    void getAllPlatosPaginadosPorRestaurante() {

        List<Platos> platosList = new ArrayList<>();
        platosList.add(plato);

        when(platosUseCase.getAllPlatosPaginadosPorRestaurante(plato.getRestaurante().getNombre(),0,4))
                .thenReturn(platosList);


        List<Platos> platosEncontrados = platosUseCase
                .getAllPlatosPaginadosPorRestaurante(plato.getRestaurante().getNombre(),0,4);

        assertEquals(platosEncontrados.size(),platosList.size());
    }
}