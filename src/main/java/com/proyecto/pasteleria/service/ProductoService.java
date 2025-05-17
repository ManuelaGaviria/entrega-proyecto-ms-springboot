package com.proyecto.pasteleria.service;

import com.proyecto.pasteleria.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    ProductoDTO crearProducto(ProductoDTO dto);
    List<ProductoDTO> listarDisponibles();
    void actualizarDisponibilidad(Long productoId, Boolean disponibilidad);
}
