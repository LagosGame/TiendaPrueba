package com.tienda.Tienda.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(
        @NotBlank(message = "El nombre no puede estar vacío.")
        @Size(min = 3, max = 100, message = "El mensaje debe tener entre 3 y 100 caracteres")
        String nombre,
        @NotBlank(message = "El nombre no puede estar vacío.")
        @Size(min = 3, max = 500, message = "El mensaje debe tener entre 3 y 500 caracteres")
        String descripcion
) {
}
