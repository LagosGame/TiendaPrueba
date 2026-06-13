package com.tienda.Tienda.service;

import com.tienda.Tienda.dto.CategoriaDTO;
import com.tienda.Tienda.dto.CategoriaRequestDTO;

import java.util.List;

public interface CategoryService {
    List<CategoriaDTO> obtenerTodas();
    CategoriaDTO obtenerPorId(Long id);
    CategoriaDTO crear(CategoriaRequestDTO requestDTO);
    CategoriaDTO actualizar(Long id, CategoriaRequestDTO requestDTO);
    void eliminar (Long id);
}
