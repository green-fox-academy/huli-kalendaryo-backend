ALTER TABLE kalendar
    CHANGE output_calendar_id name VARCHAR(255),
    ADD google_calendar_id VARCHAR(255);

