package com.proyecto.pasteleria.service.impl;

import com.proyecto.pasteleria.dto.ClienteDTO;
import com.proyecto.pasteleria.exception.ResourceNotFoundException;
import com.proyecto.pasteleria.model.Cliente;
import com.proyecto.pasteleria.repository.ClienteRepository;
import com.proyecto.pasteleria.repository.PedidoRepository;
import com.proyecto.pasteleria.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    public ClienteDTO crearCliente(ClienteDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEmail(dto.getEmail());
        cliente.setDireccion(dto.getDireccion());

        Cliente guardado = clienteRepository.save(cliente);
        dto.setId(guardado.getId());
        return dto;
    }

    @Override
    public List<ClienteDTO> obtenerTodos() {
        return clienteRepository.findAll().stream()
                .map(c -> new ClienteDTO(
                        c.getId(),
                        c.getNombreCompleto(),
                        c.getTelefono(),
                        c.getEmail(),
                        c.getDireccion()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public long contarPedidosActivos(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteId);
        }
        return pedidoRepository.countByClienteIdAndEstadoActualNombreIn(clienteId, Arrays.asList("Confirmado", "En Producción"));
    }

}
