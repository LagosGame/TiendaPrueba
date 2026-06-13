package com.tienda.Tienda.repository;

import com.tienda.Tienda.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombre (String nombre);
}
