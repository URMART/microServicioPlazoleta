package com.pragma.powerup.domain.usecase.categoria;

import com.pragma.powerup.domain.api.categoria.ICategoriaServicePort;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.spi.categoria.ICategoriaPersistencePort;

public class CategoriaUseCase implements ICategoriaServicePort {
    private final ICategoriaPersistencePort categoriaPersistencePort;

    public CategoriaUseCase(ICategoriaPersistencePort categoriaPersistencePort) {
        this.categoriaPersistencePort = categoriaPersistencePort;
    }

    @Override
    public Categoria findByNombre(String nombre) {
        return categoriaPersistencePort.findByNombre(nombre);
    }
}
