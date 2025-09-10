-- 1. Crear objetos (uno por uno)
INSERT INTO objects (name, type, metadata) VALUES ('ESTADO_ORDEN', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('TIPO_COSTO', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('UNIDAD_MEDIDA_TIPO', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('UNIDAD_MEDIDA', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('UNIDAD_CONVERSION', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('PRODUCTO', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('LOTE_PRODUCCION', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('ORDEN_PRODUCCION', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('LINEA_ORDEN', 'DOMAIN','{"description":"test"}');
INSERT INTO objects (name, type, metadata) VALUES ('COSTO_ORDEN', 'DOMAIN','{"description":"test"}');

-- 2. Crear tags (uno por uno)
INSERT INTO tags (name, description, owner_role_id) VALUES ('ESTADO_ORDEN_TAG', 'Tag para ESTADO_ORDEN', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('TIPO_COSTO_TAG', 'Tag para TIPO_COSTO', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('UNIDAD_MEDIDA_TIPO_TAG', 'Tag para UNIDAD_MEDIDA_TIPO', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('UNIDAD_MEDIDA_TAG', 'Tag para UNIDAD_MEDIDA', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('UNIDAD_CONVERSION_TAG', 'Tag para UNIDAD_CONVERSION', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('PRODUCTO_TAG', 'Tag para PRODUCTO', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('LOTE_PRODUCCION_TAG', 'Tag para LOTE_PRODUCCION', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('ORDEN_PRODUCCION_TAG', 'Tag para ORDEN_PRODUCCION', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('LINEA_ORDEN_TAG', 'Tag para LINEA_ORDEN', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
INSERT INTO tags (name, description, owner_role_id) VALUES ('COSTO_ORDEN_TAG', 'Tag para COSTO_ORDEN', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));

-- 3. Asociar objetos con tags
INSERT INTO object_tags (object_id, tag_id)
SELECT o.id, t.id FROM objects o JOIN tags t ON t.name = o.name || '_TAG';

-- 4. Crear policies (ADMIN + tags + permisos universales)
INSERT INTO policies (role_id, tag_id, permission_id)
SELECT r.id, t.id, p.id
FROM roles r
JOIN tags t ON t.name IN (
    'ESTADO_ORDEN_TAG',
    'TIPO_COSTO_TAG',
    'UNIDAD_MEDIDA_TIPO_TAG',
    'UNIDAD_MEDIDA_TAG',
    'UNIDAD_CONVERSION_TAG',
    'PRODUCTO_TAG',
    'LOTE_PRODUCCION_TAG',
    'ORDEN_PRODUCCION_TAG',
    'LINEA_ORDEN_TAG',
    'COSTO_ORDEN_TAG'
)
JOIN permissions p ON p.action IN ('CREATE','READ','UPDATE','DELETE')
WHERE r.name = 'ROLE_ADMIN';
