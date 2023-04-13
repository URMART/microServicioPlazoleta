package com.pragma.powerup.domain.usecase.restaurante;

import com.pragma.powerup.domain.api.restaurante.IRestauranteServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.domain.spi.restaurante.IRestaurantePersistencePort;

import java.util.List;

public class RestauranteUseCase implements IRestauranteServicePort {

    private final IRestaurantePersistencePort restaurantePersistencePort;

    public RestauranteUseCase(IRestaurantePersistencePort objectPersistencePort) {
        this.restaurantePersistencePort = objectPersistencePort;
    }


    @Override
    public void saveRestaurante(Restaurante restaurante) {
        if(restaurante.getNombre() == null || restaurante.getDireccion() == null || restaurante.getNit() == null
                || restaurante.getTelefono() == null || restaurante.getIdPropietario() == null
                || restaurante.getUrlLogo() == null)
        {

            throw  new DomainException("las datos no pueden ser nulos ");
        }
                restaurantePersistencePort.saveRestaurante(restaurante);


    }

    @Override
    public List<Restaurante> getAllRestaurantes() {
        return restaurantePersistencePort.getAllRestaurantes();
    }

    @Override
    public List<Restaurante> getAllRestaurantesPaginados(int page,int size) {
        return restaurantePersistencePort.getAllRestaurantesPaginados(page,size);
    }

    @Override
    public Restaurante findById(Long id) {
        return restaurantePersistencePort.findById(id);
    }

    @Override
    public Restaurante findByNombre(String nombre) {
        return restaurantePersistencePort.findByNombre(nombre);
    }
}