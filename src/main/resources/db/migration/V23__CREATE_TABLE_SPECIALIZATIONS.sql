CREATE TABLE specializations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

ALTER TABLE users ADD COLUMN specialization_id UUID REFERENCES specializations(id) ON DELETE SET NULL;

ALTER TABLE tickets DROP COLUMN ticket_type;
ALTER TABLE tickets ADD COLUMN specialization_id UUID REFERENCES specializations(id) ON DELETE SET NULL;