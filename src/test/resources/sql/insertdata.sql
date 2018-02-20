INSERT INTO social.account (email, password, activate)
VALUES ('pro@gmail.com', '$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni', 1);
INSERT INTO social.profile (account_id, online_status) VALUES (1, 0);
INSERT INTO social.profile_details (first_name, last_name, sex, birthday, profile_id, country)
VALUES ('Rostyslav', 'Paliuha', 'male', '1992-03-16', 1, 'IF');

INSERT INTO social.account (email, PASSWORD, activate)
VALUES ('ura@gmail.com', '$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni', 1);
INSERT INTO social.profile (account_id, online_status) VALUES (2, 0);
INSERT INTO social.profile_details (first_name, last_name, sex, birthday, profile_id, country)
VALUES ('Ura', 'Atamanchyk', 'male', '1992-10-29', 2, 'IF');
INSERT INTO social.friends_list (profile_id, friend_profile_id) VALUES (1, 2);
INSERT INTO conversation (companion_id, profile_id) VALUES (1, 2);
INSERT INTO message (message_content, message_date, conversation_id) VALUES ('Hi dude!', '2018-01-12 20:16:52', 1);

INSERT INTO social.account (email, password, activate)
VALUES ('andriy@gmail.com', '$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni', 1);
INSERT INTO social.profile (account_id, online_status) VALUES (3, 0);
INSERT INTO social.profile_details (first_name, last_name, sex, birthday, profile_id, country)
VALUES ('Andriy', 'Melnik', 'male', '1992-11-15', 3, 'IF');