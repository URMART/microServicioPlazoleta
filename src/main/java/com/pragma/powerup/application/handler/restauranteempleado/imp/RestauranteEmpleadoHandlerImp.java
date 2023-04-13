package com.pragma.powerup.application.handler.restauranteempleado.imp;

import com.pragma.powerup.application.dto.restauranteempleado.request.RestauranteEmpleadoRequestDto;
import com.pragma.powerup.application.dto.restauranteempleado.response.RestauranteEmpleadoResponseDto;
import com.pragma.powerup.application.handler.restauranteempleado.IRestauranteEmpleadoHandler;
import com.pragma.powerup.application.mapper.restaurenteempleado.IRestauranteEmpleadoRequestMapper;
import com.pragma.powerup.application.mapper.restaurenteempleado.IRestauranteEmpleadoResponseMapper;
import com.pragma.powerup.domain.api.restauranteempleado.IRestauranteEmpleadoServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestauranteEmpleadoHandlerImp implements IRestauranteEmpleadoHandler {

    private final IRestauranteEmpleadoServicePort restauranteEmpleadoServicePort;
    private final IRestauranteEmpleadoRequestMapper restauranteEmpleadoRequestMapper;

    private final IRestauranteEmpleadoResponseMapper restauranteEmpleadoResponseMapper;



    @Override
    public void saveRestauranteEmpleado(RestauranteEmpleadoRequestDto restauranteEmpleadoRequestDto) {
        restauranteEmpleadoServicePort.saveRestauranteEmpleado(restauranteEmpleadoRequestMapper
                .toRestauranteEmpleado(restauranteEmpleadoRequestDto));
    }

    @Override
    public RestauranteEmpleadoResponseDto findById(Long id) {
        return restauranteEmpleadoResponseMapper
                .toRestauranteEmpleadoDto(restauranteEmpleadoServicePort.findById(id));
    }
}
