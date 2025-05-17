package com.proyecto.pasteleria.service.impl;

import com.proyecto.pasteleria.dto.EmpleadoDTO;
import com.proyecto.pasteleria.model.Empleado;
import com.proyecto.pasteleria.repository.EmpleadoRepository;
import com.proyecto.pasteleria.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setNombre(dto.getNombre());
        empleado.setRol(dto.getRol());
        empleado.setEspecialidad(dto.getEspecialidad());

        Empleado guardado = empleadoRepository.save(empleado);
        dto.setId(guardado.getId());
        return dto;
    }

    @Override
    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(e -> new EmpleadoDTO(e.getId(), e.getNombre(), e.getRol(), e.getEspecialidad()))
                .collect(Collectors.toList());
    }
}

