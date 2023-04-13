package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "platos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlatosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String descripcion;
    @NotNull
    @Positive
    private Long precio;
    @NotEmpty
    private String urlImagen;


    private Boolean activo;


    @ManyToOne(cascade = CascadeType.ALL)
    private CategoriaEntity categoria;

    @ManyToOne(cascade = CascadeType.ALL)
    private RestauranteEntity restaurante;
}
