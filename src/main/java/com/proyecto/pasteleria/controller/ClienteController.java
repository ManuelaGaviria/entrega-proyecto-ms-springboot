package com.proyecto.pasteleria.controller;

import com.proyecto.pasteleria.dto.ClienteDTO;
import com.proyecto.pasteleria.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevo = clienteService.crearCliente(clienteDTO);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @GetMapping("/{id}/pedidos-activos")
    public ResponseEntity<Map<String, Object>> obtenerPedidosActivos(@PathVariable Long id) {
        Long total = clienteService.contarPedidosActivos(id);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("clienteId", id);
        respuesta.put("pedidosActivos", total);
        return ResponseEntity.ok(respuesta);
    }

}
