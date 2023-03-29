package com.pragma.powerup.domain.api.restaurante;

import com.pragma.powerup.domain.model.Restaurante;

import java.util.List;

public interface IRestauranteServicePort {

    void saveRestaurante(Restaurante restaurante);

    List<Restaurante> getAllRestaurantes();
}