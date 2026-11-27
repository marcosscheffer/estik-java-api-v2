CREATE TABLE solution_posts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    author_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
    specialization_id UUID REFERENCES specializations(id) ON DELETE SET NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(800) NOT NULL,
    file_id UUID REFERENCES files(id) ON DELETE SET NULL,
    resolved BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE solution_responses(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    author_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
    solution_post_id UUID NOT NULL REFERENCES solution_posts(id) ON DELETE CASCADE,
    message VARCHAR(800) NOT NULL,
    file_id UUID REFERENCES files(id) ON DELETE SET NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);