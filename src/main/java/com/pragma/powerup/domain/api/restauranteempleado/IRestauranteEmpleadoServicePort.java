package com.pragma.powerup.domain.api.restauranteempleado;



import com.pragma.powerup.domain.model.RestauranteEmpleado;

public interface IRestauranteEmpleadoServicePort {

    void saveRestauranteEmpleado(RestauranteEmpleado restauranteEmpleado);

    RestauranteEmpleado findById(Long id);


}
