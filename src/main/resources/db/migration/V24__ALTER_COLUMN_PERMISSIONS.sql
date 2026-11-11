DELETE FROM permissions
WHERE "permission" = 'ACCESS_MANAGE';

INSERT INTO permissions ("role_id", "permission") 
SELECT r.id, unnest(ARRAY[
    'SYSTEM_MANAGE'
    ]) FROM roles r where r.name = 'SUPER';