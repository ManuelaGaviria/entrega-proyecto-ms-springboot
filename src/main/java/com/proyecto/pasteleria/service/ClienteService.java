package com.proyecto.pasteleria.service;

import com.proyecto.pasteleria.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> obtenerTodos();
    long contarPedidosActivos(Long clienteId);
}
