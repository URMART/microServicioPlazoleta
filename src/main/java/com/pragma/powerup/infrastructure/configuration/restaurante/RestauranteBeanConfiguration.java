package com.pragma.powerup.infrastructure.configuration.restaurante;

import com.pragma.powerup.domain.api.restaurante.IRestauranteServicePort;
import com.pragma.powerup.domain.spi.restaurante.IRestaurantePersistencePort;
import com.pragma.powerup.domain.usecase.restaurante.RestauranteUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.restaurante.RestauranteJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.restaurante.IRestauranteEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.restaurante.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RestauranteBeanConfiguration {
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort() {
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort() {
        return new RestauranteUseCase(restaurantePersistencePort());
    }
}