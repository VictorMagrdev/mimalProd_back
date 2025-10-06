CREATE TABLE roles (
  id BIGSERIAL PRIMARY KEY,  
  nombre VARCHAR(100) UNIQUE NOT NULL,  
  descripcion VARCHAR(150)  
);  
  
CREATE TABLE centros_costo (
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) ,
  nombre VARCHAR(100) NOT NULL
);

CREATE TABLE usuarios (  
  id BIGSERIAL PRIMARY KEY,  
  username VARCHAR(150) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,  
  password VARCHAR(255) NOT NULL,
  telefono VARCHAR(255) NOT NULL,  
  codigo_empleado VARCHAR(150) UNIQUE,
  nombre VARCHAR(150) NOT NULL,
  apellidos VARCHAR(150) NOT NULL,
  centro_costo_id BIGINT REFERENCES centros_costo(id),
  capacidad_horas_dia INTERVAL DEFAULT '8 hours',
  activo BOOLEAN DEFAULT TRUE,
  creado_en TIMESTAMPTZ DEFAULT now(),
  actualizado_en TIMESTAMPTZ DEFAULT now()
);  
  
CREATE TABLE usuario_roles (  
  usuario_id BIGINT NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,  
  rol_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,  
  PRIMARY KEY (usuario_id, rol_id)  
);  
  
CREATE TABLE tags (  
  id BIGSERIAL PRIMARY KEY,  
  nombre VARCHAR(150) UNIQUE NOT NULL,  
  descripcion VARCHAR(150)
);
  
CREATE TABLE permisos (  
  id BIGSERIAL PRIMARY KEY,  
  accion VARCHAR(50) NOT NULL,  
  descripcion VARCHAR(150)  
);  
  
CREATE TABLE politicas (  
  id BIGSERIAL PRIMARY KEY,  
  rol_id BIGINT REFERENCES roles(id),  
  tag_id BIGINT REFERENCES tags(id),  
  permiso_id BIGINT REFERENCES permisos(id),  
  UNIQUE (rol_id, tag_id, permiso_id)  
);

