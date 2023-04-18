package com.pragma.powerup.application.handler.factory;

import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestDto;
import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestGuardar;
import com.pragma.powerup.application.dto.pedidoplatos.response.PedidoPlatoResponseDto;
import com.pragma.powerup.domain.model.*;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidosPlatosEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;
import org.joda.time.DateTime;

public class FactoryPedidosPlatosData {



    public static Pedido getPedidoData(){

        Pedido pedido = new Pedido();
        pedido.setId(1L);

        return  pedido;
    }

    public  static Platos getPlatoData(){
        Platos plato = new Platos();
        plato.setId(1L);

        return  plato;
    }
    public  static PedidosPlatos getPedidosPlatosData(){
        PedidosPlatos  pedidosPlatos = new PedidosPlatos();
        pedidosPlatos.setIdPedidosPlatos(1L);
        pedidosPlatos.setIdPedido(getPedidoData());
        pedidosPlatos.setIdPlato(getPlatoData());
        pedidosPlatos.setCantidad(1);

        return pedidosPlatos;
    }

    public  static PedidoPlatoRequestDto pedidoPlatoRequestDtoData(){

        PedidoPlatoRequestDto pedidoPlatoRequestDto = new PedidoPlatoRequestDto();
        pedidoPlatoRequestDto.setIdPedidosPlatos(1L);
        pedidoPlatoRequestDto.setIdPedido(getPedidoData());
        pedidoPlatoRequestDto.setIdPlato(getPlatoData());
        pedidoPlatoRequestDto.setCantidad(1);

        return pedidoPlatoRequestDto;
    }

    public  static PedidoPlatoResponseDto pedidoPlatoResponseDtoData(){

        PedidoPlatoResponseDto pedidoPlatoResponseDto = new PedidoPlatoResponseDto();
        pedidoPlatoResponseDto.setIdPedidosPlatos(1L);
        pedidoPlatoResponseDto.setIdPedido(getPedidoData());
        pedidoPlatoResponseDto.setIdPlato(getPlatoData());
        pedidoPlatoResponseDto.setCantidad(1);

        return pedidoPlatoResponseDto;
    }

    public static PedidoEntity getPedidoEntityData(){

        PedidoEntity pedido = new PedidoEntity();
        pedido.setId(1L);

        return  pedido;
    }

    public  static PlatosEntity getPlatoEntityData(){
        PlatosEntity plato = new PlatosEntity();
        plato.setId(1L);

        return  plato;
    }


    public  static PedidosPlatosEntity getPedidosPlatosEntityData(){
        PedidosPlatosEntity  pedidosPlatos = new PedidosPlatosEntity();
        pedidosPlatos.setIdPedidosPlatos(1L);
        pedidosPlatos.setIdPedido(getPedidoEntityData());
        pedidosPlatos.setIdPlato(getPlatoEntityData());
        pedidosPlatos.setCantidad(1);

        return pedidosPlatos;
    }

}
