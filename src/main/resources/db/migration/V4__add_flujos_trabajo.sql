CREATE TABLE rutas_produccion (
  id BIGSERIAL PRIMARY KEY,
  producto_id BIGINT REFERENCES productos(id),
  version VARCHAR(50),
  nombre VARCHAR(100) NOT NULL,
  activo BOOLEAN DEFAULT true,
  creado_en TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE operaciones_ruta (
  id BIGSERIAL PRIMARY KEY,
  ruta_id BIGINT REFERENCES rutas_produccion(id) ON DELETE CASCADE,
  estacion_id BIGINT REFERENCES estaciones_produccion(id),
  secuencia INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  tiempo_setup INTERVAL DEFAULT '0',
  tiempo_ejecucion INTERVAL DEFAULT '0',
  tiempo_cola INTERVAL DEFAULT '0',
  creado_en TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE operaciones_orden_estados(
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(150)
);

CREATE TABLE operaciones_orden (
  id BIGSERIAL PRIMARY KEY,
  orden_id BIGINT REFERENCES ordenes_produccion(id) ON DELETE CASCADE,
  operacion_ruta_id BIGINT REFERENCES operaciones_ruta(id),
  estacion_id BIGINT REFERENCES estaciones_produccion(id),
  secuencia INT NOT NULL,
  inicio_planificado TIMESTAMPTZ,
  fin_planificado TIMESTAMPTZ,
  inicio_real TIMESTAMPTZ,
  fin_real TIMESTAMPTZ,
  estado_id BIGINT REFERENCES operaciones_orden_estados(id),
  observaciones VARCHAR(150)
);

CREATE TABLE recursos_operacion (
  id BIGSERIAL PRIMARY KEY,
  operacion_orden_id BIGINT REFERENCES operaciones_orden(id) ON DELETE CASCADE,
  usuario_id BIGINT REFERENCES usuarios(id),
  funcion_tarea_id BIGINT REFERENCES funciones_tarea(id),
  horas_planificadas INTERVAL,
  horas_reales INTERVAL,
  asignado_por BIGINT REFERENCES usuarios(id),
  creado_en TIMESTAMPTZ DEFAULT now()
);

-- Insertar Tags para las nuevas tablas
INSERT INTO tags (nombre, descripcion) VALUES
('RUTAS_PRODUCCION_TAG', 'Etiqueta para rutas_produccion'),
('OPERACIONES_RUTA_TAG', 'Etiqueta para operaciones_ruta'),
('OPERACIONES_ORDEN_ESTADOS_TAG', 'Etiqueta para operaciones_orden_estados'),
('OPERACIONES_ORDEN_TAG', 'Etiqueta para operaciones_orden'),
('RECURSOS_OPERACION_TAG', 'Etiqueta para recursos_operacion');

-- Crear Políticas de Acceso para el Rol de Administrador en las nuevas tablas
INSERT INTO politicas (rol_id, tag_id, permiso_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r
CROSS JOIN tags t
CROSS JOIN permisos p
WHERE r.nombre = 'ROLE_ADMIN'
AND t.nombre IN (
    'RUTAS_PRODUCCION_TAG',
    'OPERACIONES_RUTA_TAG',
    'OPERACIONES_ORDEN_ESTADOS_TAG',
    'OPERACIONES_ORDEN_TAG',
    'RECURSOS_OPERACION_TAG'
)
AND p.accion IN ('CREATE','READ','UPDATE','DELETE');