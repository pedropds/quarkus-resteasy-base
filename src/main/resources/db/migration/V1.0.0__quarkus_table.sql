CREATE TABLE appuser
(
  email TEXT UNIQUE,
  name TEXT,
  password TEXT,
  roles TEXT
);

INSERT INTO appuser(email, name, password, roles)
VALUES ('johndoe@gmail.com', 'John Doe', '123456', 'ADMIN;USER');