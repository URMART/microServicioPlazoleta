package com.pragma.powerup.domain.usecase.platos;

import com.pragma.powerup.domain.api.platos.IPlatosServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.domain.spi.platos.IPlatosPersistencePort;

import java.util.List;

public class PlatosUseCase implements IPlatosServicePort {

    private final IPlatosPersistencePort platosPersistencePort;

    public PlatosUseCase(IPlatosPersistencePort platosPersistencePort) {
        this.platosPersistencePort = platosPersistencePort;
    }

    @Override
    public void savePlato(Platos platos) {

        try {
            if(platos.getActivo() == null){
                platos.setActivo(true);
                platosPersistencePort.savePlato(platos);
            }else{
                platosPersistencePort.savePlato(platos);
            }


        }catch (DomainException e) {
            throw new DomainException("Error al guardar el plato en el dominio ");
        }
    }

    @Override
    public Platos findByNombre(String nombre) {
        try {

            return platosPersistencePort.findByNombre(nombre);
        }catch (DomainException e) {
            throw new DomainException("Error al buscar el plato en el dominio ");
        }
    }

    @Override
    public Platos findById(Long id) {
        return platosPersistencePort.findById(id);
    }

    @Override
    public List<Platos> getAllPlatosPaginadosPorRestaurante(String nombreRestaurante, int page, int size) {
        return platosPersistencePort.getAllPlatosPaginadosPorRestaurante(nombreRestaurante, page, size);
    }
}
