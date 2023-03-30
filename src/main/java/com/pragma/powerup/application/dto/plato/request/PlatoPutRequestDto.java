package com.pragma.powerup.application.dto.plato.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoPutRequestDto {
    private Long id;
    private String descripcion;
    private Long precio;

}
