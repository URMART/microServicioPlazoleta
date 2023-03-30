package com.pragma.powerup.infrastructure.out.jpa.repository.platos;

import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlatosrepository extends JpaRepository<PlatosEntity,Long> {
    PlatosEntity findByNombre(String nombre);
}
