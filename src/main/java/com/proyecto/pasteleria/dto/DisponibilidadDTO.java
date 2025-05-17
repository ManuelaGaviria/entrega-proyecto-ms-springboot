package com.proyecto.pasteleria.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadDTO {
    @NotNull(message = "El valor de disponibilidad es obligatorio")
    private Boolean disponibilidad;
}
