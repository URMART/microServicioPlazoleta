package com.pragma.powerup.domain.api.categoria;

import com.pragma.powerup.domain.model.Categoria;

public interface ICategoriaServicePort {

    Categoria findByNombre(String nombre);
}
