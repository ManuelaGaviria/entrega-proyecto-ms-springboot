package com.proyecto.pasteleria.repository;

import com.proyecto.pasteleria.dto.reportes.PedidoPendienteDTO;
import com.proyecto.pasteleria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("""
    SELECT new com.proyecto.pasteleria.dto.reportes.PedidoPendienteDTO(
        p.id,
        p.cliente.nombreCompleto,
        p.estadoActual.nombre,
        p.fechaEntregaEstimada
    )
    FROM Pedido p
    WHERE p.estadoActual.nombre IN ('Confirmado', 'En Producción')
    ORDER BY p.fechaEntregaEstimada
""")
    List<PedidoPendienteDTO> obtenerPedidosPendientes();

    Long countByClienteIdAndEstadoActualNombreIn(Long clienteId, List<String> nombresEstados);
}