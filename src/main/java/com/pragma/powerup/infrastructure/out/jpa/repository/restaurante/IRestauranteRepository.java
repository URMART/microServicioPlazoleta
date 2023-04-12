package com.pragma.powerup.infrastructure.out.jpa.repository.restaurante;

import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
    RestauranteEntity findByNombre(String nombre);

    @Query(value = "SELECT r FROM RestauranteEntity r order by r.nombre asc")
    Page<RestauranteEntity> findAllRestaurantesConPaginacion(Pageable pageable);
}