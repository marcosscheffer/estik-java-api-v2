CREATE TABLE stock_balance (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    facility_id UUID NOT NULL REFERENCES facilities(id) ON DELETE CASCADE,
    departament_id UUID NOT NULL REFERENCES departaments(id) ON DELETE CASCADE,
    item_id UUID NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    quantity BIGINT NOT NULL DEFAULT 0,
    updated_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT uq_stock_balanceitem_id_facility_id_departament_id UNIQUE(item_id, facility_id, departament_id)
);

CREATE TABLE stock_movement (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    item_id UUID NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    type VARCHAR(50) NOT NULL,
    quantity BIGINT NOT NULL DEFAULT 0,
    facility_source UUID NOT NULL REFERENCES facilities(id) ON DELETE CASCADE,
    facility_destination UUID REFERENCES facilities(id) ON DELETE CASCADE,
    departament_source UUID NOT NULL REFERENCES departaments(id) ON DELETE CASCADE,
    departament_destination UUID REFERENCES departaments(id) ON DELETE CASCADE,
    description VARCHAR(255), 
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_stock_balance_item_id ON stock_balance(item_id);
CREATE INDEX idx_stock_balance_facility_id ON stock_balance(facility_id);
CREATE INDEX idx_stock_balance_departament_id ON stock_balance(departament_id);

CREATE INDEX idx_stock_movement_item_id ON stock_movement(item_id);
CREATE INDEX idx_stock_movement_facility_source ON stock_movement(facility_source);
CREATE INDEX idx_stock_movement_facility_destination ON stock_movement(facility_destination);
CREATE INDEX idx_stock_movement_departament_source ON stock_movement(departament_source);
CREATE INDEX idx_stock_movement_departament_destination ON stock_movement(departament_destination);