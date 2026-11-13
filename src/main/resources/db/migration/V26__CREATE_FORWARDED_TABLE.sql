CREATE TABLE forwarded (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ticket_id NOT NUll REFERENCES tickets(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT uk_user_id_ticket_id_forwarded UNIQUE(ticket_id, user_id)
);

CREATE INDEX idx_user_id_forwarded ON forwarded(user_id);
CREATE INDEX idx_ticket_id_forwarded ON forwarded(ticket_id);