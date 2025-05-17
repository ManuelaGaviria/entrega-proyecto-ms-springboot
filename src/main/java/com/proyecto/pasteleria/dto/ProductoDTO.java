package com.proyecto.pasteleria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio base es obligatorio")
    @Min(value = 0, message = "El precio base no puede ser negativo")
    private Double precioBase;

    @NotNull(message = "La disponibilidad debe especificarse")
    private Boolean disponibilidad;
}
