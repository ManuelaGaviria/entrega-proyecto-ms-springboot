package com.proyecto.pasteleria.controller;

import com.proyecto.pasteleria.dto.DisponibilidadDTO;
import com.proyecto.pasteleria.dto.ProductoDTO;
import com.proyecto.pasteleria.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO creado = productoService.crearProducto(productoDTO);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerDisponibles() {
        return ResponseEntity.ok(productoService.listarDisponibles());
    }

    @PutMapping("/{id}/disponibilidad")
    public ResponseEntity<String> actualizarDisponibilidad(
            @PathVariable Long id,
            @Valid @RequestBody DisponibilidadDTO dto
    ) {
        productoService.actualizarDisponibilidad(id, dto.getDisponibilidad());
        return ResponseEntity.ok("Disponibilidad actualizada correctamente");
    }


}

