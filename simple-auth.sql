CREATE DATABASE simple_auth WITH OWNER = postgres
ENCODING = 'UTF8' CONNECTION LIMIT = -1;

DROP TABLE account;

CREATE TABLE account
(
    id BIGSERIAL,
    email VARCHAR (50) UNIQUE NOT NULL,
    password VARCHAR (50) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO account
(email, password)
VALUES('admin@email.com', 'admin');
INSERT INTO account
(email, password)
VALUES('luciano@email.com', '123');
INSERT INTO account
(email, password)
VALUES('gabriel@email.com', '123');
