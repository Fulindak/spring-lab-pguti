create table if not exists currency
(
    name          varchar not null,
    quantity      float   not null,
    exchange_rate float   not null,
    date          date    not null,
    UNIQUE (name),
    PRIMARY KEY (name)
);