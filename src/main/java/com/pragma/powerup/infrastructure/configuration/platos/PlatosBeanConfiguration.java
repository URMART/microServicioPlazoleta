package com.pragma.powerup.infrastructure.configuration.platos;

import com.pragma.powerup.domain.api.platos.IPlatosServicePort;
import com.pragma.powerup.domain.api.restaurante.IRestauranteServicePort;
import com.pragma.powerup.domain.spi.platos.IPlatosPersistencePort;
import com.pragma.powerup.domain.spi.restaurante.IRestaurantePersistencePort;
import com.pragma.powerup.domain.usecase.platos.PlatosUseCase;
import com.pragma.powerup.domain.usecase.restaurante.RestauranteUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.platos.PlatosJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.restaurante.RestauranteJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.platos.IPlatosEntityMapper;

import com.pragma.powerup.infrastructure.out.jpa.repository.platos.IPlatosrepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PlatosBeanConfiguration {

    private final IPlatosrepository platosrepository;
    private final IPlatosEntityMapper platosEntityMapper;

    @Bean
    public IPlatosPersistencePort platosPersistencePort() {
        return new PlatosJpaAdapter(platosrepository, platosEntityMapper);
    }

    @Bean
    public IPlatosServicePort platosServicePort() {
        return new PlatosUseCase(platosPersistencePort());
    }


}
