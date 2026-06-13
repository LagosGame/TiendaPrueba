package com.tienda.Tienda.controller;


import com.tienda.Tienda.dto.CategoriaDTO;
import com.tienda.Tienda.dto.CategoriaRequestDTO;
import com.tienda.Tienda.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerTodas(){
        return  ResponseEntity.ok(categoryService.obtenerTodas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.obtenerPorId(id));
    }
    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@Valid @RequestBody CategoriaRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.crear(requestDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO requestDTO){
        return ResponseEntity.ok(categoryService.actualizar(id,requestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id){
        categoryService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

