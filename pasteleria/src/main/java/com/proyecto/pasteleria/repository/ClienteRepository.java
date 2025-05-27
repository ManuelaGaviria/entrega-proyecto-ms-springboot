package com.proyecto.pasteleria.repository;

import com.proyecto.pasteleria.dto.reportes.ClientePedidoReporteDTO;
import com.proyecto.pasteleria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);

    @Query("""
        SELECT COUNT(p) 
        FROM Pedido p 
        WHERE p.cliente.id = :clienteId 
        AND p.estadoActual.nombre IN ('Solicitado', 'Confirmado', 'En Producción')
    """)
    long contarPedidosActivos(Long clienteId);

    @Query("SELECT new com.proyecto.pasteleria.dto.reportes.ClientePedidoReporteDTO(c.nombreCompleto, COUNT(p.id)) " +
            "FROM Cliente c LEFT JOIN c.pedidos p GROUP BY c.nombreCompleto")
    List<ClientePedidoReporteDTO> obtenerReporteClientesPedidos();

}
