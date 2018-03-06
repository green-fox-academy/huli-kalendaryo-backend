ALTER TABLE calendar_id
    DROP FOREIGN KEY calendar_id_ibfk_2;

ALTER TABLE calendar_id
    DROP COLUMN output_calendar_id;

ALTER TABLE merged_calendar
    DROP PRIMARY KEY;

ALTER TABLE merged_calendar
    CHANGE user_id id BIGINT AUTO_INCREMENT PRIMARY KEY;