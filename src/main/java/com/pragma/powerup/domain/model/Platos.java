package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Platos {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long precio;
    private String urlImagen;
    private Boolean activo;

    private Categoria categoria;
    private Restaurante restaurante;

}
