CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table if not exists roles
(
    name        varchar     not null,
    UNIQUE      (name),
    PRIMARY KEY (name)
    );
create table if not exists users
(
    id          uuid         not null,
    first_name  varchar(100) not null,
    last_name   varchar(100) not null,
    role        varchar,
    UNIQUE      (id),
    PRIMARY KEY (id)
    );
ALTER TABLE users ADD CONSTRAINT fk_roles FOREIGN KEY (role) references roles(name);

create table if not exists currency
(
    name          varchar not null,
    quantity      float   not null,
    exchange_rate float   not null,
    date          date    not null,
    UNIQUE (name),
    PRIMARY KEY (name)
);