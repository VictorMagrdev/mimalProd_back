-- Add production tag
INSERT INTO tags (name, description) VALUES ('PRODUCCION', 'Permisos para el modulo de produccion');

-- Add object entities
INSERT INTO object_entities (name, description) VALUES
('ESTADO_ORDEN', 'Entidad para los estados de las ordenes de produccion'),
('TIPO_COSTO', 'Entidad para los tipos de costos'),
('UNIDAD_MEDIDA_TIPO', 'Entidad para los tipos de unidades de medida'),
('UNIDAD_MEDIDA', 'Entidad para las unidades de medida'),
('UNIDAD_CONVERSION', 'Entidad para las conversiones de unidades de medida'),
('PRODUCTO', 'Entidad para los productos'),
('LOTE_PRODUCCION', 'Entidad para los lotes de produccion'),
('ORDEN_PRODUCCION', 'Entidad para las ordenes de produccion'),
('LINEA_ORDEN', 'Entidad para las lineas de las ordenes de produccion'),
('COSTO_ORDEN', 'Entidad para los costos de las ordenes de produccion');

-- Add permissions
DO $$
DECLARE
    tag_id bigint;
    object_id bigint;
BEGIN
    -- Get tag id
    SELECT id INTO tag_id FROM tags WHERE name = 'PRODUCCION';

    -- ESTADO_ORDEN
    SELECT id INTO object_id FROM object_entities WHERE name = 'ESTADO_ORDEN';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- TIPO_COSTO
    SELECT id INTO object_id FROM object_entities WHERE name = 'TIPO_COSTO';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- UNIDAD_MEDIDA_TIPO
    SELECT id INTO object_id FROM object_entities WHERE name = 'UNIDAD_MEDIDA_TIPO';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- UNIDAD_MEDIDA
    SELECT id INTO object_id FROM object_entities WHERE name = 'UNIDAD_MEDIDA';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- UNIDAD_CONVERSION
    SELECT id INTO object_id FROM object_entities WHERE name = 'UNIDAD_CONVERSION';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- PRODUCTO
    SELECT id INTO object_id FROM object_entities WHERE name = 'PRODUCTO';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- LOTE_PRODUCCION
    SELECT id INTO object_id FROM object_entities WHERE name = 'LOTE_PRODUCCION';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- ORDEN_PRODUCCION
    SELECT id INTO object_id FROM object_entities WHERE name = 'ORDEN_PRODUCCION';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- LINEA_ORDEN
    SELECT id INTO object_id FROM object_entities WHERE name = 'LINEA_ORDEN';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);

    -- COSTO_ORDEN
    SELECT id INTO object_id FROM object_entities WHERE name = 'COSTO_ORDEN';
    INSERT INTO permissions (action, object_entity_id, tag_id) VALUES
    ('CREATE', object_id, tag_id),
    ('READ', object_id, tag_id),
    ('UPDATE', object_id, tag_id),
    ('DELETE', object_id, tag_id);
END $$;
