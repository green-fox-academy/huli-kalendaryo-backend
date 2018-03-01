DROP TABLE IF EXISTS event_response;

CREATE TABLE event_response (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  channel_id VARCHAR(255),
  resource_id VARCHAR(255),
  resource_uri VARCHAR(255),
  resource_state VARCHAR(255),
  message_number INT,
  channel_expiration VARCHAR(255),
  channel_token VARCHAR(255)
);