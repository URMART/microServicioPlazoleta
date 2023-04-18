package com.pragma.powerup.application.handler.platos.imp;

import com.pragma.powerup.application.dto.plato.request.PlatoRequestDto;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.application.mapper.platos.IPlatoRequestMapper;
import com.pragma.powerup.application.mapper.platos.IPlatoResponseMapper;
import com.pragma.powerup.domain.api.platos.IPlatosServicePort;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.model.Restaurante;
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
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlatosHandlerImpTest {
    @InjectMocks
    private PlatosHandlerImp platosHandlerImp;
    @Mock
    private  IPlatosServicePort platosServicePort;
    @Mock
    private  IPlatoRequestMapper platoRequestMapper;
    @Mock
    private  IPlatoResponseMapper platoResponseMapper;

    private PlatoResponseDto platoResponseDto;
    private PlatoRequestDto platoRequestDto;
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

        platoResponseDto = new PlatoResponseDto();
        platoResponseDto.setId(1L);
        platoResponseDto.setNombre("pastas");
        platoResponseDto.setDescripcion("pastas doria");
        platoResponseDto.setPrecio(20000L);
        platoResponseDto.setUrlImagen("http://www.img.com");
        platoResponseDto.setActivo(true);
        platoResponseDto.setRestaurante(restaurante);
        platoResponseDto.setCategoria(categoria);


        platoRequestDto = new PlatoRequestDto();
        platoRequestDto.setId(1L);
        platoRequestDto.setNombre("pastas");
        platoRequestDto.setDescripcion("pastas doria");
        platoRequestDto.setPrecio(20000L);
        platoRequestDto.setUrlImagen("http://www.img.com");
        platoRequestDto.setActivo(true);
        platoRequestDto.setRestaurante(restaurante);
        platoRequestDto.setCategoria(categoria);
    }
    @Test
    void savePlato() {

        when(platoRequestMapper.toPlatos(platoRequestDto)).thenReturn(plato);


        platosHandlerImp.savePlato(platoRequestDto);

        verify(platosServicePort,timeout(1)).savePlato(plato);
    }

    @Test
    void findById() {
        when(platoResponseMapper.toPlatosDto(plato)).thenReturn(platoResponseDto);
        when(platosServicePort.findById(plato.getId())).thenReturn(plato);

        PlatoResponseDto platoEncontrado = platosHandlerImp.findById(1L);

        assertEquals(platoEncontrado.getId(),plato.getId());
    }

    @Test
    void findByNombre() {

        when(platoResponseMapper.toPlatosDto(plato)).thenReturn(platoResponseDto);
        when(platosServicePort.findByNombre(platoRequestDto.getNombre())).thenReturn(plato);

        PlatoResponseDto platoEncontrado = platosHandlerImp.findByNombre("pastas");

        assertEquals(platoEncontrado.getNombre(),plato.getNombre());

    }

    @Test
    void getAllPlatosPaginadosPorRestaurante() {

        List<Platos> platosList = new ArrayList<>();
        platosList.add(plato);

        List<PlatoResponseDto> platosListResponseDto = new ArrayList<>();
        platosListResponseDto.add(platoResponseDto);


        when(platoResponseMapper.toPlatosDtoList(platosList)).thenReturn(platosListResponseDto);
        when(platosServicePort
                .getAllPlatosPaginadosPorRestaurante(restaurante.getNombre(), 0, 4))
                .thenReturn(platosList);

        List<PlatoResponseDto> platosListResponseDtoEncontrados = platosHandlerImp
                .getAllPlatosPaginadosPorRestaurante(restaurante.getNombre(),0,4);

        assertEquals(platosListResponseDtoEncontrados.size(),platosList.size());
    }
}