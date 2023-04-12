package com.pragma.powerup.domain.api.platos;

import com.pragma.powerup.domain.model.Pedido;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.model.Restaurante;

import java.util.List;

public interface IPlatosServicePort {
    void savePlato(Platos  platos);
    Platos findByNombre(String nombre);
    Platos findById(Long id);

    List<Platos> getAllPlatosPaginadosPorRestaurante(String nombreRestaurante,int page, int size);
}
