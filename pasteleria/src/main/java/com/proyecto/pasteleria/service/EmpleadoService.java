package com.proyecto.pasteleria.service;

import com.proyecto.pasteleria.dto.EmpleadoDTO;
import java.util.List;

public interface EmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoDTO dto);
    List<EmpleadoDTO> listarEmpleados();
}
