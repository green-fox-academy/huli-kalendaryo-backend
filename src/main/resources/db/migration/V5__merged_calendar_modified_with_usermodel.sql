ALTER TABLE merged_calendar
    CHANGE user_name user BIGINT,
    ADD FOREIGN KEY (user) REFERENCES user_model(id);

ALTER TABLE calendar_id
    ADD FOREIGN KEY (output_calendar_id) REFERENCES merged_calendar(user);