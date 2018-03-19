ALTER TABLE user_model
  RENAME TO kal_user;

ALTER TABLE auth_model
  RENAME TO google_auth,
  CHANGE calendar_id google_calendars VARCHAR(255);

ALTER TABLE event_response
  RENAME TO google_calendar_update;

ALTER TABLE merged_calendar
  RENAME TO kalendar;

ALTER TABLE calendar_id
  RENAME TO google_calendar,
  CHANGE auth_model_email google_auth_email VARCHAR(255),
  CHANGE merged_calendar_id kalendar_id BIGINT;