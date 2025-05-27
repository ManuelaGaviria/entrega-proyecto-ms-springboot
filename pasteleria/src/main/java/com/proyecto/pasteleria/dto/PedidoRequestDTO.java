package com.proyecto.pasteleria.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "Debe especificar la fecha estimada de entrega")
    private LocalDateTime fechaEntregaEstimada;

    @NotEmpty(message = "Debe agregar al menos un producto al pedido")
    private List<DetalleProductoDTO> productos;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetalleProductoDTO {
        @NotNull(message = "Debe indicar el ID del producto")
        private Long productoId;

        @NotNull(message = "Debe especificar la cantidad")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        private Integer cantidad;

        private String personalizacion;
    }
}
