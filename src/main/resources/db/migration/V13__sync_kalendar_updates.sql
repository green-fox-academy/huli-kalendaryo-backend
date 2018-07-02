ALTER TABLE kalendar
  ADD last_sync DATETIME;

ALTER TABLE google_calendar
  ADD visibility VARCHAR(255);
