package com.pragma.powerup.application.handler.factory;

import com.pragma.powerup.application.dto.plato.request.PlatoRequestDto;
import com.pragma.powerup.application.dto.plato.response.PlatoResponseDto;
import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.domain.model.Platos;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlatosEntity;

public class FactoryPlatosData {

    public static Categoria categoriaData(){

        Categoria categoria = new Categoria();
        categoria.setId(1L);

        return categoria;
    }


    public static  Platos platosData(){
        Platos plato = new Platos();
        plato.setId(1L);
        plato.setNombre("pastas");
        plato.setDescripcion("pastas doria");
        plato.setPrecio(20000L);
        plato.setUrlImagen("http://www.img.com");
        plato.setActivo(true);
        plato.setRestaurante(FactoryPedidoData.getRestauranteData());
        plato.setCategoria(categoriaData());
        return plato;
    }


    public static  PlatoResponseDto platoResponseDtoData(){
        PlatoResponseDto platoResponseDto = new PlatoResponseDto();
        platoResponseDto.setId(1L);
        platoResponseDto.setNombre("pastas");
        platoResponseDto.setDescripcion("pastas doria");
        platoResponseDto.setPrecio(20000L);
        platoResponseDto.setUrlImagen("http://www.img.com");
        platoResponseDto.setActivo(true);
        platoResponseDto.setRestaurante(FactoryPedidoData.getRestauranteData());
        platoResponseDto.setCategoria(categoriaData());


        return platoResponseDto;
    }

    public  static PlatoRequestDto platoRequestDtoData(){
        PlatoRequestDto platoRequestDto = new PlatoRequestDto();
        platoRequestDto.setId(1L);
        platoRequestDto.setNombre("pastas");
        platoRequestDto.setDescripcion("pastas doria");
        platoRequestDto.setPrecio(20000L);
        platoRequestDto.setUrlImagen("http://www.img.com");
        platoRequestDto.setActivo(true);
        platoRequestDto.setRestaurante(FactoryPedidoData.getRestauranteData());
        platoRequestDto.setCategoria(categoriaData());
        return  platoRequestDto;
    }

    public static CategoriaEntity categoriaEntityData(){

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(1L);

        return categoria;
    }

    public static PlatosEntity platosEntityData(){
        PlatosEntity plato = new PlatosEntity();
        plato.setId(1L);
        plato.setNombre("pastas");
        plato.setDescripcion("pastas doria");
        plato.setPrecio(20000L);
        plato.setUrlImagen("http://www.img.com");
        plato.setActivo(true);
        plato.setRestaurante(FactoryPedidoData.restauranteEntityData());
        plato.setCategoria(categoriaEntityData());
        return plato;
    }
}
