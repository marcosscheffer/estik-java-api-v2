CREATE TABLE assigneeds(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    ticket_id UUID NOT NULL REFERENCES tickets(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT now()

    CONSTRAINT uk_user_id_ticket_id_assigneeds UNIQUE(ticket_id, user_id)
);

CREATE INDEX idx_user_id_assigneeds ON assigneeds(user_id);
CREATE INDEX idx_ticket_id_assigneeds ON assigneeds(ticket_id);