CREATE TABLE album_visibility
(
    id   BIGSERIAL   NOT NULL,
    name VARCHAR(20) NOT NULL,

    CONSTRAINT pk_alvisibility PRIMARY KEY (id),
    CONSTRAINT uq_alvisibility_name UNIQUE (name)
);

INSERT INTO album_visibility (name)
VALUES ('PRIVATE');
INSERT INTO album_visibility (name)
VALUES ('PUBLIC_RO');
INSERT INTO album_visibility (name)
VALUES ('PUBLIC_RW');

CREATE TABLE albums
(
    id            BIGSERIAL    NOT NULL,
    name          VARCHAR(100) NOT NULL,
    description   VARCHAR(1000),
    updated_at    TIMESTAMP,
    created_at    TIMESTAMP,
    owner_id      BIGINT       NOT NULL,
    visibility_id BIGINT       NOT NULL,

    CONSTRAINT pk_albums PRIMARY KEY (id),

    CONSTRAINT fk_users_albums FOREIGN KEY (owner_id) REFERENCES users (id),
    CONSTRAINT fk_alvisibility_albums FOREIGN KEY (visibility_id) REFERENCES album_visibility (id)
);

CREATE TRIGGER albums_set_created_at
    BEFORE INSERT
    ON albums
    FOR EACH ROW
EXECUTE PROCEDURE set_creation_timestamp();

CREATE TRIGGER albums_set_updated_at
    BEFORE UPDATE
    ON albums
    FOR EACH ROW
EXECUTE PROCEDURE set_update_timestamp();