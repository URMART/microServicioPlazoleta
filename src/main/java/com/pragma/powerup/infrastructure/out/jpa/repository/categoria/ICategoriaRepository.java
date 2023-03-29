package com.pragma.powerup.infrastructure.out.jpa.repository.categoria;


import com.pragma.powerup.infrastructure.out.jpa.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<CategoriaEntity,Long> {

    CategoriaEntity findByNombre(String nombre);

}
