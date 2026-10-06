ALTER TABLE users 
    ADD COLUMN facility_id UUID,
    ADD CONSTRAINT fk_users_facilities
        FOREIGN KEY (facility_id) REFERENCES facilities(id) ON DELETE RESTRICT;

CREATE INDEX idx_users_facilities_id ON users(facility_id);
