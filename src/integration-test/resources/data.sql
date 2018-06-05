INSERT INTO kal_user (id, client_token, user_email) VALUES (1, 'token', 'email@email.com');

INSERT INTO kalendar (id, user, output_google_auth_id, name, google_calendar_id) VALUES (1, 1, 'email@email.com', 'Test Kalendar', 'testkalendar@gmail.com');

INSERT INTO google_auth (id, user_id, email, access_token) VALUES (1, 1, 'email@email.com', 'token');
