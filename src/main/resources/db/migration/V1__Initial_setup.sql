DROP TABLE IF EXISTS user_model;

CREATE TABLE user_model (
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  client_token VARCHAR(255),
  user_email VARCHAR(255)
);

DROP TABLE IF EXISTS auth_model;

CREATE TABLE auth_model (
  email VARCHAR(255) NOT NULL PRIMARY KEY,
  auth_code VARCHAR(255),
  display_name VARCHAR(255),
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user_model(id)
);
