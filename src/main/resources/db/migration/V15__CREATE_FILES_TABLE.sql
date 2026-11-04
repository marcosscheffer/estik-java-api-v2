CREATE TABLE files(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    file_name VARCHAR(100) NOT NULL,
    file_type VARCHAR(50) NOT NULL,
    file_size BIGINT NOT NULL,
    file_url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);

ALTER TABLE attachments ADD COLUMN file_id UUID NOT NULL REFERENCES files(id) ON DELETE CASCADE;

CREATE INDEX idx_attachments_file_id ON attachments(file_id);