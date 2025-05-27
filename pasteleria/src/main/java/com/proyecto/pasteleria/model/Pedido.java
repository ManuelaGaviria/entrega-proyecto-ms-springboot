package com.proyecto.pasteleria.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntregaEstimada;
    private Double totalPagar;

    @ManyToOne
    @JoinColumn(name = "estado_actual_id")
    private Estado estadoActual;
}
