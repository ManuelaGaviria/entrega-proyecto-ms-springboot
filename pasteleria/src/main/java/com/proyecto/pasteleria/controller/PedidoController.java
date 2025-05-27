package com.proyecto.pasteleria.controller;

import com.proyecto.pasteleria.dto.CambioEstadoDTO;
import com.proyecto.pasteleria.dto.PedidoRequestDTO;
import com.proyecto.pasteleria.dto.PedidoResumenDTO;
import com.proyecto.pasteleria.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<String> crearPedido(@Valid @RequestBody PedidoRequestDTO dto) {
        pedidoService.crearPedido(dto);
        return ResponseEntity.ok("Pedido creado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<PedidoResumenDTO>> obtenerTodos() {
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<String> cambiarEstado(
            @PathVariable Long id,
            @Valid @RequestBody CambioEstadoDTO dto
    ) {
        pedidoService.cambiarEstado(id, dto);
        return ResponseEntity.ok("Estado actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelarPedido(
            @PathVariable Long id,
            @RequestParam Long empleadoId,
            @RequestParam(required = false) String comentario
    ) {
        pedidoService.cancelarPedido(id, empleadoId, comentario);
        return ResponseEntity.ok("Pedido cancelado correctamente");
    }


}
