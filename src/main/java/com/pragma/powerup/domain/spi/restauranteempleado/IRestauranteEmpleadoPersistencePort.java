package com.pragma.powerup.domain.spi.restauranteempleado;

import com.pragma.powerup.domain.model.RestauranteEmpleado;

public interface IRestauranteEmpleadoPersistencePort {

    void saveRestauranteEmpleado(RestauranteEmpleado restauranteEmpleado);

    RestauranteEmpleado findById(Long id);

}
