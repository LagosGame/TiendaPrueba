package com.tienda.Tienda.dto;


import jakarta.validation.constraints.*;

public record ProductoRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío.")
        @Size(min = 3, max = 100, message = "El mensaje debe tener entre 3 y 100 caracteres")
        String nombre,
        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor que 0")
        Double precio,
        @NotNull(message = "El stock debe ser obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,
        @NotNull(message = "El campo disponible es obligatorio")
        Boolean disponible,
        @NotNull(message = "La categoria es obligatoria")
        @Positive(message = "El id debe ser mayor que 0")
        Long categoriaId
) {
}
