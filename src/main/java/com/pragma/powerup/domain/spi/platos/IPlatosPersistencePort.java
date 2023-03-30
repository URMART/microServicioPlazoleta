package com.pragma.powerup.domain.spi.platos;

import com.pragma.powerup.domain.model.Platos;

public interface IPlatosPersistencePort {
    void savePlato(Platos platos);
    Platos findByNombre(String nombre);
}
