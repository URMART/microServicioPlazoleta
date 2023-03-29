package com.pragma.powerup.domain.spi.restaurante;

import com.pragma.powerup.domain.model.Restaurante;
import java.util.List;

public interface IRestaurantePersistencePort {
    Restaurante saveRestaurante(Restaurante restaurante);

    List<Restaurante> getAllRestaurantes();
}