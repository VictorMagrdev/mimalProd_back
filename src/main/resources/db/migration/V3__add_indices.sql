-- Índices para la sección de Usuarios y Permisos
CREATE INDEX idx_usuarios_centro_costo_id ON usuarios(centro_costo_id);
CREATE INDEX idx_tags_owner_rol_id ON tags(owner_rol_id);
CREATE INDEX idx_policy_conditions_politica_id ON policy_conditions(politica_id);

-- Índices para la sección de Productos y Unidades
CREATE INDEX idx_unidades_medida_tipo_id ON unidades_medida(unidad_medida_tipo_id);
CREATE INDEX idx_productos_metodo_valoracion_id ON productos(metodo_valoracion_id);
CREATE INDEX idx_productos_tipo_id ON productos(tipo_id);
CREATE INDEX idx_productos_unidad_base_id ON productos(unidad_base_id);

-- Índices para la sección de Producción
CREATE INDEX idx_lotes_produccion_producto_id ON lotes_produccion(producto_id);
CREATE INDEX idx_ordenes_produccion_unidad_id ON ordenes_produccion(unidad_id);
CREATE INDEX idx_ordenes_produccion_estado_id ON ordenes_produccion(estado_id);
CREATE INDEX idx_ordenes_produccion_creado_por ON ordenes_produccion(creado_por);
CREATE INDEX idx_lineas_orden_orden_id ON lineas_orden(orden_id);
CREATE INDEX idx_lineas_orden_producto_componente_id ON lineas_orden(producto_componente_id);
CREATE INDEX idx_lineas_orden_unidad_componente_id ON lineas_orden(unidad_componente_id);
CREATE INDEX idx_costos_orden_orden_id ON costos_orden(orden_id);
CREATE INDEX idx_costos_orden_tipo_costo_id ON costos_orden(tipo_costo_id);

-- Índices para la sección de Inventario y Bodegas
CREATE INDEX idx_bodegas_tipo_bodega_id ON bodegas(tipo_bodega_id);
CREATE INDEX idx_movimientos_inventario_bodega_origen_id ON movimientos_inventario(bodega_origen_id);
CREATE INDEX idx_movimientos_inventario_bodega_destino_id ON movimientos_inventario(bodega_destino_id);
CREATE INDEX idx_movimientos_inventario_tipo_movimiento_id ON movimientos_inventario(tipo_movimiento_id);
CREATE INDEX idx_movimientos_inventario_creado_por ON movimientos_inventario(creado_por);
CREATE INDEX idx_movimiento_detalle_movimiento_id ON movimiento_inventario_detalle(movimiento_id);
CREATE INDEX idx_movimiento_detalle_producto_id ON movimiento_inventario_detalle(producto_id);
CREATE INDEX idx_movimiento_detalle_lote_id ON movimiento_inventario_detalle(lote_id);
CREATE INDEX idx_movimiento_detalle_unidad_id ON movimiento_inventario_detalle(unidad_id);
CREATE INDEX idx_reservas_material_orden_orden_id ON reservas_material_orden(orden_id);
CREATE INDEX idx_reservas_material_orden_producto_id ON reservas_material_orden(producto_id);
CREATE INDEX idx_reservas_material_orden_lote_id ON reservas_material_orden(lote_id);
CREATE INDEX idx_reservas_material_orden_unidad_id ON reservas_material_orden(unidad_id);
CREATE INDEX idx_conteos_ciclico_producto_id ON conteos_ciclico(producto_id);
CREATE INDEX idx_conteos_ciclico_bodega_id ON conteos_ciclico(bodega_id);
CREATE INDEX idx_conteos_ciclico_lote_id ON conteos_ciclico(lote_id);
CREATE INDEX idx_conteos_ciclico_unidad_id ON conteos_ciclico(unidad_id);
CREATE INDEX idx_discrepancias_inventario_conteo_id ON discrepancias_inventario(conteo_id);
CREATE INDEX idx_puntos_reorden_producto_id ON puntos_reorden(producto_id);
CREATE INDEX idx_puntos_reorden_unidad_id ON puntos_reorden(unidad_id);

-- Índices para la sección de Estaciones de Producción
CREATE INDEX idx_ordenes_estacion_orden_id ON ordenes_estacion(orden_id);
CREATE INDEX idx_ordenes_estacion_estacion_id ON ordenes_estacion(estacion_id);
CREATE INDEX idx_ordenes_estacion_estado_id ON ordenes_estacion(estado_orden_estacion_id);
CREATE INDEX idx_ordenes_evento_orden_id ON ordenes_evento(orden_id);

-- Índices para la sección de Órdenes de Trabajo y Timesheets
CREATE INDEX idx_ordenes_trabajo_tipo_id ON ordenes_trabajo(tipo_id);
CREATE INDEX idx_ordenes_trabajo_estado_id ON ordenes_trabajo(estado_id);
CREATE INDEX idx_asignaciones_orden_trabajo_id ON asignaciones(orden_trabajo_id);
CREATE INDEX idx_asignaciones_usuario_id ON asignaciones(usuario_id);
CREATE INDEX idx_asignaciones_asignado_por ON asignaciones(asignado_por);
CREATE INDEX idx_asignaciones_estado_asignacion_id ON asignaciones(estado_asignacion_id);
CREATE INDEX idx_asignaciones_funcion_tarea ON asignaciones(funcion_tarea);
CREATE INDEX idx_hojas_timesheet_usuario_id ON hojas_timesheet(usuario_id);
CREATE INDEX idx_hojas_timesheet_estado_aprobacion_id ON hojas_timesheet(estado_aprobacion_id);
CREATE INDEX idx_hojas_timesheet_aprobado_por ON hojas_timesheet(aprobado_por);
CREATE INDEX idx_registros_tiempo_asignacion_id ON registros_tiempo(asignacion_id);
CREATE INDEX idx_registros_tiempo_tipo_actividad_id ON registros_tiempo(tipo_actividad_id);
CREATE INDEX idx_registros_tiempo_tipo_costo_id ON registros_tiempo(tipo_costo_id);
CREATE INDEX idx_registros_tiempo_estado_aprobacion_id ON registros_tiempo(estado_aprobacion_id);
CREATE INDEX idx_registros_tiempo_hoja_timesheet_id ON registros_tiempo(hoja_timesheet_id);
CREATE INDEX idx_excepciones_tiempo_usuario_id ON excepciones_tiempo(usuario_id);
CREATE INDEX idx_tarifas_empleado_usuario_id ON tarifas_empleado(usuario_id);