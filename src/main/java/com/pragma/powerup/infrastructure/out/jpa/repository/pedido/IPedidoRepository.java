package com.pragma.powerup.infrastructure.out.jpa.repository.pedido;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPedidoRepository extends JpaRepository<PedidoEntity,Long> {

   Optional<PedidoEntity> findById(Long id);

   PedidoEntity save(PedidoEntity entity);
   @Query(value = "SELECT p FROM PedidoEntity AS p where p.idCliente = ?1 and  p.estado = ?2 ")
   PedidoEntity findPedidoCliente(Long idCliente, Estados estado);
}
