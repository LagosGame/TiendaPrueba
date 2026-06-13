package com.tienda.Tienda.service;


import com.tienda.Tienda.dto.CategoriaDTO;
import com.tienda.Tienda.dto.CategoriaRequestDTO;
import com.tienda.Tienda.exception.CategoriaNotFoundException;
import com.tienda.Tienda.model.Categoria;
import com.tienda.Tienda.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoriaRepository categoriaRepository;
    @Override
    public List<CategoriaDTO> obtenerTodas() {
        return categoriaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CategoriaDTO obtenerPorId(Long id) {
        return toDTO(categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id)));
    }

    @Override
    public CategoriaDTO crear(CategoriaRequestDTO requestDTO) {
        var categoria = Categoria.builder()
                .nombre(requestDTO.nombre())
                .descripcion(requestDTO.descripcion())
                .build();
        return toDTO(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaDTO actualizar(Long id, CategoriaRequestDTO requestDTO) {
        var existente = categoriaRepository.findById(id)
                .orElseThrow(()-> new CategoriaNotFoundException(id));
        existente.setNombre(requestDTO.nombre());
        existente.setDescripcion(requestDTO.descripcion());
        return toDTO(categoriaRepository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
    if (!categoriaRepository.existsById(id)){
        throw new CategoriaNotFoundException(id);
    }
    categoriaRepository.deleteById(id);
    }

    private CategoriaDTO toDTO(Categoria categoria){
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}
