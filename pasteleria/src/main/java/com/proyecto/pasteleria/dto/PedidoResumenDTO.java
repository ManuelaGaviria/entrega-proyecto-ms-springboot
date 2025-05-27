package com.proyecto.pasteleria.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResumenDTO {
    private Long id;
    private String clienteNombre;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntregaEstimada;
    private Double totalPagar;
}
