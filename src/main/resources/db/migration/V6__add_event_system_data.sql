-- Tipos de Movimiento para el flujo de producción
INSERT INTO tipo_movimiento (nombre, descripcion, tipo) VALUES
('Consumo por Producción', 'Salida de materia prima por avance de estación de producción', 'SALIDA'),
('Ingreso de Producción', 'Entrada de producto terminado desde una orden de producción finalizada', 'ENTRADA'),
('Salida a WIP', 'Salida de materia prima hacia la bodega de trabajo en proceso (WIP)', 'SALIDA');

-- Estados de Orden de Producción
INSERT INTO estado_orden (nombre, descripcion, es_inicial, es_final) VALUES
('Iniciada', 'La orden de producción ha comenzado y la primera estación está activa.', false, false),
('En Proceso', 'La orden de producción está avanzando a través de las estaciones.', false, false),
('Finalizada', 'La orden de producción ha completado todas las estaciones.', false, true);
