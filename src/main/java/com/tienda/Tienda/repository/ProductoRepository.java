package com.tienda.Tienda.repository;

import com.tienda.Tienda.model.Categoria;
import com.tienda.Tienda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByDisponibleTrue();
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByCategoria(Categoria categoria);
}
