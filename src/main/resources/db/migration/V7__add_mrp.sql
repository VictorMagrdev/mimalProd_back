CREATE TABLE estructuras_producto (
  id BIGSERIAL PRIMARY KEY,
  producto_padre_id BIGINT NOT NULL REFERENCES productos(id) ON DELETE CASCADE,
  producto_hijo_id BIGINT NOT NULL REFERENCES productos(id),
  cantidad NUMERIC(18,6) NOT NULL,
  unidad_id BIGINT REFERENCES unidades_medida(id),
  version VARCHAR(50),
  activo BOOLEAN DEFAULT TRUE,
  creado_en TIMESTAMPTZ DEFAULT now(),
  CONSTRAINT uq_estructura UNIQUE (producto_padre_id, producto_hijo_id, version)
);

CREATE TABLE parametros_planificacion (
  id BIGSERIAL PRIMARY KEY,
  producto_id BIGINT UNIQUE REFERENCES productos(id),
  lead_time_dias INT DEFAULT 0,
  lote_minimo NUMERIC(18,6) DEFAULT 0,
  lote_economico NUMERIC(18,6) DEFAULT 0,
  politica_inventario VARCHAR(50) DEFAULT 'MRP',
  actualizado_en TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE requerimientos_materiales (
  id BIGSERIAL PRIMARY KEY,
  producto_id BIGINT REFERENCES productos(id),
  orden_produccion_id BIGINT REFERENCES ordenes_produccion(id),
  cantidad_requerida NUMERIC(18,6),
  cantidad_disponible NUMERIC(18,6) DEFAULT 0,
  cantidad_a_pedir NUMERIC(18,6) GENERATED ALWAYS AS (GREATEST(cantidad_requerida - cantidad_disponible, 0)) STORED,
  fecha_necesidad DATE,
  creado_en TIMESTAMPTZ DEFAULT now()
);
