package com.tienda.Tienda.controller;




import com.tienda.Tienda.dto.ProductoDTO;
import com.tienda.Tienda.dto.ProductoRequestDTO;
import com.tienda.Tienda.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodos(){
        return ResponseEntity.ok(productoService.obtenerTodos());
    }


    @GetMapping("/disponibles")
    public ResponseEntity<List<ProductoDTO>> obtenerDisponibles(){
        return ResponseEntity.ok(productoService.obtenerDisponibles());
    }
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoDTO>> obtenerPorCategoria(@PathVariable Long categoriaId){
        return ResponseEntity.ok(productoService.obtenerPorCategoria(categoriaId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }
    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
