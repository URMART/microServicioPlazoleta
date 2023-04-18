package com.pragma.powerup.application.handler.factory;

import com.pragma.powerup.application.dto.pedido.request.PedidoRequestDto;
import com.pragma.powerup.application.dto.pedido.response.PedidoResponseDto;
import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.infrastructure.out.jpa.entity.PedidoEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import org.joda.time.DateTime;

import java.time.LocalDateTime;

public class FactoryPedidoData {


    public  static Restaurante getRestauranteData(){
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        return  restaurante;
    }


    public  static Pedido getPedidoData(){
        Pedido pedido = new Pedido(1L, LocalDateTime.now(), Estados.PENDIENTE,getRestauranteData(),1L,1L);
        return  pedido;
    }

    public  static PedidoResponseDto getPedidoResponseDtoData(){

        PedidoResponseDto pedidoResponseDto = new PedidoResponseDto();

        pedidoResponseDto.setId(1L);
        pedidoResponseDto.setFecha(DateTime.now().toDate());
        pedidoResponseDto.setEstado(Estados.PENDIENTE);
        pedidoResponseDto.setIdRestaurante(getRestauranteData());
        pedidoResponseDto.setIdCliente(1L);
        pedidoResponseDto.setIdChef(1L);

        return  pedidoResponseDto;
    }

    public  static PedidoRequestDto getPedidoRequestDtoData(){


        PedidoRequestDto pedidoRequestDto = new PedidoRequestDto();
        pedidoRequestDto.setId(1L);
        pedidoRequestDto.setFecha(DateTime.now().toDate());
        pedidoRequestDto.setEstado(Estados.PENDIENTE);
        pedidoRequestDto.setIdRestaurante(getRestauranteData());
        pedidoRequestDto.setIdCliente(1L);
        pedidoRequestDto.setIdChef(1L);


        return  pedidoRequestDto;
    }

    public  static RestauranteEntity restauranteEntityData(){
        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(1L);

        return restauranteEntity;
    }


    public  static PedidoEntity getPedidoEntityData(){
        PedidoEntity pedido = new PedidoEntity(1L, LocalDateTime.now(), Estados.PENDIENTE,restauranteEntityData(),1L,1L);
        return  pedido;
    }


}
