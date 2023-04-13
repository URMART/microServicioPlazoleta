package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "restaurante_empleado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestauranteEmpleadoEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotNull
    private Long idRestaurante;
    @NotNull
    private Long idEmpleado;
}
