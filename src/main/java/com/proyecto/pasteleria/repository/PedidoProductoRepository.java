package com.proyecto.pasteleria.repository;

import com.proyecto.pasteleria.dto.reportes.ProductoMasVendidoDTO;
import com.proyecto.pasteleria.model.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Long> {

    @Query("""
        SELECT new com.proyecto.pasteleria.dto.reportes.ProductoMasVendidoDTO(
            p.producto.id, 
            p.producto.nombre, 
            SUM(p.cantidad)
        )
        FROM PedidoProducto p
        WHERE p.pedido.fechaCreacion >= :inicioMes
        GROUP BY p.producto.id, p.producto.nombre
        ORDER BY SUM(p.cantidad) DESC
    """)
    List<ProductoMasVendidoDTO> obtenerProductosMasVendidosEnElMes(LocalDateTime inicioMes);
}
