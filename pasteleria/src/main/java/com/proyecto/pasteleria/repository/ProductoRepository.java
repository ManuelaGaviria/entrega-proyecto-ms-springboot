package com.proyecto.pasteleria.repository;

import com.proyecto.pasteleria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombre(String nombre);
    List<Producto> findByDisponibilidadTrue();
}
