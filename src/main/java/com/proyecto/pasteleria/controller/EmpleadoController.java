package com.proyecto.pasteleria.controller;

import com.proyecto.pasteleria.dto.EmpleadoDTO;
import com.proyecto.pasteleria.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crear(@Valid @RequestBody EmpleadoDTO dto) {
        return ResponseEntity.ok(empleadoService.crearEmpleado(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }
}

