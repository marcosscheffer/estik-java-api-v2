ALTER TABLE users ADD COLUMN function VARCHAR(20) NOT NULL DEFAULT 'USER';

ALTER TABLE tickets ADD COLUMN forwarded_id UUID REFERENCES users(id);
ALTER TABLE tickets ADD COLUMN regulator_id UUID REFERENCES users(id);

CREATE INDEX idx_tickets_forwarded_to_id ON tickets(forwarded_id);