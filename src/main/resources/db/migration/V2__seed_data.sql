-- Roles base
INSERT INTO roles (nombre, descripcion) VALUES
('ADMIN', 'Superusuario con acceso total'),
('PLANNER', 'Planeador de producción'),
('OPERATOR', 'Operario de planta'),
('MANAGER', 'Jefe de planta / gerente');

-- Usuario admin con contraseña en BCrypt
INSERT INTO users (username, email, password, estado)
VALUES ('admin', 'admin@erp.com', '$2a$10$k9vPQ3W3EwYFzldpHD0/7OqFfD5xYZv2ZtJhXswT9qfxXjBgWxK2m', TRUE);
-- password = admin123

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);

-- Módulos del ERP
INSERT INTO modules (nombre, descripcion) VALUES
('Producción', 'Gestión de órdenes de producción y lotes'),
('Inventario', 'Control de insumos y productos terminados'),
('Compras', 'Gestión de proveedores y órdenes de compra'),
('Ventas', 'Gestión de clientes y pedidos');

-- Operaciones genéricas
INSERT INTO operations (nombre, descripcion) VALUES
('create', 'Crear registros'),
('read', 'Consultar registros'),
('update', 'Actualizar registros'),
('delete', 'Eliminar registros'),
('approve', 'Aprobar procesos o documentos');

-- Ejemplos de permisos
-- Producción: crear, consultar y aprobar
INSERT INTO permissions (module_id, operation_id) VALUES
(1, 1), -- Producción + create
(1, 2), -- Producción + read
(1, 5); -- Producción + approve

-- Inventario: consultar y actualizar
INSERT INTO permissions (module_id, operation_id) VALUES
(2, 2), -- Inventario + read
(2, 3); -- Inventario + update

-- Compras: crear, consultar, aprobar
INSERT INTO permissions (module_id, operation_id) VALUES
(3, 1), -- Compras + create
(3, 2), -- Compras + read
(3, 5); -- Compras + approve

-- Ventas: crear, consultar
INSERT INTO permissions (module_id, operation_id) VALUES
(4, 1), -- Ventas + create
(4, 2); -- Ventas + read

-- Políticas: asignar permisos a roles
-- ADMIN = todo
INSERT INTO policies (role_id, permission_id)
SELECT 1, id FROM permissions;

-- PLANNER: producción (create, read), inventario (read)
INSERT INTO policies (role_id, permission_id) VALUES
(2, 1), (2, 2), (2, 4);

-- OPERATOR: solo lectura en producción e inventario
INSERT INTO policies (role_id, permission_id) VALUES
(3, 2), (3, 4);

-- MANAGER: aprobar en producción y compras
INSERT INTO policies (role_id, permission_id) VALUES
(4, 3), (4, 8);
-- Usuario admin con contraseña en BCrypt (password = admin123)
INSERT INTO users (username, email, password, estado)
VALUES ('admin', 'admin@erp.com', '$2a$10$k9vPQ3W3EwYFzldpHD0/7OqFfD5xYZv2ZtJhXswT9qfxXjBgWxK2m', TRUE);

-- Asignar rol ADMIN al usuario
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
