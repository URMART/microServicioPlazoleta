package com.pragma.powerup.infrastructure.out.jpa.repository.pedidosplatos;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidosPlatosEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidosPlatosRepository extends JpaRepository<PedidosPlatosEntity, Long> {

    Optional<PedidosPlatosEntity> findById(Long id);

    @Query(value = "SELECT p " +
            "FROM PedidosPlatosEntity p where p.idPedido.id = :idPedido")
    List<PedidosPlatosEntity> findAll(@Param("idPedido") Long idPedido);

    @Query(value = "SELECT pp FROM PedidosPlatosEntity  pp where pp.idPlato.restaurante.id = :idRestaurante " +
            "and pp.idPedido.estado = :estado " )
    Page<PedidosPlatosEntity> findAllPedidosPendientesPaginados(
            @Param("estado") Estados estado, @Param("idRestaurante") Long idRestaurante, Pageable pageable
    );

}
