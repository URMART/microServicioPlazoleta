package com.pragma.powerup.infrastructure.out.jpa.adapter.restauranteempleado;

import com.pragma.powerup.domain.model.RestauranteEmpleado;
import com.pragma.powerup.domain.spi.restauranteempleado.IRestauranteEmpleadoPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.restauranteempleado.IRestauranteEmpleadoEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.restauranteempleado.IRestauranteEmpleadoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteEmpleadoJpaAdapter implements IRestauranteEmpleadoPersistencePort {

    private final IRestauranteEmpleadoRepository restauranteEmpleadoRepository;
    private final IRestauranteEmpleadoEntityMapper restauranteEmpleadoEntityMapper;

    @Override
    public void saveRestauranteEmpleado(RestauranteEmpleado restauranteEmpleado) {
        restauranteEmpleadoRepository
                .save(restauranteEmpleadoEntityMapper.toRestauranteEmpleadoEntity(restauranteEmpleado));
    }

    @Override
    public RestauranteEmpleado findById(Long id) {
        return restauranteEmpleadoEntityMapper
                .toRestauranteEmpleadoModel(restauranteEmpleadoRepository.findById(id).orElseThrow());
    }
}
