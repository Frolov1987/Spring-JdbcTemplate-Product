Створюємо базу данних

CREATE DATABASE demo_db4;

Створюємо таблицю продуктів

CREATE TABLE IF NOT EXISTS products
( id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  quantity INTEGER NOT NULL,
  price DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (id)
);

