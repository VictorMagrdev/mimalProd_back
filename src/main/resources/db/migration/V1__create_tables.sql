CREATE TABLE roles (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) UNIQUE NOT NULL,
  description TEXT
);

CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(150) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  active BOOLEAN DEFAULT TRUE
);

CREATE TABLE user_roles (
  user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  role_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role_id)
);

CREATE TABLE tags (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(150) UNIQUE NOT NULL,
  description TEXT,
  owner_role_id BIGINT REFERENCES roles(id)
);

CREATE TABLE objects (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(100),
  metadata JSONB
);

CREATE TABLE object_tags (
  object_id BIGINT REFERENCES objects(id) ON DELETE CASCADE,
  tag_id BIGINT REFERENCES tags(id) ON DELETE CASCADE,
  PRIMARY KEY (object_id, tag_id)
);

CREATE TABLE permissions (
  id BIGSERIAL PRIMARY KEY,
  action VARCHAR(50) NOT NULL,
  description TEXT
);

CREATE TABLE policies (
  id BIGSERIAL PRIMARY KEY,
  role_id BIGINT REFERENCES roles(id),
  tag_id BIGINT REFERENCES tags(id),
  permission_id BIGINT REFERENCES permissions(id),
  UNIQUE (role_id, tag_id, permission_id)
);

CREATE TABLE attributes (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  value TEXT,
  user_id BIGINT REFERENCES users(id)
);

CREATE TABLE policy_conditions (
  id BIGSERIAL PRIMARY KEY,
  policy_id BIGINT REFERENCES policies(id) ON DELETE CASCADE,
  attribute_name VARCHAR(150) NOT NULL,
  operator VARCHAR(10) NOT NULL,
  expected_value TEXT
);

CREATE TABLE audit_logs (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES users(id),
  object_id BIGINT REFERENCES objects(id),
  action VARCHAR(100),
  result VARCHAR(20),
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);
