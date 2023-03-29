package com.pragma.powerup.application.dto.restaurante.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteRequestDto {
    private Long id;
    private String nombre;
    private String direccion;
    private String urlLogo;
    private Long nit;
    private String telefono;

    private Long idPropietario;
}
