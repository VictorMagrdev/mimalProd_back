-- Limpieza de tablas para un estado consistente (opcional, bueno para desarrollo)
-- TRUNCATE TABLE policy, user_roles, users, roles, permission, object_entity RESTART IDENTITY CASCADE;

-- 1. Insertar Permisos (Operaciones)
-- Estos son los tipos de acciones que un usuario puede realizar.
INSERT INTO permission (name, description) VALUES
('CREATE', 'Permite crear nuevos registros'),
('READ', 'Permite leer registros existentes'),
('UPDATE', 'Permite actualizar registros existentes'),
('DELETE', 'Permite eliminar registros existentes');

-- 2. Insertar Entidades Objeto (Módulos)
-- Estos son los módulos o tipos de datos sobre los que se aplican los permisos.
INSERT INTO object_entity (name, description) VALUES
('USER', 'Administración de Usuarios'),
('ROLE', 'Administración de Roles'),
('POLICY', 'Administración de Políticas de Acceso'),
('TAG', 'Administración de Etiquetas');

-- 3. Insertar Roles
-- Roles principales del sistema.
INSERT INTO roles (name, description) VALUES
('ROLE_ADMIN', 'Administrador del sistema con acceso a la configuración'),
('ROLE_OPERATOR', 'Usuario estándar con permisos limitados');

-- 4. Insertar Usuario Administrador
-- Contraseña es 'admin123'
INSERT INTO users (username, email, password) VALUES
('admin', 'admin@example.com', '$2a$10$k9vPQ3W3EwYFzldpHD0/7OqFfD5xYZv2ZtJhXswT9qfxXjBgWxK2m');

-- 5. Asignar Rol de Administrador al Usuario 'admin'
INSERT INTO user_roles (user_id, role_id) VALUES
((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));

-- 6. Crear Políticas de Acceso para el Rol de Administrador
-- El administrador necesita permisos CRUD completos sobre los objetos de administración.

-- Permisos sobre Usuarios (USER)
INSERT INTO policy (role_id, object_entity_id, permission_id) VALUES
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'USER'), (SELECT id FROM permission WHERE name = 'CREATE')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'USER'), (SELECT id FROM permission WHERE name = 'READ')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'USER'), (SELECT id FROM permission WHERE name = 'UPDATE')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'USER'), (SELECT id FROM permission WHERE name = 'DELETE'));

-- Permisos sobre Roles (ROLE)
INSERT INTO policy (role_id, object_entity_id, permission_id) VALUES
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'ROLE'), (SELECT id FROM permission WHERE name = 'CREATE')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'ROLE'), (SELECT id FROM permission WHERE name = 'READ')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'ROLE'), (SELECT id FROM permission WHERE name = 'UPDATE')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'ROLE'), (SELECT id FROM permission WHERE name = 'DELETE'));

-- Permisos sobre Políticas (POLICY)
INSERT INTO policy (role_id, object_entity_id, permission_id) VALUES
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'POLICY'), (SELECT id FROM permission WHERE name = 'CREATE')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'POLICY'), (SELECT id FROM permission WHERE name = 'READ')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'POLICY'), (SELECT id FROM permission WHERE name = 'DELETE'));

-- Permisos sobre Etiquetas (TAG)
INSERT INTO policy (role_id, object_entity_id, permission_id) VALUES
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'TAG'), (SELECT id FROM permission WHERE name = 'CREATE')),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM object_entity WHERE name = 'TAG'), (SELECT id FROM permission WHERE name = 'READ'));