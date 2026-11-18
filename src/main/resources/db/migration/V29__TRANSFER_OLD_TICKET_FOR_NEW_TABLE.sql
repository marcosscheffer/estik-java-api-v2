INSERT INTO assigneeds (ticket_id, user_id)
SELECT id, assignee_id FROM tickets WHERE assignee_id IS NOT NULL;

INSERT INTO forwarded (ticket_id, user_id)
SELECT id, forwarded_id FROM tickets WHERE forwarded_id IS NOT NULL;

ALTER TABLE tickets DROP COLUMN assignee_id;
ALTER TABLE tickets DROP COLUMN forwarded_id;