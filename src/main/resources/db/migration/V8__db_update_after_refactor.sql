ALTER TABLE user_model
    RENAME TO kal_user;

ALTER TABLE auth_model
    DROP FOREIGN KEY auth_model_ibfk_2,
    DROP COLUMN calendar_id,
    RENAME TO google_auth;

ALTER TABLE event_response
    RENAME TO google_calendar_update;

ALTER TABLE merged_calendar
    RENAME TO kalendar;

ALTER TABLE calendar_id
    DROP FOREIGN KEY calendar_id_ibfk_1,
    CHANGE auth_model_email google_auth_email VARCHAR(255),
    CHANGE merged_calendar_id kalendar_id BIGINT,
    ADD FOREIGN KEY (google_auth_email) REFERENCES google_auth(email),
    RENAME TO google_calendar;
