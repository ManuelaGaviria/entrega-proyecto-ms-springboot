package com.proyecto.pasteleria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialEstado {

    private Long id;
    private Long empleadoId;
    private Long pedidoId;
    private Long estadoId;
    private LocalDateTime fechaCambio;
    private String comentario;

}
