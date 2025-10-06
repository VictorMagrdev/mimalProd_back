-- 1. Insertar Permisos (Operaciones) - CORREGIDO
INSERT INTO permisos (accion, descripcion) VALUES
('CREATE', 'Permite crear nuevos registros'),
('READ', 'Permite leer registros existentes'),
('UPDATE', 'Permite actualizar registros existentes'),
('DELETE', 'Permite eliminar registros existentes');

-- 2. Insertar Roles - CORREGIDO
INSERT INTO roles (nombre, descripcion) VALUES
('ROLE_ADMIN', 'Administrador del sistema con acceso a la configuración'),
('ROLE_OPERATOR', 'Usuario estándar con permisos limitados');

-- 3. Insertar Tags (Etiquetas) para los objetos - CORREGIDO
INSERT INTO tags (nombre, descripcion) VALUES
('ROLES_TAG', 'Etiqueta para roles'),
('CENTROS_COSTO_TAG', 'Etiqueta para centros_costo'),
('USUARIOS_TAG', 'Etiqueta para usuarios'),
('USUARIO_ROLES_TAG', 'Etiqueta para usuario_roles'),
('TAGS_TAG', 'Etiqueta para tags'),
('PERMISOS_TAG', 'Etiqueta para permisos'),
('POLITICAS_TAG', 'Etiqueta para politicas'),
('ESTADOS_ORDEN_TAG', 'Etiqueta para estados_orden'),
('TIPOS_COSTO_TAG', 'Etiqueta para tipos_costo'),
('UNIDADES_MEDIDA_TIPO_TAG', 'Etiqueta para unidades_medida_tipo'),
('UNIDADES_MEDIDA_TAG', 'Etiqueta para unidades_medida'),
('UNIDADES_CONVERSION_TAG', 'Etiqueta para unidades_conversion'),
('TIPOS_PRODUCTO_TAG', 'Etiqueta para tipos_producto'),
('METODOS_VALORACION_TAG', 'Etiqueta para metodos_valoracion'),
('PRODUCTOS_TAG', 'Etiqueta para productos'),
('LOTES_PRODUCCION_TAG', 'Etiqueta para lotes_produccion'),
('ORDENES_PRODUCCION_TAG', 'Etiqueta para ordenes_produccion'),
('ORDENES_LOTES_TAG', 'Etiqueta para ordenes_lotes'),
('LINEAS_ORDEN_TAG', 'Etiqueta para lineas_orden'),
('COSTOS_ORDEN_TAG', 'Etiqueta para costos_orden'),
('TIPOS_BODEGA_TAG', 'Etiqueta para tipos_bodega'),
('BODEGAS_TAG', 'Etiqueta para bodegas'),
('TIPOS_MOVIMIENTO_TAG', 'Etiqueta para tipos_movimiento'),
('MOVIMIENTOS_INVENTARIO_TAG', 'Etiqueta para movimientos_inventario'),
('MOVIMIENTO_INVENTARIO_DETALLE_TAG', 'Etiqueta para movimiento_inventario_detalle'),
('INVENTARIOS_LOTE_TAG', 'Etiqueta para inventarios_lote'),
('RESERVAS_MATERIAL_ORDEN_TAG', 'Etiqueta para reservas_material_orden'),
('ESTACIONES_PRODUCCION_TAG', 'Etiqueta para estaciones_produccion'),
('ESTADOS_ORDEN_ESTACION_TAG', 'Etiqueta para estados_orden_estacion'),
('ORDENES_ESTACION_TAG', 'Etiqueta para ordenes_estacion'),
('ORDENES_EVENTO_TAG', 'Etiqueta para ordenes_evento'),
('CONTEOS_CICLICO_TAG', 'Etiqueta para conteos_ciclico'),
('DISCREPANCIAS_INVENTARIO_TAG', 'Etiqueta para discrepancias_inventario'),
('PUNTOS_REORDEN_TAG', 'Etiqueta para puntos_reorden'),
('ESTADOS_ASIGNACION_TAG', 'Etiqueta para estados_asignacion'),
('ESTADOS_APROBACION_TAG', 'Etiqueta para estados_aprobacion'),
('TIPOS_ACTIVIDAD_TAG', 'Etiqueta para tipos_actividad'),
('ESTADOS_ORDEN_TRABAJO_TAG', 'Etiqueta para estados_orden_trabajo'),
('TIPOS_ORDEN_TRABAJO_TAG', 'Etiqueta para tipos_orden_trabajo'),
('ORDENES_TRABAJO_TAG', 'Etiqueta para ordenes_trabajo'),
('FUNCIONES_TAREA_TAG', 'Etiqueta para funciones_tarea'),
('ASIGNACIONES_TAG', 'Etiqueta para asignaciones'),
('HOJAS_TIMESHEET_TAG', 'Etiqueta para hojas_timesheet'),
('REGISTROS_TIEMPO_TAG', 'Etiqueta para registros_tiempo'),
('EXCEPCIONES_TIEMPO_TAG', 'Etiqueta para excepciones_tiempo'),
('TARIFAS_EMPLEADO_TAG', 'Etiqueta para tarifas_empleado');

