CREATE TABLE departaments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    facility_id UUID references facilities(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT uq_departaments_name_facility_id UNIQUE (name, facility_id)
);

CREATE INDEX idx_departaments_facility_id ON departaments(facility_id);