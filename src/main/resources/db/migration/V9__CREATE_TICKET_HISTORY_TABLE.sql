CREATE TABLE ticket_histories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ticket_id UUID NOT NULL REFERENCES tickets(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL,
    type VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
)