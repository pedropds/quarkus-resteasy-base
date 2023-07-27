-- SEQ DEFINITION
CREATE SEQUENCE IF NOT EXISTS appuser_id_seq START 1 INCREMENT 1;

CREATE TABLE appuser
(
  id        BIGINT  NOT NULL DEFAULT nextval('appuser_id_seq'),
  email     TEXT UNIQUE,
  name      TEXT,
  password  TEXT,
  roles     TEXT
);

INSERT INTO appuser(email, name, password, roles)
VALUES ('johndoe@gmail.com', 'John Doe', '123456', 'ADMIN;USER');