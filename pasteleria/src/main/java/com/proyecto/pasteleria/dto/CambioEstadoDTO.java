package com.proyecto.pasteleria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CambioEstadoDTO {

    @NotBlank(message = "El nuevo estado es obligatorio")
    private String nuevoEstado;

    @NotNull(message = "Debe indicar el ID del empleado que realiza el cambio")
    private Long empleadoId;

    private String comentario;
}
