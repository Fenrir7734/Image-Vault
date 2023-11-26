CREATE TABLE startup
(
    name    VARCHAR(100)    NOT NULL,
    value   VARCHAR(100)    NULL,

    CONSTRAINT uq_startup_name UNIQUE (name)
);

INSERT INTO startup (name, value)
VALUES ('ADMIN_CREATED', 'false');