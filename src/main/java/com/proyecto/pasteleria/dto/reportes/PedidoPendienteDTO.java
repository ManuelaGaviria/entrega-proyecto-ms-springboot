package com.proyecto.pasteleria.dto.reportes;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPendienteDTO {
    private Long id;
    private String clienteNombre;
    private String estado;
    private LocalDateTime fechaEntregaEstimada;
}
