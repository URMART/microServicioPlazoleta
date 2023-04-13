package com.pragma.powerup.infrastructure.out.jpa.adapter.restaurante;

import com.pragma.powerup.domain.model.Restaurante;
import com.pragma.powerup.domain.spi.restaurante.IRestaurantePersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestauranteEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.restaurante.IRestauranteEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.restaurante.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class RestauranteJpaAdapter implements IRestaurantePersistencePort {

    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;


    @Override
    public Restaurante saveRestaurante(Restaurante restaurante) {
        RestauranteEntity restauranteEntity= restauranteRepository
                .save(restauranteEntityMapper.toRestauranteEntity(restaurante));

        return restauranteEntityMapper.toRestauranteModel(restauranteEntity);
    }

    @Override
    public List<Restaurante> getAllRestaurantes() {

        List<RestauranteEntity> entityList = restauranteRepository.findAll();

        if (entityList.isEmpty()){
            throw  new NoDataFoundException();
        }

        return  restauranteEntityMapper.toRestauranteModelList(entityList);
    }

    @Override
    public List<Restaurante> getAllRestaurantesPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return restauranteEntityMapper
                .toRestauranteModelListPa(restauranteRepository
                .findAllRestaurantesConPaginacion(pageable));
    }

    @Override
    public Restaurante findById(Long id) {
        return restauranteEntityMapper
                .toRestauranteModel(restauranteRepository.findById(id).orElseThrow());
    }

    @Override
    public Restaurante findByNombre(String nombre) {
        return restauranteEntityMapper
                .toRestauranteModel(restauranteRepository.findByNombre(nombre));
    }
}