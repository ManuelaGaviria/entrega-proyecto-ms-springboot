package com.proyecto.pasteleria.dto.reportes;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoMasVendidoDTO {
    private Long productoId;
    private String nombre;
    private Long cantidadVendida;
}