CREATE TABLE estados_orden (  
  id BIGSERIAL PRIMARY KEY,
  codigo VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(150),
  activo BOOLEAN DEFAULT true,  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE tipos_costo (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(150),  
  activo BOOLEAN DEFAULT true,  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE unidades_medida_tipo (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(150),  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE unidades_medida (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  abreviatura VARCHAR(50),  
  unidad_medida_tipo_id BIGINT REFERENCES unidades_medida_tipo(id),  
  activa BOOLEAN DEFAULT true,  
  base BOOLEAN DEFAULT false,  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE unidades_conversion (  
  id BIGSERIAL PRIMARY KEY,  
  origen_id BIGINT REFERENCES unidades_medida(id),  
  destino_id BIGINT REFERENCES unidades_medida(id),  
  factor NUMERIC(18,8) NOT NULL,  
  CONSTRAINT uq_conversion UNIQUE (origen_id, destino_id)  
);  
  
CREATE TABLE tipos_producto (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(150)  
);  
  
CREATE TABLE metodos_valoracion(  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(150)  
);  
  
CREATE TABLE productos (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(64) UNIQUE,  
  nombre VARCHAR(150) NOT NULL,  
  metodo_valoracion_id BIGINT REFERENCES metodos_valoracion(id),  
  tipo_id BIGINT REFERENCES tipos_producto(id),  
  unidad_base_id BIGINT REFERENCES unidades_medida(id),  
  costo_base NUMERIC(18,4) DEFAULT 0,  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE lotes_produccion (
  id BIGSERIAL PRIMARY KEY,
  numero_lote VARCHAR(100) UNIQUE NOT NULL,
  producto_id BIGINT REFERENCES productos(id),
  fabricado_en TIMESTAMPTZ,
  vence_en TIMESTAMPTZ,
  creado_en TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE ordenes_produccion (
  id BIGSERIAL PRIMARY KEY,
  numero_orden VARCHAR(100) UNIQUE NOT NULL,
  cantidad NUMERIC(18,6) NOT NULL DEFAULT 0,
  unidad_id BIGINT REFERENCES unidades_medida(id),
  estado_id BIGINT REFERENCES estados_orden(id),
  inicio_planificado TIMESTAMPTZ,
  fin_planificado TIMESTAMPTZ,
  inicio_real TIMESTAMPTZ,
  fin_real TIMESTAMPTZ,
  cantidad_desperdicio NUMERIC(18,6) DEFAULT 0,
  cantidad_producida NUMERIC(18,6) DEFAULT 0,
  creado_por BIGINT REFERENCES usuarios(id),
  observaciones VARCHAR(150),
  creado_en TIMESTAMPTZ DEFAULT now(),
  actualizado_en TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE ordenes_lotes (
  orden_id BIGINT NOT NULL REFERENCES ordenes_produccion(id) ON DELETE CASCADE,
  lote_id BIGINT NOT NULL REFERENCES lotes_produccion(id) ON DELETE CASCADE,
  PRIMARY KEY (orden_id, lote_id)
);

CREATE TABLE lineas_orden (  
  id BIGSERIAL PRIMARY KEY,  
  orden_id BIGINT REFERENCES ordenes_produccion(id),  
  numero_linea INT NOT NULL DEFAULT 1,  
  producto_componente_id BIGINT REFERENCES productos(id),  
  cantidad_requerida NUMERIC(18,6) NOT NULL DEFAULT 0,  
  unidad_componente_id BIGINT REFERENCES unidades_medida(id),  
  cantidad_usada NUMERIC(18,6) DEFAULT 0,  
  costo_unitario NUMERIC(18,6) DEFAULT 0,  
  costo_total NUMERIC(18,6) GENERATED ALWAYS AS (cantidad_requerida * costo_unitario) STORED,  
  observaciones VARCHAR(150),  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE costos_orden (  
  id BIGSERIAL PRIMARY KEY,  
  orden_id BIGINT REFERENCES ordenes_produccion(id),  
  tipo_costo_id BIGINT REFERENCES tipos_costo(id),  
  descripcion VARCHAR(150),  
  monto NUMERIC(18,6) NOT NULL DEFAULT 0,  
  moneda VARCHAR(5)  DEFAULT 'COP',  
  registrado_en TIMESTAMPTZ DEFAULT now()  
);

CREATE TABLE tipos_bodega (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(150)  
);  
  
CREATE TABLE bodegas (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(150),  
  tipo_bodega_id BIGINT REFERENCES tipos_bodega(id),  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE tipos_movimiento (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(255),  
  afecta_wip BOOLEAN DEFAULT false,  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE movimientos_inventario (  
  id BIGSERIAL PRIMARY KEY,  
  fecha TIMESTAMPTZ DEFAULT now(),  
  bodega_origen_id BIGINT REFERENCES bodegas(id),  
  bodega_destino_id BIGINT REFERENCES bodegas(id),  
  tipo_movimiento_id BIGINT REFERENCES tipos_movimiento(id),  
  referencia VARCHAR(150),  
  observaciones VARCHAR(150),  
  creado_por BIGINT REFERENCES usuarios(id),  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE movimiento_inventario_detalle (  
  id BIGSERIAL PRIMARY KEY,  
  movimiento_id BIGINT REFERENCES movimientos_inventario(id),  
  producto_id BIGINT REFERENCES productos(id),  
  lote_id BIGINT REFERENCES lotes_produccion(id),  
  cantidad NUMERIC(18,6) NOT NULL DEFAULT 0,  
  unidad_id BIGINT REFERENCES unidades_medida(id),  
  costo_unitario NUMERIC(18,6) DEFAULT 0,  
  costo_total NUMERIC(18,6) GENERATED ALWAYS AS (cantidad * costo_unitario) STORED  
);  

CREATE TABLE inventarios_lote (  
  id BIGSERIAL PRIMARY KEY,  
  producto_id BIGINT REFERENCES productos(id),  
  lote_id BIGINT REFERENCES lotes_produccion(id),  
  bodega_id BIGINT REFERENCES bodegas(id),  
  cantidad NUMERIC(18,6) NOT NULL DEFAULT 0,  
  unidad_id BIGINT REFERENCES unidades_medida(id),  
  actualizado_en TIMESTAMPTZ DEFAULT now(),  
  CONSTRAINT uq_producto_lote_bodega UNIQUE (producto_id, lote_id, bodega_id)  
);  

CREATE TABLE reservas_material_orden (  
  id BIGSERIAL PRIMARY KEY,  
  orden_id BIGINT REFERENCES ordenes_produccion(id),  
  producto_id BIGINT REFERENCES productos(id),  
  lote_id BIGINT REFERENCES lotes_produccion(id),  
  cantidad_reservada NUMERIC(18,6),  
  unidad_id BIGINT REFERENCES unidades_medida(id),  
  fecha_reserva TIMESTAMPTZ DEFAULT now()  
);
  
CREATE TABLE estaciones_produccion (  
  id BIGSERIAL PRIMARY KEY,  
  codigo VARCHAR(50) UNIQUE NOT NULL,  
  nombre VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(150),  
  orden INT NOT NULL,  
  creado_en TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE estados_orden_estacion(
	id BIGSERIAL PRIMARY KEY,  
	nombre VARCHAR(100) NOT NULL,
	descripcion VARCHAR(100)
);

CREATE TABLE ordenes_estacion (  
  id BIGSERIAL PRIMARY KEY,  
  orden_id BIGINT REFERENCES ordenes_produccion(id),  
  estacion_id BIGINT REFERENCES estaciones_produccion(id),  
  inicio_planificado TIMESTAMPTZ,  
  fin_planificado TIMESTAMPTZ,  
  inicio_real TIMESTAMPTZ,  
  fin_real TIMESTAMPTZ,  
  estado_orden_estacion_id BIGINT REFERENCES estados_orden_estacion(id),  
  observaciones VARCHAR(150)  
);  
  
CREATE TABLE ordenes_evento (  
  id BIGSERIAL PRIMARY KEY,  
  orden_id BIGINT REFERENCES ordenes_produccion(id),  
  evento VARCHAR(100) NOT NULL,  
  descripcion VARCHAR(150),  
  fecha TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE conteos_ciclico (  
  id BIGSERIAL PRIMARY KEY,  
  producto_id BIGINT REFERENCES productos(id),  
  bodega_id BIGINT REFERENCES bodegas(id),  
  lote_id BIGINT REFERENCES lotes_produccion(id),  
  cantidad_contada NUMERIC(18,6) NOT NULL,  
  unidad_id BIGINT REFERENCES unidades_medida(id),  
  fecha TIMESTAMPTZ DEFAULT now()  
);  
  
CREATE TABLE discrepancias_inventario (  
  id BIGSERIAL PRIMARY KEY,  
  conteo_id BIGINT REFERENCES conteos_ciclico(id),  
  cantidad_sistema NUMERIC(18,6) NOT NULL,  
  resuelto BOOLEAN DEFAULT false  
);  
  
CREATE TABLE puntos_reorden (  
  id BIGSERIAL PRIMARY KEY,  
  producto_id BIGINT REFERENCES productos(id),  
  stock_minimo NUMERIC(18,6) NOT NULL,  
  stock_seguridad NUMERIC(18,6) DEFAULT 0,  
  unidad_id BIGINT REFERENCES unidades_medida(id)  
);

CREATE TABLE estados_asignacion(
	id BIGSERIAL PRIMARY KEY,  
	nombre VARCHAR(100) NOT NULL,
	descripcion VARCHAR(100)
);

CREATE TABLE estados_aprobacion(
	id BIGSERIAL PRIMARY KEY,  
	nombre VARCHAR(100) NOT NULL,
	descripcion VARCHAR(100)
);

CREATE TABLE tipos_actividad(
	id BIGSERIAL PRIMARY KEY,  
	nombre VARCHAR(100) NOT NULL,
	descripcion VARCHAR(100)
);

CREATE TABLE estados_orden_trabajo ( 
	id BIGSERIAL PRIMARY KEY,
	nombre VARCHAR(100) UNIQUE NOT NULL,
	descripcion VARCHAR(100) 
 );
 
 CREATE TABLE tipos_orden_trabajo ( 
	id BIGSERIAL PRIMARY KEY,
	nombre VARCHAR(100) UNIQUE NOT NULL,
	descripcion VARCHAR(100) 
 );

CREATE TABLE ordenes_trabajo (
  id BIGSERIAL PRIMARY KEY,
  referencia VARCHAR(150),
  tipo_id BIGINT REFERENCES tipos_orden_trabajo(id),
  descripcion VARCHAR(150),
  inicio_planificado TIMESTAMPTZ,
  fin_planificado TIMESTAMPTZ,
  estado_id BIGINT REFERENCES estados_orden_trabajo(id),
  creado_en TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE funciones_tarea ( 
id BIGSERIAL PRIMARY KEY, 
nombre VARCHAR(100) UNIQUE NOT NULL, 
descripcion VARCHAR(150) 
);

CREATE TABLE asignaciones (
  id BIGSERIAL PRIMARY KEY,
  orden_trabajo_id BIGINT REFERENCES ordenes_trabajo(id) ON DELETE CASCADE,
  usuario_id BIGINT REFERENCES usuarios(id) ON DELETE CASCADE,
  inicio_planificado TIMESTAMPTZ NOT NULL,
  fin_planificado TIMESTAMPTZ NOT NULL,
  horas_planificadas INTERVAL,
  asignado_por BIGINT REFERENCES usuarios(id),
  estado_asignacion_id BIGINT REFERENCES estados_asignacion(id) NOT NULL,
  funcion_tarea BIGINT REFERENCES funciones_tarea(id) NOT NULL,
  creado_en TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE hojas_timesheet (
  id BIGSERIAL PRIMARY KEY,
  usuario_id BIGINT REFERENCES usuarios(id) NOT NULL,
  inicio_periodo DATE NOT NULL,
  fin_periodo DATE NOT NULL,
  estado_aprobacion_id BIGINT NOT NULL REFERENCES estados_aprobacion(id),
  aprobado_por BIGINT REFERENCES usuarios(id),
  aprobado_en TIMESTAMPTZ,
  creado_en TIMESTAMPTZ DEFAULT now(),
  UNIQUE (usuario_id, inicio_periodo, fin_periodo)
);

CREATE TABLE registros_tiempo (
  id BIGSERIAL PRIMARY KEY,
  asignacion_id BIGINT REFERENCES asignaciones(id),
  inicio_tz TIMESTAMPTZ NOT NULL,
  fin_tz TIMESTAMPTZ NOT NULL,
  duracion INTERVAL GENERATED ALWAYS AS (fin_tz - inicio_tz) STORED,
  tipo_actividad_id BIGINT REFERENCES tipos_actividad(id),
  tipo_costo_id BIGINT REFERENCES tipos_costo(id),
  estado_aprobacion_id BIGINT NOT NULL REFERENCES estados_aprobacion(id),
  hoja_timesheet_id BIGINT REFERENCES hojas_timesheet(id),
  creado_en TIMESTAMPTZ DEFAULT now(),
  actualizado_en TIMESTAMPTZ DEFAULT now(),
  CHECK (fin_tz > inicio_tz)
);

CREATE TABLE excepciones_tiempo (
  id BIGSERIAL PRIMARY KEY,
  usuario_id BIGINT REFERENCES usuarios(id),
  periodo tstzrange,
  detalles VARCHAR(150),
  creado_en TIMESTAMPTZ DEFAULT now(),
  resuelto BOOLEAN DEFAULT false
);

CREATE TABLE tarifas_empleado (
  id BIGSERIAL PRIMARY KEY,
  usuario_id BIGINT REFERENCES usuarios(id),
  tarifa NUMERIC(12,2) NOT NULL,
  moneda VARCHAR(3) DEFAULT 'COP',
  vigente_desde DATE NOT NULL,
  vigente_hasta DATE,
  creado_en TIMESTAMPTZ DEFAULT now()
);