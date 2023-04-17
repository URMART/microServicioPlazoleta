package com.pragma.powerup.infrastructure.input.rest.pedido.clientecontroller;

import com.pragma.powerup.application.dto.pedido.request.PedidoRequestDto;
import com.pragma.powerup.application.dto.pedido.response.PedidoResponseDto;
import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestDto;
import com.pragma.powerup.application.dto.pedidoplatos.request.PedidoPlatoRequestGuardar;
import com.pragma.powerup.application.dto.pedidoplatos.response.PedidoPlatoResponseDto;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.application.dto.restaurante.response.RestauranteResponseDto;
import com.pragma.powerup.application.handler.pedido.IPedidosHandler;
import com.pragma.powerup.application.handler.pedidosplatos.IPedidosPlatosHandler;
import com.pragma.powerup.application.handler.platos.IPlatosHandler;
import com.pragma.powerup.application.handler.restaurante.IRestauranteHandler;
import com.pragma.powerup.application.mapper.pedido.IPedidoResponseMapper;
import com.pragma.powerup.application.mapper.platos.IPlatoResponseMapper;
import com.pragma.powerup.application.mapper.restaurante.IRestauranteResponseMapper;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Estados;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.client.UsuariosClient;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.modelsmicroservice.Usuarios;
import com.pragma.powerup.infrastructure.out.jpa.microservicios.twilio.clientwilio.TwilioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/plazoleta/auth/cliente")
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
    private final TwilioClient twilioClient;





    @PostMapping("/crearPedido")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<Void> savePedido(@Valid @RequestBody PedidoPlatoRequestGuardar pedidoPlatoRequestGuardar)
            throws Exception {

        try {

            Usuarios cliente =  usuariosClient.findById(pedidoPlatoRequestGuardar.getIdCliente());

            RestauranteResponseDto restaurante = restauranteHandler
                    .findByNombre(pedidoPlatoRequestGuardar.getIdRestaurante().getNombre());

            PlatoResponseDto platoAGuardar = platosHandler.findById(pedidoPlatoRequestGuardar.getPlato().getId());

            if (restaurante == null ||
                    cliente.getRol().getId()  != 4 ||
                    pedidoPlatoRequestGuardar.getCantidad() <= 0) {
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
                    throw  new DomainException("No se Puede Realizar el pedido porque tienes un pedido en estado " + estado);
                }
            }

            if(restaurante != null && cliente != null){
                //guardar pedido
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
        }catch (Exception e){

            return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return  new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @PostMapping("/agregarPlatoPedido/{idCliente}")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public   ResponseEntity<Void> agregarPlatoPedido(@Valid @PathVariable Long idCliente,@RequestBody PedidoPlatoRequestDto pedidoPlatoRequestDto){

        try{

            Usuarios cliente =  usuariosClient.findById(idCliente);

            PlatoResponseDto platoAGuardar = platosHandler.findById(pedidoPlatoRequestDto.getIdPlato().getId());

            PedidoResponseDto pedidoCliente = pedidosHandler.findPedidoCliente(cliente.getId(), Estados.PENDIENTE);

            if (platoAGuardar == null){throw new DomainException("El plato no existe");}
            else if (pedidoPlatoRequestDto.getCantidad()<0){throw new DomainException("la cantidad del plato no puede ser 0");}

            if ( pedidoCliente!=null &&  pedidoCliente.getIdCliente() == cliente.getId() &&
                    platoAGuardar.getRestaurante().getId() == pedidoCliente.getIdRestaurante().getId()){

                // guardar nuevo plato al pedido
                PedidoPlatoRequestDto pedidosPlatosNuevo = new PedidoPlatoRequestDto();
                pedidosPlatosNuevo.setIdPedido(pedidoResponseMapper.toPedidoModel(pedidoCliente));
                pedidosPlatosNuevo.setIdPlato(platoResponseMapper.toPlatoModel(platoAGuardar));
                pedidosPlatosNuevo.setCantidad(pedidoPlatoRequestDto.getCantidad());
                pedidosPlatosHandler.savePedidosPlatos(pedidosPlatosNuevo);

                return  new ResponseEntity<>(HttpStatus.OK);

            }


        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    @DeleteMapping("/cancelarPedido/{idCliente}/{idPedido}")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public Object eliminarPorPorId(@PathVariable Long idCliente ,@PathVariable Long idPedido ) {
        try{
            Map<String, Object> response = new HashMap<>();
            Usuarios cliente = usuariosClient.findById(idCliente);

            PedidoResponseDto pedidoExitente = pedidosHandler.findById(idPedido);

            if (cliente == null) {
                response.put("menssage", "El  Cliente No Se Existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            if (pedidoExitente == null) {
                response.put("menssage", "El  pedido No Se Existe");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            if (pedidoExitente.getIdCliente() == cliente.getId()) {
                if (pedidoExitente.getEstado() == Estados.PENDIENTE) {

                    List<PedidoPlatoResponseDto> pedidosPlatosAEliminar = pedidosPlatosHandler
                            .findAll(pedidoExitente.getId());

                    for(PedidoPlatoResponseDto eliminar: pedidosPlatosAEliminar){
                        pedidosPlatosHandler.eliminarPedidoPlato(eliminar.getIdPedidosPlatos());
                    }

                    pedidosHandler.DeletePedido(pedidoExitente.getId());
                    response.put("menssage", "Eliminado");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    twilioClient.enviarSmsNoCancelarPedido();
                    response.put("menssage", "Lo sentimos, tu pedido ya esta en preparacion y no puede cancelarse");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
