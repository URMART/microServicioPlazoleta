package com.pragma.powerup.application.dto.pedido.request;

import com.pragma.powerup.domain.model.Codigo;
import com.pragma.powerup.domain.model.Estados;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestCambiarEstadoYSms {


    private List<Long> idPedido;

    private Codigo codigo;

}
