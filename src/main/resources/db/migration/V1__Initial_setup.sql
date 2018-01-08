DROP TABLE IF EXISTS user_model;

CREATE TABLE user_model (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  client_token VARCHAR(255),
  user_email VARCHAR(255),
  access_token VARCHAR(255)
);

DROP TABLE IF EXISTS Auth_Model;

CREATE TABLE Auth_Model (
  email VARCHAR(255) NOT NULL PRIMARY KEY,
  auth_code VARCHAR(255),
  display_name VARCHAR(255),
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user_model(id)
);