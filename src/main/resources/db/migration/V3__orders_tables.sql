CREATE TABLE estado_orden (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  activo BOOLEAN DEFAULT true,
  creado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE tipo_costo (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  activo BOOLEAN DEFAULT true,
  creado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE unidad_medida_tipo (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  creado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE unidad_medida (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  abreviatura VARCHAR(50),
  id_tipo BIGINT REFERENCES unidad_medida_tipo(id),
  es_activa BOOLEAN DEFAULT true,
  es_base BOOLEAN DEFAULT false,
  creado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE unidad_conversion (
  id BIGSERIAL PRIMARY KEY,
  id_origen BIGINT REFERENCES unidad_medida(id),
  id_destino BIGINT REFERENCES unidad_medida(id),
  factor NUMERIC(18,8) NOT NULL,
  CONSTRAINT uq_conversion UNIQUE (id_origen, id_destino)
);

CREATE TABLE tipo_producto (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT
);
-- Métodos de valoración de inventario (ej: FIFO, LIFO, PROMEDIO)
CREATE TABLE metodo_valoracion (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT
);

CREATE TABLE producto (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(64) UNIQUE,
  nombre TEXT NOT NULL,
  id_metodo_valoracion BIGINT REFERENCES metodo_valoracion(id),
  id_tipo BIGINT REFERENCES tipo_producto(id),
  id_unidad_base BIGINT REFERENCES unidad_medida(id),
  costo_base NUMERIC(18,4) DEFAULT 0,
  creado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE lote_produccion (
  id BIGSERIAL PRIMARY KEY,
  numero_lote VARCHAR(100) UNIQUE NOT NULL,
  id_producto BIGINT REFERENCES producto(id),
  fabricado_en TIMESTAMP,
  vence_en TIMESTAMP,
  creado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE orden_produccion (
  id BIGSERIAL PRIMARY KEY,
  numero_orden VARCHAR(100) UNIQUE NOT NULL,
  id_lote BIGINT REFERENCES lote_produccion(id),
  id_producto BIGINT REFERENCES producto(id),
  cantidad NUMERIC(18,6) NOT NULL DEFAULT 0,
  id_unidad BIGINT REFERENCES unidad_medida(id),
  id_estado BIGINT REFERENCES estado_orden(id),
  inicio_planificado TIMESTAMP,
  fin_planificado TIMESTAMP,
  inicio_real TIMESTAMP,
  fin_real TIMESTAMP,
  cantidad_desperdicio NUMERIC(18,6) DEFAULT 0,
  cantidad_producida NUMERIC(18,6) DEFAULT 0,
  creado_por BIGINT,
  observaciones TEXT,
  creado_en TIMESTAMP DEFAULT now(),
  actualizado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE linea_orden (
  id BIGSERIAL PRIMARY KEY,
  id_orden BIGINT REFERENCES orden_produccion(id),
  numero_linea INT NOT NULL DEFAULT 1,
  id_producto_componente BIGINT REFERENCES producto(id),
  cantidad_requerida NUMERIC(18,6) NOT NULL DEFAULT 0,
  id_unidad_componente BIGINT REFERENCES unidad_medida(id),
  cantidad_usada NUMERIC(18,6) DEFAULT 0,
  costo_unitario NUMERIC(18,6) DEFAULT 0,
  costo_total NUMERIC(18,6) GENERATED ALWAYS AS (cantidad_requerida * costo_unitario) STORED,
  observaciones TEXT,
  creado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE costo_orden (
  id BIGSERIAL PRIMARY KEY,
  id_orden BIGINT REFERENCES orden_produccion(id),
  id_tipo_costo BIGINT REFERENCES tipo_costo(id),
  descripcion TEXT,
  monto NUMERIC(18,6) NOT NULL DEFAULT 0,
  moneda VARCHAR(5)  DEFAULT 'COP',
  registrado_en TIMESTAMP DEFAULT now()
);

