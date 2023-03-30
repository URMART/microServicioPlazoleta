package com.pragma.powerup.application.handler.platos;

import com.pragma.powerup.application.dto.plato.request.PlatoPutRequestDto;
import com.pragma.powerup.application.dto.plato.request.PlatoRequestDto;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.domain.model.Platos;

public interface IPlatosHandler {



    void savePlato(PlatoRequestDto platoRequestDto);

    PlatoResponseDto findByNombre(String nombre);
}
