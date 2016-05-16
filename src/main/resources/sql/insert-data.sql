INSERT INTO user(user_id, username, email, pw) VALUES (1, 'admin','admin@email.com','$2a$10$QloPsESzsuHlCeYyEEf/KOPIkIKRtQdF5v6o7JiaaSFyjc2ZiPytm');

INSERT INTO expression(user_id, expression, result, pub_date) VALUES (1, '2+2', '4', TIMESTAMP '2016-05-15 02:00:22');
INSERT INTO expression(user_id, expression, result, pub_date) VALUES (2, '3+3', '6', TIMESTAMP '2016-05-15 02:00:22');
