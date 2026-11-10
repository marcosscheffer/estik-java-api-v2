INSERT INTO permissions ("role_id", "permission") 
SELECT r.id, unnest(ARRAY[
    'TICKET_SUPERVISOR'
    ]) FROM roles r where r.name = 'SUPER';