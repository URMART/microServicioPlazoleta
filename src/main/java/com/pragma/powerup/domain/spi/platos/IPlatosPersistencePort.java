package com.pragma.powerup.domain.spi.platos;

import com.pragma.powerup.domain.model.Platos;

import java.util.List;

public interface IPlatosPersistencePort {
    void savePlato(Platos platos);
    Platos findByNombre(String nombre);

    Platos findById(Long id);
    List<Platos> getAllPlatosPaginadosPorRestaurante(String nombreRestaurante, int page, int size);
}
