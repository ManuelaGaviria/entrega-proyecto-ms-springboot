package com.proyecto.pasteleria.repository;

import com.proyecto.pasteleria.dto.reportes.PedidoCanceladoDTO;
import com.proyecto.pasteleria.dto.reportes.PedidoEntregadoDTO;
import com.proyecto.pasteleria.model.HistorialEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface HistorialEstadoRepository extends JpaRepository<HistorialEstado, Long> {

    @Query("""
    SELECT new com.proyecto.pasteleria.dto.reportes.PedidoCanceladoDTO(
        h.pedido.id,
        h.pedido.cliente.nombreCompleto,
        h.fechaCambio,
        h.comentario
    )
    FROM HistorialEstado h
    WHERE h.estado.nombre = 'Cancelado'
      AND h.fechaCambio BETWEEN :desde AND :hasta
    ORDER BY h.fechaCambio DESC
""")
    List<PedidoCanceladoDTO> obtenerPedidosCanceladosEnPeriodo(LocalDateTime desde, LocalDateTime hasta);

    @Query("""
    SELECT new com.proyecto.pasteleria.dto.reportes.PedidoEntregadoDTO(
        h.pedido.id,
        h.pedido.cliente.nombreCompleto,
        h.fechaCambio,
        h.pedido.totalPagar
    )
    FROM HistorialEstado h
    WHERE h.estado.nombre = 'Entregado'
      AND h.fechaCambio >= :inicioMes
""")
    List<PedidoEntregadoDTO> obtenerPedidosEntregadosUltimoMes(LocalDateTime inicioMes);
}
