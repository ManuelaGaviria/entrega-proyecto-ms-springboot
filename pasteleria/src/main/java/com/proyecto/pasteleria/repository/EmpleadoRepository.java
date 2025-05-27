package com.proyecto.pasteleria.repository;

import com.proyecto.pasteleria.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
