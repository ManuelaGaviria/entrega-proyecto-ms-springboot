package com.proyecto.pasteleria.dto.reportes;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCanceladoDTO {
    private Long pedidoId;
    private String cliente;
    private LocalDateTime fechaCancelacion;
    private String comentario;
}
