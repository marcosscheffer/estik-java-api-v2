CREATE TABLE stock_balance (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    facility_id UUID NOT NULL REFERENCES facilities(id) ON DELETE CASCADE,
    departament_id UUID NOT NULL REFERENCES departaments(id) ON DELETE CASCADE,
    item_id UUID NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    quantity BIGINT NOT NULL DEFAULT 0,
    updated_at TIMESTAMP NOT NULL DEFAULT now()

    ADD CONSTRAINT UNIQUE(item_id, facility_id, departament_id);
);

CREATE TABLE stock_movement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    item_id UUID NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    type VARCHAR(50) NOT NULL,
    quantity BIGINT NOT NULL DEFAULT 0,
    source UUID NOT NULL REFERENCES facilities(id) ON DELETE CASCADE,
    destination UUID NOT NULL REFERENCES facilities(id) ON DELETE CASCADE,
    description VARCHAR(255), 
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_stock_balance_item_id ON stock_balance(item_id);
CREATE INDEX idx_stock_balance_facility_id ON stock_balance(facility_id);
CREATE INDEX idx_stock_balance_departament_id ON stock_balance(departament_id);

CREATE INDEX idx_stock_movement_item_id ON stock_movement(item_id);
CREATE INDEX idx_stock_movement_source ON stock_movement(source);
CREATE INDEX idx_stock_movement_destination ON stock_movement(destination);