package com.proyecto.pasteleria.service.impl;

import com.proyecto.pasteleria.dto.ProductoDTO;
import com.proyecto.pasteleria.model.Producto;
import com.proyecto.pasteleria.repository.ProductoRepository;
import com.proyecto.pasteleria.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public ProductoDTO crearProducto(ProductoDTO dto) {
        if (productoRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe un producto con ese nombre");
        }

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioBase(dto.getPrecioBase());
        producto.setDisponibilidad(dto.getDisponibilidad());

        Producto guardado = productoRepository.save(producto);
        dto.setId(guardado.getId());
        return dto;
    }

    @Override
    public List<ProductoDTO> listarDisponibles() {
        return productoRepository.findByDisponibilidadTrue()
                .stream()
                .map(p -> new ProductoDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getPrecioBase(),
                        p.getDisponibilidad()
                ))
                .toList();
    }

    @Override
    public void actualizarDisponibilidad(Long productoId, Boolean disponibilidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));
        producto.setDisponibilidad(disponibilidad);
        productoRepository.save(producto);
    }

}

