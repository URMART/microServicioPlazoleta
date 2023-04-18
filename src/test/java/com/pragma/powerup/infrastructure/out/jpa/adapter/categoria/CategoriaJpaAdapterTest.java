package com.pragma.powerup.infrastructure.out.jpa.adapter.categoria;

import com.pragma.powerup.domain.model.Categoria;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.categoria.ICategoriaEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.categoria.ICategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaJpaAdapterTest {


    @Mock
    private ICategoriaEntityMapper categoriaEntityMapper;

    @Mock
    private ICategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaJpaAdapter categoriaJpaAdapter;

    private Categoria categoria;
    private CategoriaEntity categoriaEntity;

    @BeforeEach
    void setup() {

        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("arroz");
        categoria.setDescripcion("arroz variado");

        categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(1L);
        categoriaEntity.setNombre("arroz");
        categoriaEntity.setDescripcion("arroz variado");

    }

    @Test
    void findByNombre() {

        when(categoriaRepository.findByNombre(categoria.getNombre())).thenReturn(categoriaEntity);
        when(categoriaEntityMapper.toCategoriaModel(categoriaEntity)).thenReturn(categoria);

        Categoria categoriaEncontrada = categoriaJpaAdapter.findByNombre(categoria.getNombre());

        assertEquals(categoriaEncontrada.getNombre(),categoria.getNombre());
    }



}