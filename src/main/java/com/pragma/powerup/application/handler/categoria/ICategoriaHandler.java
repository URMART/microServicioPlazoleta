package com.pragma.powerup.application.handler.categoria;

import com.pragma.powerup.application.dto.categoria.response.CategoriaResponseDto;

public interface ICategoriaHandler {

    CategoriaResponseDto findByNombre(String nombre);

}
