ALTER TABLE google_calendar
DROP PRIMARY KEY;

ALTER TABLE google_calendar
CHANGE id google_calendar_id VARCHAR(255);

ALTER TABLE google_calendar
ADD id BIGINT AUTO_INCREMENT PRIMARY KEY;
