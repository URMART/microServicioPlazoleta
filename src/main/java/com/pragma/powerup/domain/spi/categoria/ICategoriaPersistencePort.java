package com.pragma.powerup.domain.spi.categoria;

import com.pragma.powerup.domain.model.Categoria;

public interface ICategoriaPersistencePort {
    Categoria findByNombre(String nombre);
}
