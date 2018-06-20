DROP TABLE IF EXISTS merged_calendar;

CREATE TABLE merged_calendar (
    user_id BIGINT PRIMARY KEY,
    user_name VARCHAR(255),
    output_account VARCHAR(255),
    output_calendar_id VARCHAR(255)
);

ALTER TABLE auth_model
    ADD access_token VARCHAR (255),
    ADD refresh_token VARCHAR (255),
    ADD calendar_id VARCHAR (255);

DROP TABLE IF EXISTS calendar_id;

CREATE TABLE calendar_id (
    id VARCHAR (255) PRIMARY KEY,
    auth_model_email VARCHAR(255),
    output_calendar_id BIGINT,
    FOREIGN KEY (auth_model_email) REFERENCES auth_model(email),
    FOREIGN KEY (output_calendar_id) REFERENCES merged_calendar(user_id)
);

ALTER TABLE auth_model
    ADD FOREIGN KEY (calendar_id) REFERENCES calendar_id(id);
