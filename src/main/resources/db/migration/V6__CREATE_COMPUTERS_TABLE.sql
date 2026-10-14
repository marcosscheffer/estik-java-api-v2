CREATE TABLE computers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    cpu VARCHAR(100) NOT NULL,
    ram VARCHAR(10) NOT NULL,
    os VARCHAR(10) NOT NULL,
    storage_type VARCHAR(10) NOT NULL,
    storage VARCHAR(10) NOT NULL,
    facility_id UUID REFERENCES facilities(id) ON DELETE CASCADE,
    departament_id UUID REFERENCES departaments(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE computer_histories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    service_description VARCHAR(255),
    direction VARCHAR(10) NOT NULL,
    computer_id UUID REFERENCES computers(id) ON DELETE CASCADE,
    departament_id UUID REFERENCES departaments(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);


CREATE INDEX idx_computers_facility_id ON computers(facility_id);
CREATE INDEX idx_computers_departament_id ON computers(departament_id);