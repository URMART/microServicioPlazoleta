package com.pragma.powerup.application.handler.platos.imp;

import com.pragma.powerup.application.dto.plato.request.PlatoPutRequestDto;
import com.pragma.powerup.application.dto.plato.request.PlatoRequestDto;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.application.handler.platos.IPlatosHandler;
import com.pragma.powerup.application.mapper.platos.IPlatoRequestMapper;
import com.pragma.powerup.application.mapper.platos.IPlatoResponseMapper;
import com.pragma.powerup.domain.api.platos.IPlatosServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class PlatosHandlerImp implements IPlatosHandler {

    private final IPlatosServicePort platosServicePort;
    private final IPlatoRequestMapper platoRequestMapper;
    private final IPlatoResponseMapper platoResponseMapper;
    @Override
    public void savePlato(PlatoRequestDto platoRequestDto) {
        platosServicePort.savePlato(platoRequestMapper.toPlatos(platoRequestDto));
    }


    @Override
    public PlatoResponseDto findByNombre(String nombre) {
        return platoResponseMapper.toPlatosDto(platosServicePort.findByNombre(nombre));
    }
}
