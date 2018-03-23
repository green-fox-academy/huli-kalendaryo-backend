ALTER TABLE merged_calendar
    CHANGE user_name user BIGINT,
    ADD FOREIGN KEY (user) REFERENCES user_model(id);
