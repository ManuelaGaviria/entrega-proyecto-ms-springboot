package com.proyecto.pasteleria.dto.reportes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientePedidoReporteDTO {
    private String nombre;
    private Long totalPedidos;
}
