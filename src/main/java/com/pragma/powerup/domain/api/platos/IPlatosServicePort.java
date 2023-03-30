package com.pragma.powerup.domain.api.platos;

import com.pragma.powerup.domain.model.Platos;

public interface IPlatosServicePort {
    void savePlato(Platos  platos);
    Platos findByNombre(String nombre);
}
