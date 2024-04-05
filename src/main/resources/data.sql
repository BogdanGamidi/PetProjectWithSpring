CREATE TABLE IF NOT EXISTS person (
    id bigserial PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS post (
    id bigserial PRIMARY KEY,
    person_id bigint REFERENCES person(id),
    title VARCHAR(50) NOT NULL,
    content TEXT
);

CREATE TABLE IF NOT EXISTS subscription (
    id bigserial PRIMARY KEY,
    person_sender_id bigint REFERENCES person(id),
    person_receiver_id bigint REFERENCES person(id),
    created_date timestamp NOT NULL default current_timestamp
);

CREATE TABLE IF NOT EXISTS message (
    id bigserial PRIMARY KEY,
    person_sender_id bigint REFERENCES person(id),
    person_receiver_id bigint REFERENCES person(id),
    created_date timestamp NOT NULL default current_timestamp,
    content TEXT
);

// function
CREATE OR REPLACE FUNCTION person_do_post_fnc()
    RETURNS trigger AS
$$
BEGIN
    INSERT INTO "person_post" ("person_id", "post_id")
VALUES (NEW."person_id", NEW."id");
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

// trigger
CREATE TRIGGER person_do_post_trigger
    AFTER INSERT
    ON "post"
    FOR EACH ROW
    EXECUTE PROCEDURE person_do_post_fnc();