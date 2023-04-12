package com.pragma.powerup.application.dto.pedido.response;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Restaurante;
import lombok.Data;

import java.util.Date;
@Data
public class PedidoResponseDto {
    private Long id;
    private Date fecha;
    private Estados estado;
    private Restaurante idRestaurante;
    private Long idCliente;
    private Long idChef;

    @Override
    public String toString() {
        return "PedidoResponseDto{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", idRestaurante=" + idRestaurante +
                ", idCliente=" + idCliente +
                ", idChef=" + idChef +
                '}';
    }
}
