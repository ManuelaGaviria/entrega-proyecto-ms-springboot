package com.proyecto.pasteleria.service.impl;

import com.proyecto.pasteleria.dto.CambioEstadoDTO;
import com.proyecto.pasteleria.dto.PedidoRequestDTO;
import com.proyecto.pasteleria.dto.PedidoResumenDTO;
import com.proyecto.pasteleria.model.*;
import com.proyecto.pasteleria.repository.*;
import com.proyecto.pasteleria.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoProductoRepository pedidoProductoRepository;
    private final ClienteRepository clienteRepository;
    private final EstadoRepository estadoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final HistorialEstadoRepository historialEstadoRepository;

    @Override
    public void crearPedido(PedidoRequestDTO dto) {
        if (dto.getProductos() == null || dto.getProductos().isEmpty()) {
            throw new IllegalArgumentException("Debe incluir al menos un producto");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        Estado estadoSolicitado = estadoRepository.findByNombre("Solicitado")
                .orElseThrow(() -> new IllegalArgumentException("Estado 'Solicitado' no configurado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setFechaEntregaEstimada(dto.getFechaEntregaEstimada());
        pedido.setEstadoActual(estadoSolicitado);
        pedido.setTotalPagar(0.0); // lo calcularemos
        pedido = pedidoRepository.save(pedido);

        double total = 0;

        for (PedidoRequestDTO.DetalleProductoDTO item : dto.getProductos()) {
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            double precioFinal = producto.getPrecioBase(); // + extras si hubiera
            PedidoProducto pp = new PedidoProducto();
            pp.setPedido(pedido);
            pp.setProducto(producto);
            pp.setCantidad(item.getCantidad());
            pp.setPersonalizacion(item.getPersonalizacion());
            pp.setPrecioFinal(precioFinal);

            total += precioFinal * item.getCantidad();

            pedidoProductoRepository.save(pp);
        }

        pedido.setTotalPagar(total);
        pedidoRepository.save(pedido);
    }

    @Override
    public List<PedidoResumenDTO> obtenerTodos() {
        return pedidoRepository.findAll().stream()
                .map(p -> new PedidoResumenDTO(
                        p.getId(),
                        p.getCliente().getNombreCompleto(),
                        p.getEstadoActual().getNombre(),
                        p.getFechaCreacion(),
                        p.getFechaEntregaEstimada(),
                        p.getTotalPagar()
                ))
                .toList();
    }

    @Override
    public void cambiarEstado(Long pedidoId, CambioEstadoDTO dto) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));

        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        Estado nuevoEstado = estadoRepository.findByNombre(dto.getNuevoEstado())
                .orElseThrow(() -> new IllegalArgumentException("Estado no válido"));

        String estadoActual = pedido.getEstadoActual().getNombre();
        String estadoDestino = nuevoEstado.getNombre();

        // RN8: No cancelar si faltan menos de 48h
        if (estadoDestino.equalsIgnoreCase("Cancelado")) {
            if (pedido.getFechaEntregaEstimada().minusHours(48).isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("No se puede cancelar el pedido a menos de 48h de la entrega");
            }
        }

        // RN10: Para pasar a 'Listo para Entregar', debe estar en 'En Producción'
        if (estadoDestino.equalsIgnoreCase("Listo para Entregar") &&
                !estadoActual.equalsIgnoreCase("En Producción")) {
            throw new IllegalArgumentException("No puedes pasar a 'Listo para Entregar' sin pasar por 'En Producción'");
        }

        // RN14: Validaciones por rol
        if ((estadoDestino.equalsIgnoreCase("En Producción") || estadoDestino.equalsIgnoreCase("Listo para Entregar"))
                && !empleado.getRol().equalsIgnoreCase("Producción")) {
            throw new IllegalArgumentException("Solo empleados de Producción pueden cambiar a ese estado");
        }

        if (estadoDestino.equalsIgnoreCase("Entregado") && !empleado.getRol().equalsIgnoreCase("Despacho")) {
            throw new IllegalArgumentException("Solo empleados de Despacho pueden marcar como Entregado");
        }

        // Aplicar cambio
        pedido.setEstadoActual(nuevoEstado);
        pedidoRepository.save(pedido);

        HistorialEstado historial = new HistorialEstado();
        historial.setPedido(pedido);
        historial.setEmpleado(empleado);
        historial.setEstado(nuevoEstado);
        historial.setFechaCambio(LocalDateTime.now());
        historial.setComentario(dto.getComentario());

        historialEstadoRepository.save(historial);
    }

    @Override
    public void cancelarPedido(Long pedidoId, Long empleadoId, String comentario) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        if (pedido.getFechaEntregaEstimada().minusHours(48).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se puede cancelar el pedido a menos de 48h de la entrega");
        }

        Estado cancelado = estadoRepository.findByNombre("Cancelado")
                .orElseThrow(() -> new IllegalArgumentException("Estado 'Cancelado' no está configurado"));

        pedido.setEstadoActual(cancelado);
        pedidoRepository.save(pedido);

        HistorialEstado historial = new HistorialEstado();
        historial.setPedido(pedido);
        historial.setEmpleado(empleado);
        historial.setEstado(cancelado);
        historial.setFechaCambio(LocalDateTime.now());
        historial.setComentario(comentario != null ? comentario : "Cancelación realizada por sistema");

        historialEstadoRepository.save(historial);
    }

}
