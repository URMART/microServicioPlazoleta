package com.pragma.powerup.infrastructure.configuration.pedido;


import com.pragma.powerup.domain.api.categoria.ICategoriaServicePort;
import com.pragma.powerup.domain.api.pedido.IPedidoServicePort;
import com.pragma.powerup.domain.spi.pedido.IPedidoPersistencePort;

import com.pragma.powerup.domain.usecase.categoria.CategoriaUseCase;
import com.pragma.powerup.domain.usecase.pedido.PedidoUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.pedido.PedidoJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.pedido.IPedidoEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.pedido.IPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PedidoBeanConfiguration {

    private final IPedidoRepository pedidoRepository;

    private final IPedidoEntityMapper pedidoEntityMapper;



    @Bean
    public IPedidoPersistencePort pedidoPersistencePort() {
        return new PedidoJpaAdapter(pedidoEntityMapper,pedidoRepository );
    }

    @Bean
    public IPedidoServicePort pedidoServicePort() {
        return new PedidoUseCase(pedidoPersistencePort());
    }
}
