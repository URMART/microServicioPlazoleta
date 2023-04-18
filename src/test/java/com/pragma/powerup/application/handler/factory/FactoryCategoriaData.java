package com.pragma.powerup.application.handler.factory;

import com.pragma.powerup.application.dto.categoria.response.CategoriaResponseDto;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;

public class FactoryCategoriaData {

    public static Categoria getCategoriaData(){

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("arroz");
        categoria.setDescripcion("arroz variado");

        return  categoria;

    }


    public  static CategoriaResponseDto getCategoriaResponseDto(){

       CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto();
        categoriaResponseDto.setId(1L);
        categoriaResponseDto.setNombre("arroz");
        categoriaResponseDto.setDescripcion("arroz variado");

        return  categoriaResponseDto;
    }

    public  static CategoriaEntity categoriaEntityData(){
        CategoriaEntity  categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(1L);
        categoriaEntity.setNombre("arroz");
        categoriaEntity.setDescripcion("arroz variado");


        return categoriaEntity;
    }
}
