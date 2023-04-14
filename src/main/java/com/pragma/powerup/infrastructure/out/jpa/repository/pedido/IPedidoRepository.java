package com.pragma.powerup.infrastructure.out.jpa.repository.pedido;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface IPedidoRepository extends JpaRepository<PedidoEntity,Long> {

   Optional<PedidoEntity> findById(Long id);

   PedidoEntity save(PedidoEntity entity);
   @Query(value = "SELECT p FROM PedidoEntity AS p where p.idCliente = ?1 and  p.estado = ?2 ")
   PedidoEntity findPedidoCliente(Long idCliente, Estados estado);

   @Query(value = "SELECT p FROM PedidoEntity  p where p.estado = :estado and p.idRestaurante.id = :idRestaurante " +
           "and p.idChef = :idChef")
   Page<PedidoEntity> findAllPedidosPendientesPaginados(
           @Param("estado")Estados estado, @Param("idRestaurante") Long idRestaurante,@Param("idChef") Long idChef, Pageable pageable
   );




}
