package com.pragma.powerup.infrastructure.out.jpa.repository.restauranteempleado;

import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IRestauranteEmpleadoRepository extends JpaRepository<RestauranteEmpleadoEntity,Long> {

    @Query(value = "SELECT r FROM RestauranteEmpleadoEntity r WHERE r.idEmpleado = ?1")
    Optional<RestauranteEmpleadoEntity> findById(Long id);
}
