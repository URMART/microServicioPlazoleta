package com.pragma.powerup.infrastructure.configuration.categoria;

import com.pragma.powerup.domain.api.categoria.ICategoriaServicePort;
import com.pragma.powerup.domain.api.restaurante.IRestauranteServicePort;
import com.pragma.powerup.domain.spi.categoria.ICategoriaPersistencePort;
import com.pragma.powerup.domain.spi.restaurante.IRestaurantePersistencePort;
import com.pragma.powerup.domain.usecase.categoria.CategoriaUseCase;
import com.pragma.powerup.domain.usecase.restaurante.RestauranteUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.categoria.CategoriaJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.restaurante.RestauranteJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.categoria.ICategoriaEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.categoria.ICategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoriaBeanConfiguration {

    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper categoriaEntityMapper;

    @Bean
    public ICategoriaPersistencePort categoriaPersistencePort() {
        return new CategoriaJpaAdapter(categoriaRepository, categoriaEntityMapper);
    }


    @Bean
    public ICategoriaServicePort categoriaServicePort() {
        return new CategoriaUseCase(categoriaPersistencePort());
    }


}
