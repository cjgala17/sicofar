package com.idat.SISCOFAR.dto;

import jakarta.validation.constraints.*;

public record ProductoDTO(

		Long id,

		@NotBlank(message = "El nombre es obligatorio") @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres") String nombre,

		String descripcion,

		@NotNull(message = "El stock es obligatorio") @Min(value = 0, message = "El stock no puede ser negativo") Integer stock,

		@NotNull(message = "El precio es obligatorio") @Positive(message = "El precio debe ser un número positivo") Double precio) {
}