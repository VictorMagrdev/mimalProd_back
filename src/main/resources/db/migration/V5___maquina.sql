CREATE TABLE maquinas (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(150),
  numero_serie VARCHAR(100),
  fecha_compra DATE NOT NULL,
  costo_compra NUMERIC(18,2) NOT NULL,
  valor_rescate NUMERIC(18,2) DEFAULT 0,
  vida_util_anios INTEGER NOT NULL,
  centro_costo_id BIGINT REFERENCES centros_costo(id),
  activa BOOLEAN DEFAULT TRUE,
  creado_en TIMESTAMPTZ DEFAULT now()
);
CREATE TYPE tipoPeriodo AS ENUM ('MENSUAL', 'ANUAL');
CREATE TABLE depreciaciones (
  id BIGSERIAL PRIMARY KEY,
  maquina_id BIGINT REFERENCES maquinas(id),
  tipo_periodo tipoPeriodo NOT NULL,
  periodo DATE NOT NULL,
  depreciacion_periodo NUMERIC(18,2) NOT NULL,
  depreciacion_acumulada NUMERIC(18,2) NOT NULL,
  valor_neto NUMERIC(18,2) NOT NULL,
  calculado_en TIMESTAMPTZ DEFAULT now()
);