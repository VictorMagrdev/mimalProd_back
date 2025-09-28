-- 1. Insertar Permisos (Operaciones)
INSERT INTO permissions (action, description) VALUES
('CREATE', 'Permite crear nuevos registros'),
('READ', 'Permite leer registros existentes'),
('UPDATE', 'Permite actualizar registros existentes'),
('DELETE', 'Permite eliminar registros existentes');

-- 3. Insertar Roles
INSERT INTO roles (name, description) VALUES
('ROLE_ADMIN', 'Administrador del sistema con acceso a la configuración'),
('ROLE_OPERATOR', 'Usuario estándar con permisos limitados');

-- 4. Insertar Tags (Etiquetas) para los objetos
INSERT INTO tags (name, description, owner_role_id) VALUES
('ROLES_TAG', 'Etiqueta para roles', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('CENTROS_COSTO_TAG', 'Etiqueta para centros_costo', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('USUARIOS_TAG', 'Etiqueta para usuarios', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('USUARIO_ROLES_TAG', 'Etiqueta para usuario_roles', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TAGS_TAG', 'Etiqueta para tags', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('PERMISOS_TAG', 'Etiqueta para permisos', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('POLITICAS_TAG', 'Etiqueta para politicas', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ESTADOS_ORDEN_TAG', 'Etiqueta para estados_orden', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TIPOS_COSTO_TAG', 'Etiqueta para tipos_costo', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('UNIDADES_MEDIDA_TIPO_TAG', 'Etiqueta para unidades_medida_tipo', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('UNIDADES_MEDIDA_TAG', 'Etiqueta para unidades_medida', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('UNIDADES_CONVERSION_TAG', 'Etiqueta para unidades_conversion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TIPOS_PRODUCTO_TAG', 'Etiqueta para tipos_producto', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('METODOS_VALORACION_TAG', 'Etiqueta para metodos_valoracion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('PRODUCTOS_TAG', 'Etiqueta para productos', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('LOTES_PRODUCCION_TAG', 'Etiqueta para lotes_produccion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ORDENES_PRODUCCION_TAG', 'Etiqueta para ordenes_produccion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ORDENES_LOTES_TAG', 'Etiqueta para ordenes_lotes', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('LINEAS_ORDEN_TAG', 'Etiqueta para lineas_orden', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('COSTOS_ORDEN_TAG', 'Etiqueta para costos_orden', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TIPOS_BODEGA_TAG', 'Etiqueta para tipos_bodega', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('BODEGAS_TAG', 'Etiqueta para bodegas', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TIPOS_MOVIMIENTO_TAG', 'Etiqueta para tipos_movimiento', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('MOVIMIENTOS_INVENTARIO_TAG', 'Etiqueta para movimientos_inventario', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('MOVIMIENTO_INVENTARIO_DETALLE_TAG', 'Etiqueta para movimiento_inventario_detalle', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('INVENTARIOS_LOTE_TAG', 'Etiqueta para inventarios_lote', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('RESERVAS_MATERIAL_ORDEN_TAG', 'Etiqueta para reservas_material_orden', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ESTACIONES_PRODUCCION_TAG', 'Etiqueta para estaciones_produccion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ESTADOS_ORDEN_ESTACION_TAG', 'Etiqueta para estados_orden_estacion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ORDENES_ESTACION_TAG', 'Etiqueta para ordenes_estacion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ORDENES_EVENTO_TAG', 'Etiqueta para ordenes_evento', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('CONTEOS_CICLICO_TAG', 'Etiqueta para conteos_ciclico', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('DISCREPANCIAS_INVENTARIO_TAG', 'Etiqueta para discrepancias_inventario', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('PUNTOS_REORDEN_TAG', 'Etiqueta para puntos_reorden', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ESTADOS_ASIGNACION_TAG', 'Etiqueta para estados_asignacion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ESTADOS_APROBACION_TAG', 'Etiqueta para estados_aprobacion', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TIPOS_ACTIVIDAD_TAG', 'Etiqueta para tipos_actividad', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ESTADOS_ORDEN_TRABAJO_TAG', 'Etiqueta para estados_orden_trabajo', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TIPOS_ORDEN_TRABAJO_TAG', 'Etiqueta para tipos_orden_trabajo', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ORDENES_TRABAJO_TAG', 'Etiqueta para ordenes_trabajo', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('FUNCIONES_TAREA_TAG', 'Etiqueta para funciones_tarea', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('ASIGNACIONES_TAG', 'Etiqueta para asignaciones', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('HOJAS_TIMESHEET_TAG', 'Etiqueta para hojas_timesheet', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('REGISTROS_TIEMPO_TAG', 'Etiqueta para registros_tiempo', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('EXCEPCIONES_TIEMPO_TAG', 'Etiqueta para excepciones_tiempo', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TARIFAS_EMPLEADO_TAG', 'Etiqueta para tarifas_empleado', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));

-- 6. Insertar Usuario Administrador
-- Contraseña: 'admin123' (bcrypt hash)
INSERT INTO users (username, email, password, active) VALUES
('admin', 'admin@example.com', '$2a$17$YFAsX6jOME7VCexvNKkgEuiykKj821bfIwIDen1MeEYFAg5xIc8jG', true);

-- 7. Asignar Rol de Administrador al Usuario 'admin'
INSERT INTO user_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE username = 'admin'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
);

-- 8. Crear Políticas de Acceso para el Rol de Administrador
-- Usando los tags correctos en lugar de objetos
INSERT INTO policies (role_id, tag_id, permission_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r, tags t, permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND t.name = 'TAG_USERS'
  AND p.action IN ('CREATE','READ','UPDATE','DELETE');

INSERT INTO policies (role_id, tag_id, permission_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r, tags t, permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND t.name = 'TAG_ROLES'
  AND p.action IN ('CREATE','READ','UPDATE','DELETE');

INSERT INTO policies (role_id, tag_id, permission_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r, tags t, permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND t.name = 'TAG_POLICIES'
  AND p.action IN ('CREATE','READ','DELETE');

INSERT INTO policies (role_id, tag_id, permission_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r, tags t, permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND t.name = 'TAG_GENERAL'
  AND p.action IN ('CREATE','READ');

-- 9. Insertar algunos atributos de ejemplo
INSERT INTO attributes (name, value, user_id) VALUES
('department', 'IT', (SELECT id FROM users WHERE username = 'admin')),
('security_level', 'high', (SELECT id FROM users WHERE username = 'admin'));

-- 10. Insertar condición de ejemplo para una política
INSERT INTO policy_conditions (policy_id, attribute_name, operator, expected_value)
SELECT
    p.id,
    'department',
    '=',
    'IT'
FROM policies p
WHERE p.role_id = (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
LIMIT 1;