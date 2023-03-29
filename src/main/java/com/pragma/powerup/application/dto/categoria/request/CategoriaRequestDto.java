package com.pragma.powerup.application.dto.categoria.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequestDto {
    private Long id;
    private String nombre;
    private String descripcion;
}
