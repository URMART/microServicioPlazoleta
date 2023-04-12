package com.pragma.powerup.application.dto.pedido.request;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Restaurante;
import lombok.Data;

import java.util.Date;

@Data
public class PedidoRequestDto {

    private Long id;
    private Estados estado;

    private Date fecha;

    private Restaurante idRestaurante;
    private Long idCliente;
    private Long idChef;
}
