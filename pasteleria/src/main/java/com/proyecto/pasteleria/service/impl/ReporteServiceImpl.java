package com.proyecto.pasteleria.service.impl;

import com.proyecto.pasteleria.dto.reportes.*;
import com.proyecto.pasteleria.repository.ClienteRepository;
import com.proyecto.pasteleria.repository.HistorialEstadoRepository;
import com.proyecto.pasteleria.repository.PedidoProductoRepository;
import com.proyecto.pasteleria.repository.PedidoRepository;
import com.proyecto.pasteleria.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final ClienteRepository clienteRepository;
    private final PedidoProductoRepository pedidoProductoRepository;
    private final PedidoRepository pedidoRepository;
    private final HistorialEstadoRepository historialEstadoRepository;

    @Override
    public List<ClientePedidoReporteDTO> obtenerReporteClientesPedidos() {
        return clienteRepository.obtenerReporteClientesPedidos();
    }

    @Override
    public List<ProductoMasVendidoDTO> obtenerProductosMasVendidosUltimoMes() {
        LocalDateTime haceUnMes = LocalDateTime.now().minusMonths(1);
        return pedidoProductoRepository.obtenerProductosMasVendidosEnElMes(haceUnMes);
    }

    @Override
    public List<PedidoPendienteDTO> obtenerPedidosPendientes() {
        return pedidoRepository.obtenerPedidosPendientes();
    }

    @Override
    public List<PedidoCanceladoDTO> obtenerPedidosCancelados(LocalDateTime desde, LocalDateTime hasta) {
        return historialEstadoRepository.obtenerPedidosCanceladosEnPeriodo(desde, hasta);
    }

    @Override
    public ReportePedidosEntregadosDTO obtenerPedidosEntregadosUltimoMes() {
        LocalDateTime haceUnMes = LocalDateTime.now().minusMonths(1);
        List<PedidoEntregadoDTO> pedidos = historialEstadoRepository.obtenerPedidosEntregadosUltimoMes(haceUnMes);

        double total = pedidos.stream()
                .mapToDouble(PedidoEntregadoDTO::getTotalPagar)
                .sum();

        return new ReportePedidosEntregadosDTO(total, pedidos);
    }


}