package com.pragma.powerup.application.dto.plato.request;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Restaurante;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoRequestDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long precio;
    private String urlImagen;
    private Boolean activo;

    private Categoria categoria;
    private Restaurante restaurante;
}
