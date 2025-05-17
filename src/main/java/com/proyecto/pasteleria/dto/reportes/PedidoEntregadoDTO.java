package com.proyecto.pasteleria.dto.reportes;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntregadoDTO {
    private Long pedidoId;
    private String cliente;
    private LocalDateTime fechaEntrega;
    private Double totalPagar;
}
