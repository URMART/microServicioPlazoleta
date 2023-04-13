package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.infrastructure.out.jpa.microservicios.feing.modelsmicroservice.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "restaurantes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestauranteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "El campo nombre no puede estar vacío")
    @Pattern(regexp = ".*[^0-9].*", message = "El campo nombre debe contener al menos un caracter no numérico")
    @Column(unique = true)
    private String nombre;
    @NotEmpty
    private String direccion;
    @NotEmpty
    private String urlLogo;
    @NotNull
    private Long nit;
    @Pattern(regexp="^\\+?[\\d]{1,13}$", message="El teléfono debe tener un máximo de 13 caracteres " +
            "y puede contener el símbolo '+'")
    private String telefono;
    @NotNull
    private Long idPropietario;

    @Transient
    private Usuarios usuarios;
}
