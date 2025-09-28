-- 1. Insertar Permisos (Operaciones)
INSERT INTO permissions (action, description) VALUES
('CREATE', 'Permite crear nuevos registros'),
('READ', 'Permite leer registros existentes'),
('UPDATE', 'Permite actualizar registros existentes'),
('DELETE', 'Permite eliminar registros existentes');

-- 2. Insertar Objetos (Módulos)
INSERT INTO objects (name, type, metadata) VALUES
('USER', 'ENTITY', '{"description":"Administración de Usuarios"}'),
('ROLE', 'ENTITY', '{"description":"Administración de Roles"}'),
('POLICY', 'ENTITY', '{"description":"Administración de Políticas de Acceso"}'),
('TAG', 'ENTITY', '{"description":"Administración de Etiquetas"}');

-- 3. Insertar Roles
INSERT INTO roles (name, description) VALUES
('ROLE_ADMIN', 'Administrador del sistema con acceso a la configuración'),
('ROLE_OPERATOR', 'Usuario estándar con permisos limitados');

-- 4. Insertar Tags (Etiquetas) para los objetos
INSERT INTO tags (name, description, owner_role_id) VALUES
('TAG_USERS', 'Etiqueta para usuarios', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TAG_ROLES', 'Etiqueta para roles', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TAG_POLICIES', 'Etiqueta para políticas', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
('TAG_GENERAL', 'Etiqueta para etiquetas', (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));

-- 5. Asociar objetos con tags
INSERT INTO object_tags (object_id, tag_id) VALUES
((SELECT id FROM objects WHERE name = 'USER'), (SELECT id FROM tags WHERE name = 'TAG_USERS')),
((SELECT id FROM objects WHERE name = 'ROLE'), (SELECT id FROM tags WHERE name = 'TAG_ROLES')),
((SELECT id FROM objects WHERE name = 'POLICY'), (SELECT id FROM tags WHERE name = 'TAG_POLICIES')),
((SELECT id FROM objects WHERE name = 'TAG'), (SELECT id FROM tags WHERE name = 'TAG_GENERAL'));

-- 6. Insertar Usuario Administrador
-- Contraseña: 'admin123' (bcrypt hash)
INSERT INTO users (username, email, password, active) VALUES
('admin', 'admin@example.com', '$2a$17$YFAsX6jOME7VCexvNKkgEuiykKj821bfIwIDen1MeEYFAg5xIc8jG', true);

-- 7. Asignar Rol de Administrador al Usuario 'admin'
INSERT INTO user_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE username = 'admin'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
);

-- 8. Crear Políticas de Acceso para el Rol de Administrador
-- Usando los tags correctos en lugar de objetos
INSERT INTO policies (role_id, tag_id, permission_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r, tags t, permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND t.name = 'TAG_USERS'
  AND p.action IN ('CREATE','READ','UPDATE','DELETE');

INSERT INTO policies (role_id, tag_id, permission_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r, tags t, permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND t.name = 'TAG_ROLES'
  AND p.action IN ('CREATE','READ','UPDATE','DELETE');

INSERT INTO policies (role_id, tag_id, permission_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r, tags t, permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND t.name = 'TAG_POLICIES'
  AND p.action IN ('CREATE','READ','DELETE');

INSERT INTO policies (role_id, tag_id, permission_id)
SELECT
    r.id,
    t.id,
    p.id
FROM roles r, tags t, permissions p
WHERE r.name = 'ROLE_ADMIN'
  AND t.name = 'TAG_GENERAL'
  AND p.action IN ('CREATE','READ');

-- 9. Insertar algunos atributos de ejemplo
INSERT INTO attributes (name, value, user_id) VALUES
('department', 'IT', (SELECT id FROM users WHERE username = 'admin')),
('security_level', 'high', (SELECT id FROM users WHERE username = 'admin'));

-- 10. Insertar condición de ejemplo para una política
INSERT INTO policy_conditions (policy_id, attribute_name, operator, expected_value)
SELECT
    p.id,
    'department',
    '=',
    'IT'
FROM policies p
WHERE p.role_id = (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
LIMIT 1;