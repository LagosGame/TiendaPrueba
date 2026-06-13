package com.tienda.Tienda.service;

import com.tienda.Tienda.dto.CategoriaDTO;
import com.tienda.Tienda.dto.ProductoDTO;
import com.tienda.Tienda.dto.ProductoRequestDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> obtenerTodos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO crear(ProductoRequestDTO request);
    ProductoDTO actualizar(Long id, ProductoRequestDTO request);
    void eliminar (Long id);
    List<ProductoDTO> obtenerDisponibles();
    List<ProductoDTO> obtenerPorCategoria(Long categoriaId );
}
