package com.pragma.powerup.application.handler.platos;

import com.pragma.powerup.application.dto.plato.request.PlatoPutRequestDto;
import com.pragma.powerup.application.dto.plato.request.PlatoRequestDto;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.domain.model.Platos;

import java.util.List;

public interface IPlatosHandler {



    void savePlato(PlatoRequestDto platoRequestDto);
    PlatoResponseDto findById(Long id);
    PlatoResponseDto findByNombre(String nombre);

    List<PlatoResponseDto> getAllPlatosPaginadosPorRestaurante(String nombreRestaurante, int page, int size);
}
