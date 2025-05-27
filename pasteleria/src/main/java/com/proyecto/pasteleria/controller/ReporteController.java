package com.proyecto.pasteleria.controller;

import com.proyecto.pasteleria.dto.reportes.*;
import com.proyecto.pasteleria.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/clientes-pedidos")
    public ResponseEntity<List<ClientePedidoReporteDTO>> obtenerReporte() {
        return ResponseEntity.ok(reporteService.obtenerReporteClientesPedidos());
    }

    @GetMapping("/productos-mas-vendidos")
    public ResponseEntity<List<ProductoMasVendidoDTO>> productosMasVendidos() {
        return ResponseEntity.ok(reporteService.obtenerProductosMasVendidosUltimoMes());
    }

    @GetMapping("/pedidos-pendientes")
    public ResponseEntity<List<PedidoPendienteDTO>> pedidosPendientes() {
        return ResponseEntity.ok(reporteService.obtenerPedidosPendientes());
    }

    @GetMapping("/pedidos-cancelados")
    public ResponseEntity<List<PedidoCanceladoDTO>> pedidosCancelados(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta
    ) {
        return ResponseEntity.ok(reporteService.obtenerPedidosCancelados(desde, hasta));
    }

    @GetMapping("/pedidos-entregados")
    public ResponseEntity<ReportePedidosEntregadosDTO> pedidosEntregadosUltimoMes() {
        return ResponseEntity.ok(reporteService.obtenerPedidosEntregadosUltimoMes());
    }


}
