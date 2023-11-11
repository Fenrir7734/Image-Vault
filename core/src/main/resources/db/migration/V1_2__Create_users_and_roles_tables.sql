CREATE TABLE roles
(
    id   BIGSERIAL   NOT NULL,
    name VARCHAR(20) NOT NULL,

    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uq_roles_name UNIQUE (name)
);

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN');
INSERT INTO roles (name)
VALUES ('ROLE_USER');
INSERT INTO roles (name)
VALUES ('ROLE_VIEWER');

CREATE TABLE users
(
    id            BIGSERIAL    NOT NULL,
    code          CHAR(32)     NOT NULL DEFAULT md5(random()::text),
    auth_type     VARCHAR(50)  NOT NULL,
    external_id   VARCHAR(256) NOT NULL,
    external_name VARCHAR(100) NOT NULL,
    name          VARCHAR(100),
    email         VARCHAR(256) NOT NULL,
    verified      BOOLEAN      NOT NULL DEFAULT (FALSE),
    enabled       BOOLEAN      NOT NULL DEFAULT (TRUE),
    updated_at    TIMESTAMP,
    created_at    TIMESTAMP,
    role_id       BIGINT       NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_code UNIQUE (code),
    CONSTRAINT uq_users_email UNIQUE (email),
    CONSTRAINT uq_users_external_id UNIQUE (external_id),

    CONSTRAINT fk_roles_users FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TRIGGER users_set_created_at
    BEFORE INSERT
    ON users
    FOR EACH ROW
EXECUTE PROCEDURE set_creation_timestamp();

CREATE TRIGGER users_set_updated_at
    BEFORE UPDATE
    ON users
    FOR EACH ROW
EXECUTE PROCEDURE set_update_timestamp();