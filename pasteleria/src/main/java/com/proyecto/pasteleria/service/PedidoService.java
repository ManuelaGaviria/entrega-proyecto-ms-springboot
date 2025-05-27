package com.proyecto.pasteleria.service;

import com.proyecto.pasteleria.dto.PedidoRequestDTO;
import com.proyecto.pasteleria.dto.PedidoResumenDTO;
import com.proyecto.pasteleria.dto.CambioEstadoDTO;

import java.util.List;

public interface PedidoService {
    void crearPedido(PedidoRequestDTO dto);
    List<PedidoResumenDTO> obtenerTodos();
    void cambiarEstado(Long pedidoId, CambioEstadoDTO dto);
    void cancelarPedido(Long pedidoId, Long empleadoId, String comentario);

}
