ALTER TABLE google_calendar
  DROP FOREIGN KEY google_calendar_ibfk_2,
  DROP COLUMN google_auth_email;

ALTER TABLE google_auth
DROP PRIMARY KEY;

ALTER TABLE google_auth
ADD id BIGINT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE google_calendar
ADD google_auth_id BIGINT;

ALTER TABLE google_calendar
ADD FOREIGN KEY (google_auth_id) REFERENCES google_auth(id);