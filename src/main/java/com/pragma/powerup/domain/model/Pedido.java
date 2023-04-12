package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private Long id;

    private LocalDateTime fecha;

    private Estados estado;

    private Restaurante idRestaurante;
    private Long idCliente;
    private Long idChef;

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", idRestaurante=" + idRestaurante +
                ", idCliente=" + idCliente +
                ", idChef=" + idChef +
                '}';
    }
}
