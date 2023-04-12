package com.pragma.powerup.infrastructure.configuration.pedidosplatos;


import com.pragma.powerup.domain.api.pedidosplatos.IPedidosPlatosServicePort;

import com.pragma.powerup.domain.spi.pedidosplatos.IPedidosPlatosPersistencePort;

import com.pragma.powerup.domain.usecase.pedidosplatos.PedidosPlatosUseCase;

import com.pragma.powerup.infrastructure.out.jpa.adapter.pedidosplatos.PedidosPlatosJpaAdapter;

import com.pragma.powerup.infrastructure.out.jpa.mapper.pedidosplatos.IPedidosPlatosMapper;

import com.pragma.powerup.infrastructure.out.jpa.repository.pedidosplatos.PedidosPlatosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PedidosPlatosBeanConfiguration {

    private final PedidosPlatosRepository pedidosPlatosRepository;

    private final IPedidosPlatosMapper pedidosPlatosMapper;


    @Bean
    public IPedidosPlatosPersistencePort pedidosPlatosPersistencePort() {
        return new PedidosPlatosJpaAdapter(pedidosPlatosMapper,pedidosPlatosRepository );

    }

    @Bean
    public IPedidosPlatosServicePort pedidosPlatosServicePort() {
        return new PedidosPlatosUseCase(pedidosPlatosPersistencePort());
    }

}
