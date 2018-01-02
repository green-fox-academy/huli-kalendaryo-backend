DROP TABLE IF EXISTS Auth_Model;

CREATE TABLE Auth_Model (
  email VARCHAR(255) NOT NULL ,
  authCode VARCHAR(255),
  displayName VARCHAR(255),
  PRIMARY KEY (email)
);

DROP TABLE IF EXISTS user_model;

CREATE TABLE user_model (
  id BIGINT NOT NULL,
  clientToken VARCHAR(255),
  userEmail VARCHAR(255),
  accessToken VARCHAR(255),
  PRIMARY KEY (id)
);

