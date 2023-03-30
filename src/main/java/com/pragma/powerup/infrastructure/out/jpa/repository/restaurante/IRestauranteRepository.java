package com.pragma.powerup.infrastructure.out.jpa.repository.restaurante;

import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
    RestauranteEntity findByNombre(String nombre);
}