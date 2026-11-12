ALTER TABLE ticket_histories DROP COLUMN type;

ALTER TABLE ticket_histories ADD COLUMN specialization_id UUID REFERENCES specializations(id) ON DELETE SET NULL;