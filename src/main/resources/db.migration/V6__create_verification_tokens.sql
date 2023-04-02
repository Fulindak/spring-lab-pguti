CREATE TABLE if not exists verification_tokens
(
    id          UUID    NOT NULL,
    token       VARCHAR,
    user_id     UUID    NOT NULL,
    expiry_date TIMESTAMP WITHOUT TIME ZONE,
    confirmed   BOOLEAN NOT NULL,
    CONSTRAINT pk_verification_tokens PRIMARY KEY (id)
);
ALTER TABLE verification_tokens
    ADD CONSTRAINT FK_VERIFICATION_TOKENS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) on delete cascade;