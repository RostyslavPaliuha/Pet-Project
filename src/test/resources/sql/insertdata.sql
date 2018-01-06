SET foreign_key_checks = 0;
INSERT INTO social.account (email, password)
VALUES ('pro@gmail.com', '$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni');
INSERT INTO social.profile (account_id, online_status) VALUES (1, 0);
INSERT INTO social.profile_details (first_name, last_name, sex, age, profile_id)
VALUES ('Rostyslav', 'Paliuha', 'male', 25, 1);
INSERT INTO social.account (email, password)
VALUES ('ura@gmail.com', '$2a$04$8exKZMIRO8IfE/t8rZR10eJr88mM9y6gjQIIQ66PPP/i6SSF96Mni');
INSERT INTO social.profile (account_id, online_status) VALUES (2, 0);
INSERT INTO social.profile_details (first_name, last_name, sex, age, profile_id)
VALUES ('Ura', 'Atamanchyk', 'male', 25, 2);
INSERT INTO social.friends_list (profile_id, friend_profile_id) VALUES (1, 2);
