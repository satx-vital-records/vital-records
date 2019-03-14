
DROP DATABASE IF EXISTS vitalrecords_db;
CREATE DATABASE IF NOT EXISTS vitalrecords_db;

USE vitalrecords_db;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
id         int unsigned NOT NULL AUTO_INCREMENT ,
first_name varchar(100) NOT NULL ,
last_name  varchar(100) NOT NULL ,
email      varchar(100) NOT NULL ,
password   varchar(255) NOT NULL ,
role       tinyint(1) unsigned NOT NULL ,
username   varchar(100) NOT NULL ,
phone_num  varchar(100) NOT NULL ,
PRIMARY KEY (id),
  UNIQUE (email)
);

CREATE TABLE mailing_address
(
id         int unsigned NOT NULL AUTO_INCREMENT ,
first_name varchar(100) ,
last_name  varchar(100) ,
street     varchar(100) ,
street_2   varchar(100) ,
city       varchar(100) ,
state      varchar(100) ,
zip        varchar(100),
user_id    INT UNSIGNED NOT NULL,
PRIMARY KEY (id),
  CONSTRAINT FK_userMailingAddress FOREIGN KEY(user_id)
  REFERENCES users(id)
);


DROP TABLE IF EXISTS applications;
CREATE TABLE IF NOT EXISTS applications
(
id int unsigned NOT NULL AUTO_INCREMENT ,
first_name          varchar(100) NOT NULL ,
mid_name         varchar(100) ,
last_name           varchar(100) NOT NULL ,
street              varchar(100) NOT NULL ,
street_2            varchar(100) ,
city                varchar(100) NOT NULL ,
state               varchar(100) NOT NULL ,
zip                 varchar(100) NOT NULL ,
record_relationship varchar(100) ,
purpose             varchar(100) ,
record_type                varchar(100) ,
form_type           varchar(100) ,
contact_type        varchar(100) ,
identication_img    varchar(100) ,
form_img            varchar(100) ,
comments            text ,
comment_date        datetime ,
user_id             int unsigned NOT NULL ,
status_id           int unsigned NOT NULL ,
PRIMARY KEY (id),
  CONSTRAINT FK_userApplication FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT FK_applicationStatus FOREIGN KEY (status_id) REFERENCES statuses(id)
);

ALTER TABLE applications
    ADD FOREIGN KEY (user_id) REFERENCES users(id),
    ADD FOREIGN KEY (status_id) REFERENCES statuses(id);

CREATE TABLE records
(
id                 int unsigned NOT NULL AUTO_INCREMENT ,
first_name         varchar(100) NOT NULL,
mid_name        varchar(100) ,
last_name          varchar(100) NOT NULL,
date_of_birth      date ,
date_of_death      date ,
sex                varchar(10) ,
birth_city         varchar(100) ,
birth_county       varchar(100) ,
death_city         varchar(100) ,
death_county       varchar(100) ,
parent1_first_name varchar(100) ,
parent1_mid_name   varchar(100) ,
parent1_last_name  varchar(100) ,
parent2_first_name varchar(100) ,
parent2_mid_name   varchar(100) ,
parent2_last_name  varchar(100) ,
date_of_request    datetime ,
app_id             int unsigned NOT NULL ,
PRIMARY KEY (id),
CONSTRAINT FK_recordForApplication FOREIGN KEY (app_id) REFERENCES applications(id)
);

CREATE TABLE statuses
(
status_id   int unsigned NOT NULL ,
status_desc varchar(100) NOT NULL ,
PRIMARY KEY (status_id)
);

# use vitalrecords_db;
#
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
