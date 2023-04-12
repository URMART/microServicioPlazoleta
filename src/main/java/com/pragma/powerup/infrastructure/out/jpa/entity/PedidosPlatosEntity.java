package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Platos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(cascade =  CascadeType.REMOVE)
    private PedidoEntity idPedido;

    @ManyToOne(cascade =  CascadeType.REMOVE)
    private PlatosEntity idPlato;
    private Integer cantidad;


}
