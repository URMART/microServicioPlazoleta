package com.pragma.powerup.application.dto.pedidoplatos.request;

import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.model.Restaurante;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PedidoPlatoRequestGuardar {

    private Long id;
    private Estados estado;

    private Date fecha;

    private Restaurante idRestaurante;
    private Long idCliente;
    private Long idChef;

    private Platos plato;

    private Integer Cantidad;


}
