package com.pragma.powerup.infrastructure.out.jpa.microservicios.modelsmicroservice;


import lombok.Data;

@Data
public class Usuarios {

    private Long id;
    private String nombre;
    private String apellido;
    private Long documentoIdentidad;

    private String celular;
    private String email;
    private String clave;




}