INSERT INTO centros_costo (codigo, nombre) VALUES
('ADMIN', 'Centro de Costo Administrativo');

INSERT INTO usuarios (username, email, password, telefono, codigo_empleado, nombre, apellidos, centro_costo_id, activo) VALUES
('admin', 'admin@example.com', '$2a$10$NdzsMp8e0Syek06aG80yvO/8RkSH7zwSrODsv7ICFklnbJLH8HNmG', '123456789', 'ADMIN001', 'Administrador', 'Del Sistema', (SELECT id FROM centros_costo WHERE codigo = 'ADMIN'), true);

INSERT INTO usuario_roles (usuario_id, rol_id)
VALUES (
    (SELECT id FROM usuarios WHERE username = 'admin'),
    (SELECT id FROM roles WHERE nombre = 'ROLE_ADMIN')
);

INSERT INTO politicas (rol_id, tag_id, permiso_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r
CROSS JOIN tags t
CROSS JOIN permisos p
WHERE r.nombre = 'ROLE_ADMIN'
AND p.accion IN ('CREATE','READ','UPDATE','DELETE');

-- 8. Insertar datos básicos del sistema
-- Estados de aprobación
INSERT INTO estados_aprobacion (nombre, descripcion) VALUES
('PENDIENTE', 'Pendiente de aprobación'),
('APROBADO', 'Aprobado'),
('RECHAZADO', 'Rechazado');

-- Estados de asignación
INSERT INTO estados_asignacion (nombre, descripcion) VALUES
('ASIGNADA', 'Asignación activa'),
('COMPLETADA', 'Asignación completada'),
('CANCELADA', 'Asignación cancelada');

-- Tipos de actividad
INSERT INTO tipos_actividad (nombre, descripcion) VALUES
('PRODUCCION', 'Actividad de producción'),
('MANTENIMIENTO', 'Actividad de mantenimiento'),
('ADMINISTRATIVA', 'Actividad administrativa');

-- Estados de orden de trabajo
INSERT INTO estados_orden_trabajo (nombre, descripcion) VALUES
('PENDIENTE', 'Orden pendiente'),
('EN_PROGRESO', 'Orden en progreso'),
('COMPLETADA', 'Orden completada'),
('CANCELADA', 'Orden cancelada');

-- Tipos de orden de trabajo
INSERT INTO tipos_orden_trabajo (nombre, descripcion) VALUES
('CORRECTIVO', 'Mantenimiento correctivo'),
('PREVENTIVO', 'Mantenimiento preventivo'),
('PRODUCCION', 'Orden de producción');

-- Funciones de tarea
INSERT INTO funciones_tarea (nombre, descripcion) VALUES
('OPERADOR', 'Operador de producción'),
('SUPERVISOR', 'Supervisor de área'),
('TECNICO', 'Técnico especializado');

-- Estados de orden
INSERT INTO estados_orden (codigo, nombre, descripcion) VALUES
('BORRADOR', 'Borrador', 'Orden en estado borrador'),
('PLANIFICADA', 'Planificada', 'Orden planificada'),
('EN_PROCESO', 'En Proceso', 'Orden en proceso de producción'),
('COMPLETADA', 'Completada', 'Orden completada'),
('CANCELADA', 'Cancelada', 'Orden cancelada');

-- Tipos de costo
INSERT INTO tipos_costo (codigo, nombre, descripcion) VALUES
('MANO_OBRA', 'Mano de Obra', 'Costos de mano de obra directa'),
('MATERIALES', 'Materiales', 'Costos de materiales directos'),
('GASTOS_IND', 'Gastos Indirectos', 'Gastos indirectos de fabricación');

-- Tipos de bodega
INSERT INTO tipos_bodega (codigo, nombre, descripcion) VALUES
('MATERIA_PRIMA', 'Materia Prima', 'Bodega de materia prima'),
('PRODUCTO_TERM', 'Producto Terminado', 'Bodega de producto terminado'),
('WIP', 'Work in Progress', 'Bodega de trabajo en proceso');

-- Tipos de movimiento
INSERT INTO tipos_movimiento (codigo, nombre, descripcion, afecta_wip) VALUES
('ENTRADA', 'Entrada', 'Entrada de inventario', false),
('SALIDA', 'Salida', 'Salida de inventario', false),
('TRANSFERENCIA', 'Transferencia', 'Transferencia entre bodegas', false),
('CONSUMO_PROD', 'Consumo Producción', 'Consumo para producción', true);