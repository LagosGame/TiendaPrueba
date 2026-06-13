package com.tienda.Tienda.exception;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(Long id) {
        super("Categoría no encontrada con id: " + id);
    }
}
