package com.pragma.powerup.application.handler.categoria.imp;

import com.pragma.powerup.application.dto.categoria.response.CategoriaResponseDto;
import com.pragma.powerup.application.handler.categoria.ICategoriaHandler;
import com.pragma.powerup.application.mapper.categoria.ICategoriaResponseMapper;
import com.pragma.powerup.domain.api.categoria.ICategoriaServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaHandlerImp implements ICategoriaHandler {

    private final ICategoriaServicePort categoriaServicePort;
    private final ICategoriaResponseMapper categoriaResponseMapper;
    @Override
    public CategoriaResponseDto findByNombre(String nombre) {
        return categoriaResponseMapper.toCategoriaDto(categoriaServicePort.findByNombre(nombre));
    }
}
