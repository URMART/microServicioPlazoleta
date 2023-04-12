package com.pragma.powerup.infrastructure.out.jpa.repository.pedidosplatos;

import com.pragma.powerup.infrastructure.out.jpa.entity.PedidosPlatosEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PedidosPlatosRepository extends JpaRepository<PedidosPlatosEntity, Long> {

    Optional<PedidosPlatosEntity> findById(Long id);

}
