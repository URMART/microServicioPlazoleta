package com.pragma.powerup.infrastructure.out.jpa.adapter.categoria;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.spi.categoria.ICategoriaPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.categoria.ICategoriaEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.restaurante.IRestauranteEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.categoria.ICategoriaRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.restaurante.IRestauranteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoriaJpaAdapter implements ICategoriaPersistencePort {


    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper  categoriaEntityMapper;
    @Override
    public Categoria findByNombre(String nombre) {
        return categoriaEntityMapper.toCategoriaModel(categoriaRepository.findByNombre(nombre));
    }
}
