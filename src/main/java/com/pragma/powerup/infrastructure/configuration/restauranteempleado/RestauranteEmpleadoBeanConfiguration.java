package com.pragma.powerup.infrastructure.configuration.restauranteempleado;

import com.pragma.powerup.domain.api.categoria.ICategoriaServicePort;
import com.pragma.powerup.domain.api.restauranteempleado.IRestauranteEmpleadoServicePort;
import com.pragma.powerup.domain.spi.categoria.ICategoriaPersistencePort;
import com.pragma.powerup.domain.spi.restauranteempleado.IRestauranteEmpleadoPersistencePort;
import com.pragma.powerup.domain.usecase.categoria.CategoriaUseCase;
import com.pragma.powerup.domain.usecase.restauranteempleado.RestauranteEmpleadoUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.categoria.CategoriaJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.restauranteempleado.RestauranteEmpleadoJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.restauranteempleado.IRestauranteEmpleadoEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.restauranteempleado.IRestauranteEmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RestauranteEmpleadoBeanConfiguration {
    private final IRestauranteEmpleadoRepository restauranteEmpleadoRepository;
    private final IRestauranteEmpleadoEntityMapper restauranteEmpleadoEntityMapper;



    @Bean
    public IRestauranteEmpleadoPersistencePort restauranteEmpleadoPersistencePort() {
        return new RestauranteEmpleadoJpaAdapter(restauranteEmpleadoRepository, restauranteEmpleadoEntityMapper);
    }


    @Bean
    public IRestauranteEmpleadoServicePort restauranteEmpleadoServicePort() {
        return new RestauranteEmpleadoUseCase(restauranteEmpleadoPersistencePort());
    }




}
