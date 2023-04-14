package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Platos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pedidos_platos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidosPlatosEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pedidos_platos")
    private Long idPedidosPlatos;

    @ManyToOne(fetch = FetchType.LAZY)
    private PedidoEntity idPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    private PlatosEntity idPlato;
    private Integer cantidad;


}
