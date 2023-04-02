ALTER TABLE users
    ADD email varchar  NOT NULL UNIQUE,
    ADD password varchar NOT NULL;