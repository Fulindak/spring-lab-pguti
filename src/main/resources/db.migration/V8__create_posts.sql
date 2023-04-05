CREATE TABLE if not exists posts
(
    id         UUID        NOT NULL,
    title      VARCHAR(50) NOT NULL,
    author_id  UUID        NOT NULL,
    body     JSONB,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_posts PRIMARY KEY (id)
);

ALTER TABLE posts
    ADD CONSTRAINT FK_POSTS_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES users (id);