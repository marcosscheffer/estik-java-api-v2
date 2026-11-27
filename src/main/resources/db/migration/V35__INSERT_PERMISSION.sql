INSERT INTO permissions ("role_id", "permission") 
SELECT r.id, unnest(ARRAY[
    'SOLUTION_READ',
    'SOLUTION_WORK',
    'SOLUTION_MANAGE'
    ]) FROM roles r where r.name = 'SUPER'
    ON CONFLICT (role_id, permission) DO NOTHING;