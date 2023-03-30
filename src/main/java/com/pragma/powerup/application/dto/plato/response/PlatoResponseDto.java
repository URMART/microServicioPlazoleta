package com.pragma.powerup.application.dto.plato.response;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Restaurante;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long precio;
    private String urlImagen;
    private Boolean activo;

    private Categoria categoria;
    private Restaurante restaurante;

}
