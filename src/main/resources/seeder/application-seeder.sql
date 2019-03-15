USE vitalrecords_db;

INSERT INTO applications(id, first_name, mid_name, last_name, street, street_2, city, state, zip,record_relationship, purpose, record_type, form_type,contact_type, identication_img, form_img, comments,comment_date, user_id, status_id)
VALUES (1, 'sarah', null, 'barron', '2222 cavalier ave', null, 'san antonio', 'tx', '78210', 'daughter','funeral', 'death', 'long', 'phone', null, null, 'e',current_date, 1, 100);

INSERT INTO mailing_address(id, city, first_name, last_name, state, street, street_2, zip, user_id)
VALUES (1, 'san antonio', 'sarah', 'barron', 'tx', '2222 cavalier', 'street', '78210', 1);

INSERT INTO records(id, birth_city, birth_county, date_of_birth, date_of_death, date_of_request, death_city, death_county, first_name, last_name, mid_name, parent1_first_name, parent1_last_name, parent1_mid_name, parent2_first_name, parent2_last_name, parent2_mid_name, sex, app_id)
VALUES (1, 'san antonio', 'bexar', '1959-01-01', '2019-04-03', current_date, 'Edwards Air Force Base', 'Lincoln', 'Zeta', 'Reticulans', 'Grayson', 'Gray', 'Reticulans', null, 'Mara', 'Jade', null, 'male', 1);
#
INSERT INTO statuses(id, description)
VALUES (100, 'In Progress');
#
INSERT INTO users(id, email, first_name, last_name, password, phone_num, role, username)
VALUES (1, 'sarah.barron@email.com', 'sarah', 'barron', 'p@$$w0rd', '210-512-1234', 1, 'sarah_barron');
