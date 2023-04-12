package com.pragma.powerup.infrastructure.input.rest.pedido.clientecontroller;

import com.pragma.powerup.application.dto.pedido.request.PedidoRequestDto;
import com.pragma.powerup.application.dto.pedido.response.PedidoResponseDto;
import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestDto;
import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestGuardar;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.application.handler.pedido.IPedidosHandler;
import com.pragma.powerup.application.handler.pedidosplatos.IPedidosPlatosHandler;
import com.pragma.powerup.application.handler.platos.IPlatosHandler;
import com.pragma.powerup.application.handler.restaurante.IRestauranteHandler;
import com.pragma.powerup.application.mapper.pedido.IPedidoResponseMapper;
import com.pragma.powerup.application.mapper.pedidoplatos.IPedidoPlatosResponseMapper;
import com.pragma.powerup.application.mapper.platos.IPlatoResponseMapper;
import com.pragma.powerup.application.mapper.restaurante.IRestauranteResponseMapper;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.PedidosPlatos;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.client.UsuariosClient;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.modelsmicroservice.Usuarios;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurante/auth/cliente")
@RequiredArgsConstructor
public class ClienteControllerPedido {

    private final IPedidosHandler pedidosHandler;
    private final IPlatosHandler platosHandler;
    private final IPedidosPlatosHandler pedidosPlatosHandler;

    private final IPedidoResponseMapper pedidoResponseMapper;

    private final IPlatoResponseMapper platoResponseMapper;
    private final IRestauranteHandler restauranteHandler;
    private final IRestauranteResponseMapper  restauranteResponseMapper;

    private final UsuariosClient usuariosClient;


    @PostMapping("/createPedido")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<Void> savePedido(@Valid @RequestBody PedidoPlatoRequestGuardar pedidoPlatoRequestGuardar)
            throws Exception {


        Usuarios cliente =  usuariosClient.findById(pedidoPlatoRequestGuardar.getIdCliente());


        RestauranteResponseDto restaurante = restauranteHandler
                .findByNombre(pedidoPlatoRequestGuardar.getIdRestaurante().getNombre());


        PlatoResponseDto platoAGuardar = platosHandler.findById(pedidoPlatoRequestGuardar.getPlato().getId());

        if (restaurante == null || cliente == null ) {
            throw  new DomainException("No se Puede Realizar el pedido ");
        }

        if(platoAGuardar.getRestaurante().getNombre() != restaurante.getNombre()){
            throw  new DomainException("El plato no pertenece al restaurante ");
        }


        List<Estados> estados = new ArrayList<Estados>();
        estados.add(Estados.PENDIENTE);
        estados.add(Estados.EN_PREPARACION);
        estados.add(Estados.LISTO);


        for (Estados estado : estados ){
            PedidoResponseDto pedidoCliente = pedidosHandler.findPedidoCliente(cliente.getId(), estado);
            if ( pedidoCliente!=null){
                throw  new DomainException("No se Puede Realizar el pedido por que su estado es " + estado);
            }
        }


        if(restaurante != null && cliente != null){
            //guardar platos
            PedidoRequestDto pedido = new PedidoRequestDto();
            pedido.setIdRestaurante(restauranteResponseMapper.toRestauranteModel(restaurante));
            pedido.setEstado(Estados.PENDIENTE);
            pedido.setIdCliente(cliente.getId());
            PedidoResponseDto pedidoAGuardar = pedidosHandler.savePedido(pedido);

            // guardar pedidos_platos
            PedidoPlatoRequestDto pedidosPlatos = new PedidoPlatoRequestDto();
            pedidosPlatos.setIdPedido(pedidoResponseMapper.toPedidoModel(pedidoAGuardar));
            pedidosPlatos.setIdPlato(platoResponseMapper.toPlatoModel(platoAGuardar));
            pedidosPlatos.setCantidad(pedidoPlatoRequestGuardar.getCantidad());
            pedidosPlatosHandler.savePedidosPlatos(pedidosPlatos);

            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.CONFLICT);


    }


    @PostMapping("/agregarPlatoPedido/{idCliente}")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public   ResponseEntity<Void> agregarPlatoPedido(@Valid @PathVariable Long idCliente,@RequestBody PedidoPlatoRequestDto pedidoPlatoRequestDto){
        Usuarios cliente =  usuariosClient.findById(idCliente);

        PlatoResponseDto platoAGuardar = platosHandler.findById(pedidoPlatoRequestDto.getIdPlato().getId());

        PedidoResponseDto pedidoCliente = pedidosHandler.findPedidoCliente(cliente.getId(), Estados.PENDIENTE);

        if(cliente == null){throw  new DomainException("El cliente no existe");}
        else if (platoAGuardar == null){throw new DomainException("El plato no existe");}
        else if (pedidoPlatoRequestDto.getCantidad()<0){throw new DomainException("la cantidad del plato no puede ser 0");}

        if ( pedidoCliente!=null &&  pedidoCliente.getIdCliente() == cliente.getId()){

        // guardar nuevo plato al pedido
            PedidoPlatoRequestDto pedidosPlatosNuevo = new PedidoPlatoRequestDto();
            pedidosPlatosNuevo.setIdPedido(pedidoResponseMapper.toPedidoModel(pedidoCliente));
            pedidosPlatosNuevo.setIdPlato(platoResponseMapper.toPlatoModel(platoAGuardar));
            pedidosPlatosNuevo.setCantidad(pedidoPlatoRequestDto.getCantidad());
            pedidosPlatosHandler.savePedidosPlatos(pedidosPlatosNuevo);

            return  new ResponseEntity<>(HttpStatus.OK);

        }
        return  new ResponseEntity<>(HttpStatus.CONFLICT);

        }

}