package com.pragma.powerup.infrastructure.out.jpa.repository.platos;


import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IPlatosrepository extends JpaRepository<PlatosEntity,Long> {
    PlatosEntity findByNombre(String nombre);

    Optional<PlatosEntity> findById(Long id);

    @Query(value =
            "SELECT p FROM PlatosEntity AS p where p.restaurante.nombre = ?1 order by  p.categoria.nombre")
    Page<PlatosEntity> getAllPlatosPaginadosPorRestaurante(String nombreRestaurante,Pageable pageable);
}
