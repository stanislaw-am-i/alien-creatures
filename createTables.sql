CREATE SEQUENCE IF NOT EXISTS users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS characters_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS users (
                                     id               INTEGER NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    username         VARCHAR(255) NOT NULL,
    email            VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    confirmation_code VARCHAR(255),
    is_active        BOOLEAN DEFAULT FALSE,
    role             VARCHAR(255) DEFAULT 'USER',
    CONSTRAINT users_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS characters (
                                          id      INTEGER NOT NULL DEFAULT nextval('characters_id_seq'::regclass),
    name    VARCHAR(255) NOT NULL,
    lor     TEXT,
    image   BYTEA,
    user_id INTEGER,
    CONSTRAINT characters_pkey PRIMARY KEY (id),
    CONSTRAINT characters_user_id_fkey
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );