package com.pragma.powerup.domain.api.restaurante;

import com.pragma.powerup.domain.model.Restaurante;

import java.util.List;

public interface IRestauranteServicePort {

    void saveRestaurante(Restaurante restaurante);
    Restaurante findByNombre(String nombre);
    List<Restaurante> getAllRestaurantes();
    List<Restaurante> getAllRestaurantesPaginados(int page,int size);

    Restaurante findById(Long id);
}