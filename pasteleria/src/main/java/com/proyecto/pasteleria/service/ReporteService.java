package com.proyecto.pasteleria.service;

import com.proyecto.pasteleria.dto.reportes.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ReporteService {
    List<ClientePedidoReporteDTO> obtenerReporteClientesPedidos();
    List<ProductoMasVendidoDTO> obtenerProductosMasVendidosUltimoMes();
    List<PedidoPendienteDTO> obtenerPedidosPendientes();
    List<PedidoCanceladoDTO> obtenerPedidosCancelados(LocalDateTime desde, LocalDateTime hasta);
    ReportePedidosEntregadosDTO obtenerPedidosEntregadosUltimoMes();

}
