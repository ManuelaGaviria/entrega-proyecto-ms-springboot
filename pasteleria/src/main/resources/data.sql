
-- Estados
INSERT INTO estados (nombre) VALUES
('Solicitado'),
('Confirmado'),
('En Producción'),
('Listo para Entregar'),
('Entregado'),
('Cancelado');

-- Clientes
INSERT INTO clientes (nombre_completo, telefono, email, direccion) VALUES
('Ana Gómez', '3001234567', 'ana.gomez@email.com', 'Calle 123 #45-67'),
('Carlos Ruiz', '3017654321', 'carlos.ruiz@email.com', 'Carrera 89 #12-34'),
('María Torres', '3021122334', 'maria.torres@email.com', 'Avenida Siempreviva 742');

-- Empleados
INSERT INTO empleados (nombre, rol, especialidad) VALUES
('Juan Muriel', 'Producción', 'Tortas Temáticas'),
('Pedro Sánchez', 'Despacho', NULL),
('Manuela Gaviria', 'Administrador', NULL);

-- Productos
INSERT INTO productos (nombre, descripcion, precio_base, disponibilidad) VALUES
('Torta de Chocolate', 'Torta clásica de chocolate', 50000.00, TRUE),
('Torta de Vainilla', 'Torta esponjosa de vainilla', 45000.00, TRUE),
('Cupcakes Variados', 'Caja de 6 cupcakes de diferentes sabores', 30000.00, TRUE),
('Tarta de Fresas', 'Tarta artesanal con fresas frescas', 55000.00, TRUE);

-- Pedidos
-- Por ejemplo, si hoy es 2025-05-17:
INSERT INTO pedidos (
    cliente_id, fecha_entrega_estimada, estado_actual_id, total_pagar
) VALUES
(1, '2025-05-20T10:00:00', 1, 55000.00),
(2, '2025-05-22T14:00:00', 1, 85000.00),
(3, '2025-05-19T08:30:00', 1, 30000.00);


-- Pedido_Productos
INSERT INTO pedido_productos (pedido_id, producto_id, cantidad, personalizacion, precio_final) VALUES
(1, 1, 1, 'Con relleno de Nutella', 55000.00),
(2, 2, 1, NULL, 45000.00),
(2, 4, 1, 'Extra fresas', 40000.00),
(3, 3, 1, NULL, 30000.00);

-- Historial de Estados
INSERT INTO historial_estados (empleado_id, pedido_id, estado_id, comentario) VALUES
(3, 1, 1, 'Pedido solicitado por el cliente.'),
(3, 2, 1, 'Pedido solicitado por el cliente.'),
(3, 3, 1, 'Pedido solicitado por el cliente.');
