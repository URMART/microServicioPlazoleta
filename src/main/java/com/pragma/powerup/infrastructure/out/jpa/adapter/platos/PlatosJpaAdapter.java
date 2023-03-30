package com.pragma.powerup.infrastructure.out.jpa.adapter.platos;

import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.spi.platos.IPlatosPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.platos.IPlatosEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.restaurante.IRestauranteEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.platos.IPlatosrepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlatosJpaAdapter implements IPlatosPersistencePort {



    private final IPlatosrepository platosrepository;
    private final IPlatosEntityMapper platosEntityMapper;


    @Override
    public void savePlato(Platos platos) {
        platosrepository.save(platosEntityMapper.toPlatosEntity(platos));
    }

    @Override
    public Platos findByNombre(String nombre) {
        return platosEntityMapper.toPlatosModel(platosrepository.findByNombre(nombre));
    }
}
