
CREATE TYPE tipoPrioridad AS ENUM ('LEVE', 'MEDIA', 'ALTA');
CREATE TYPE tipoArchivo AS ENUM ('FOTO', 'AUDIO');

CREATE TABLE tipos_incidencia (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  prioridad_base tipoPrioridad
);

CREATE TABLE estados_incidencia (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(50) UNIQUE NOT NULL,
  descripcion TEXT,
  orden INTEGER DEFAULT 0,
  estado_final BOOLEAN DEFAULT FALSE
);

CREATE TABLE incidencias (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(100) UNIQUE NOT NULL,
  titulo VARCHAR(200) NOT NULL,
  descripcion TEXT,
  tipo_incidencia_id BIGINT NOT NULL REFERENCES tipos_incidencia(id),
  estado_id BIGINT REFERENCES NOT NULL estados_incidencia(id),
  maquina_id BIGINT REFERENCES NOT NULL maquinas(id),
  orden_id BIGINT REFERENCES NOT NULL ordenes_produccion(id),
  estacion_id BIGINT REFERENCES NOT NULL estaciones_produccion(id),
  reportado_por BIGINT REFERENCES NOT NULL usuarios(id),
  asignado_a BIGINT REFERENCES NOT NULL usuarios(id),
  fecha_cierre TIMESTAMPTZ,
  tiempo_parada INTERVAL,
  creado_en TIMESTAMPTZ DEFAULT now()
);


CREATE TABLE incidencias_archivos (
  id BIGSERIAL PRIMARY KEY,
  incidencia_id BIGINT REFERENCES incidencias(id) NOT NULL ON DELETE CASCADE,
  tipo tipoArchivo NOT NULL,
  nombre_original VARCHAR(255) NOT NULL,
  url TEXT NOT NULL,
  subido_por BIGINT NOT NULL REFERENCES usuarios(id),
  subido_en TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE seguimiento_incidencias (
  id BIGSERIAL PRIMARY KEY,
  incidencia_id BIGINT REFERENCES incidencias(id) ON DELETE CASCADE,
  estado_anterior_id BIGINT REFERENCES estados_incidencia(id),
  estado_nuevo_id BIGINT REFERENCES estados_incidencia(id),
  comentario TEXT,
  realizado_por BIGINT REFERENCES usuarios(id),
  realizado_en TIMESTAMPTZ DEFAULT now(),
  CONSTRAINT chk_estados_distintos CHECK (
    estado_anterior_id IS DISTINCT FROM estado_nuevo_id
  )
);