package com.tienda.Tienda.service;

import com.tienda.Tienda.dto.CategoriaDTO;
import com.tienda.Tienda.dto.ProductoDTO;
import com.tienda.Tienda.dto.ProductoRequestDTO;
import com.tienda.Tienda.exception.CategoriaNotFoundException;
import com.tienda.Tienda.exception.ProductoNotFoundException;
import com.tienda.Tienda.model.Categoria;
import com.tienda.Tienda.model.Producto;
import com.tienda.Tienda.repository.CategoriaRepository;
import com.tienda.Tienda.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoDTO> obtenerTodos() {
        return productoRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        return toDTO(productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id)));
    }

    @Override
    public ProductoDTO crear(ProductoRequestDTO request) {
        var categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException(request.categoriaId()));
        var producto = Producto.builder()
                .nombre(request.nombre())
                .precio(request.precio())
                .stock(request.stock())
                .disponible(request.disponible())
                .categoria(categoria)
                .build();
        return toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoRequestDTO request) {
        var existente = productoRepository.findById(id)
                .orElseThrow(()->new ProductoNotFoundException(id));
        var categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException(request.categoriaId()));
        existente.setNombre(request.nombre());
        existente.setPrecio(request.precio());
        existente.setStock(request.stock());
        existente.setDisponible(request.disponible());
        existente.setCategoria(categoria);
        return toDTO(productoRepository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)){
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductoDTO> obtenerDisponibles() {
        return productoRepository.findByDisponibleTrue().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ProductoDTO> obtenerPorCategoria(Long categoriaId) {
        var categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new CategoriaNotFoundException(categoriaId));
        return productoRepository.findByCategoria(categoria).stream()
                .map(this::toDTO)
                .toList();
    }

    private ProductoDTO toDTO(Producto producto){
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getDisponible(),
                producto.getCategoria().getNombre()
        );
    }
}