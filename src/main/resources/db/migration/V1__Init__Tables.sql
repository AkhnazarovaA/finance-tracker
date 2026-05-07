CREATE SEQUENCE user_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE transaction_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE item_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE users
(
    id        BIGINT PRIMARY KEY DEFAULT nextval('user_sequence'),
    username  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    user_role VARCHAR(50),
    password  VARCHAR(255) NOT NULL
);

CREATE TABLE transactions
(
    id               BIGINT PRIMARY KEY DEFAULT nextval('transaction_sequence'),
    transaction_type VARCHAR(20)    NOT NULL,
    amount           DECIMAL(19, 2) NOT NULL,
    category         VARCHAR(100)   NOT NULL,
    description      TEXT,
    transaction_date DATE           NOT NULL,
    user_id          BIGINT         NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE items
(
    id             BIGINT PRIMARY KEY DEFAULT nextval('item_sequence'),
    name           VARCHAR(255)   NOT NULL,
    amount         DECIMAL(19, 2) NOT NULL,
    quantity       INTEGER        NOT NULL,
    transaction_id BIGINT         NOT NULL,
    CONSTRAINT fk_transaction FOREIGN KEY (transaction_id) REFERENCES transactions (id) ON DELETE CASCADE
);