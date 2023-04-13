package com.pragma.powerup.domain.usecase.restauranteempleado;

import com.pragma.powerup.domain.api.restauranteempleado.IRestauranteEmpleadoServicePort;
import com.pragma.powerup.domain.model.RestauranteEmpleado;
import com.pragma.powerup.domain.spi.restauranteempleado.IRestauranteEmpleadoPersistencePort;

public class RestauranteEmpleadoUseCase implements IRestauranteEmpleadoServicePort {

    private final IRestauranteEmpleadoPersistencePort restauranteEmpleadoPersistencePort;

    public RestauranteEmpleadoUseCase(IRestauranteEmpleadoPersistencePort restauranteEmpleadoPersistencePort) {
        this.restauranteEmpleadoPersistencePort = restauranteEmpleadoPersistencePort;
    }

    @Override
    public void saveRestauranteEmpleado(RestauranteEmpleado restauranteEmpleado) {
        restauranteEmpleadoPersistencePort.saveRestauranteEmpleado(restauranteEmpleado);
    }

    @Override
    public RestauranteEmpleado findById(Long id) {
        return restauranteEmpleadoPersistencePort.findById(id);
    }
}
