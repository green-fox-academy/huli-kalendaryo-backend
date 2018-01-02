DROP TABLE IF EXISTS user_model;

CREATE TABLE user_model (
  id BIGINT PRIMARY KEY NOT NULL,
  client_token VARCHAR(255),
  user_email VARCHAR(255),
  access_token VARCHAR(255)
);

DROP TABLE IF EXISTS Auth_Model;

CREATE TABLE Auth_Model (
  email VARCHAR(255) PRIMARY KEY NOT NULL,
  auth_code VARCHAR(255),
  display_name VARCHAR(255),
  user_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES user_model(id)
);
