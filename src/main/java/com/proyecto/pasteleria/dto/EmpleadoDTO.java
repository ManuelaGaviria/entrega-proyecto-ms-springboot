package com.proyecto.pasteleria.dto;

import com.proyecto.pasteleria.validation.ValidRol;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El rol es obligatorio")
    @ValidRol
    private String rol;

    private String especialidad;
}
