CREATE OR REPLACE FUNCTION set_creation_timestamp() RETURNS trigger AS
$$
BEGIN
    NEW.created_at := current_timestamp;
    NEW.updated_at := NEW.created_at;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION set_update_timestamp() RETURNS trigger AS
$$
BEGIN
    NEW.updated_at := current_timestamp;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;