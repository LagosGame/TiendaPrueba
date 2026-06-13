package com.tienda.Tienda.dto;


public record ProductoDTO(
        Long id,
        String nombre,
        Double precio,
        Integer stock,
        Boolean disponible,
        String categoriaNombre
) {
}
