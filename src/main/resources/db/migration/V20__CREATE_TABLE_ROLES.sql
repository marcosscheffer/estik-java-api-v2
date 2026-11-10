CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

INSERT INTO roles("name") VALUES ('SUPER');

CREATE TABLE permissions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    role_id UUID NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    permission VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT uk_roles_id_permission_permissions UNIQUE(role_id, permission)
);

CREATE INDEX idx_permissions_roles_id ON permissions(role_id);

INSERT INTO permissions ("role_id", "permission") 
    
SELECT r.id, unnest(ARRAY[
    'TICKET_OPEN', 
    'TICKET_WORK', 
    'TICKET_MANAGE',
    'STOCK_READ', 
    'STOCK_MANAGE',
    'COMPUTER_READ', 
    'COMPUTER_MANAGE',
    'FACILITY_MANAGE',
    'USER_MANAGE',
    'ACCESS_MANAGE'
    ]) FROM roles r where r.name = 'SUPER';