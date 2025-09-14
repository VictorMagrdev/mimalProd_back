-- Tipos de bodega
CREATE TABLE tipo_bodega (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT
);

CREATE TABLE bodega (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  id_tipo BIGINT REFERENCES tipo_bodega(id),
  creado_en TIMESTAMP DEFAULT now()
);

-- Tipos de movimiento
CREATE TABLE tipo_movimiento (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  afecta_wip BOOLEAN DEFAULT false,
  creado_en TIMESTAMP DEFAULT now()
);

-- Movimientos de inventario
CREATE TABLE movimiento_inventario (
  id BIGSERIAL PRIMARY KEY,
  fecha TIMESTAMP DEFAULT now(),
  id_bodega_origen BIGINT REFERENCES bodega(id),
  id_bodega_destino BIGINT REFERENCES bodega(id),
  id_tipo_movimiento BIGINT REFERENCES tipo_movimiento(id),
  referencia TEXT,
  observaciones TEXT,
  creado_por BIGINT,
  creado_en TIMESTAMP DEFAULT now()
);

CREATE TABLE movimiento_inventario_detalle (
  id BIGSERIAL PRIMARY KEY,
  id_movimiento BIGINT REFERENCES movimiento_inventario(id),
  id_producto BIGINT REFERENCES producto(id),
  id_lote BIGINT REFERENCES lote_produccion(id),
  cantidad NUMERIC(18,6) NOT NULL DEFAULT 0,
  id_unidad BIGINT REFERENCES unidad_medida(id),
  costo_unitario NUMERIC(18,6) DEFAULT 0,
  costo_total NUMERIC(18,6) GENERATED ALWAYS AS (cantidad * costo_unitario) STORED
);

-- Inventario consolidado (stock físico)
CREATE TABLE inventario_lote (
  id BIGSERIAL PRIMARY KEY,
  id_producto BIGINT REFERENCES producto(id),
  id_lote BIGINT REFERENCES lote_produccion(id),
  id_bodega BIGINT REFERENCES bodega(id),
  cantidad NUMERIC(18,6) NOT NULL DEFAULT 0,
  id_unidad BIGINT REFERENCES unidad_medida(id),
  actualizado_en TIMESTAMP DEFAULT now(),
  CONSTRAINT uq_producto_lote_bodega UNIQUE (id_producto, id_lote, id_bodega)
);

-- Reservas de material para órdenes
CREATE TABLE reserva_material_orden (
  id BIGSERIAL PRIMARY KEY,
  id_orden BIGINT REFERENCES orden_produccion(id),
  id_producto BIGINT REFERENCES producto(id),
  id_lote BIGINT REFERENCES lote_produccion(id),
  cantidad_reservada NUMERIC(18,6),
  id_unidad BIGINT REFERENCES unidad_medida(id),
  fecha_reserva TIMESTAMP DEFAULT now()
);

-- Estaciones de producción
CREATE TABLE estacion_produccion (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  orden INT NOT NULL,
  creado_en TIMESTAMP DEFAULT now()
);

-- Avance de orden por estación
CREATE TABLE orden_estacion (
  id BIGSERIAL PRIMARY KEY,
  id_orden BIGINT REFERENCES orden_produccion(id),
  id_estacion BIGINT REFERENCES estacion_produccion(id),
  inicio_planificado TIMESTAMP,
  fin_planificado TIMESTAMP,
  inicio_real TIMESTAMP,
  fin_real TIMESTAMP,
  estado VARCHAR(50),
  observaciones TEXT
);

-- Bitácora de eventos de orden (trazabilidad)
CREATE TABLE orden_evento (
  id BIGSERIAL PRIMARY KEY,
  id_orden BIGINT REFERENCES orden_produccion(id),
  evento VARCHAR(100) NOT NULL,
  descripcion TEXT,
  fecha TIMESTAMP DEFAULT now()
);

-- Conteo cíclico de inventario
CREATE TABLE conteo_ciclico (
  id BIGSERIAL PRIMARY KEY,
  id_producto BIGINT REFERENCES producto(id),
  id_bodega BIGINT REFERENCES bodega(id),
  id_lote BIGINT REFERENCES lote_produccion(id),
  cantidad_contada NUMERIC(18,6) NOT NULL,
  id_unidad BIGINT REFERENCES unidad_medida(id),
  fecha TIMESTAMP DEFAULT now()
);

-- Discrepancias de inventario
CREATE TABLE discrepancia_inventario (
  id BIGSERIAL PRIMARY KEY,
  id_conteo BIGINT REFERENCES conteo_ciclico(id),
  cantidad_sistema NUMERIC(18,6) NOT NULL,
  cantidad_contada NUMERIC(18,6) NOT NULL,
  diferencia NUMERIC(18,6) GENERATED ALWAYS AS (cantidad_contada - cantidad_sistema) STORED,
  resuelto BOOLEAN DEFAULT false
);

-- Parámetros de reorden
CREATE TABLE punto_reorden (
  id BIGSERIAL PRIMARY KEY,
  id_producto BIGINT REFERENCES producto(id),
  stock_minimo NUMERIC(18,6) NOT NULL,
  stock_seguridad NUMERIC(18,6) DEFAULT 0,
  id_unidad BIGINT REFERENCES unidad_medida(id)
);
