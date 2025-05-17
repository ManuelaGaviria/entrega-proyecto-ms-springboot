package com.proyecto.pasteleria.dto.reportes;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportePedidosEntregadosDTO {
    private Double totalIngresos;
    private List<PedidoEntregadoDTO> pedidos;
}
