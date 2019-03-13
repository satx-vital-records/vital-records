use vitalrecords_db;

INSERT INTO applications(id, city, comment_date_time, comments, contact_type, first_name, form_img, form_type, identification_img, last_name, mid_name, purpose, record_relationship, record_type, state, street, street2, zip, record_id, status_id, user_id)
VALUES (1, 'san antonio', null, null, 'phone', 'sarah', null, 'long', null, 'barron', 'e', 'funeral', 'daughter', 'death', 'tx', '2222 cavalier ave', null, '78210', 1, 1, 1 );

INSERT INTO mailing_address(id, city, first_name, last_name, state, street, street_2, zip, user_id)
VALUES (1, 'san antonio', 'sarah', 'barron', 'tx', '2222 cavalier', 'street', '78210', 1);

INSERT INTO records(id, birth_city, birth_county, date_of_birth, date_of_death, date_of_request, death_city, death_county, first_name, last_name, mid_name, parent1_first_name, parent1_last_name, parent1_mid_name, parent2_first_name, parent2_last_name, parent2_mid_name, sex, application_id)
VALUES (1, 'san antonio', 'bexar', '01/01/1959', '04/03/2019', current_date, 'edwards air force base', 'lincoln', 'Zeta', 'Reticulans', 'Grayson', 'Gray', 'Reticulans', null, 'Mara', 'Jade', null, 'male', 1);

INSERT INTO statuses(id, description)
VALUES (100, 'In Progress');

INSERT INTO users(id, email, first_name, last_name, password, phone_num, role, username)
VALUES (1, 'sarah.barron@email.com', 'sarah', 'barron', 'p@$$w0rd', '210-512-1234', 1, 'sarah_barron');



-- -- USE vitalrecords_db;
-- --
-- --
-- -- CREATE TABLE `mailing_address`
-- -- (
-- -- `id`         int unsigned NOT NULL AUTO_INCREMENT ,
-- -- `first_name` varchar(100) ,
-- -- `last_name`  varchar(100) ,
-- -- `street`     varchar(100) ,
-- -- `street_2`   varchar(100) ,
-- -- `city`       varchar(100) ,
-- -- `state`      varchar(100) ,
-- -- `zip`        varchar(100) ,
-- -- PRIMARY KEY (`id`)
-- -- );
-- --
-- -- CREATE TABLE `users`
-- -- (
-- -- `id`         int unsigned NOT NULL AUTO_INCREMENT ,
-- -- `first_name` varchar(100) NOT NULL ,
-- -- `last_name`  varchar(100)) NOT NULL ,
-- -- `email`      varchar(100) unique NOT NULL DEFAULT UNIQUE ,
-- -- `password`   varchar(255) NOT NULL ,
-- -- `role`       tinyint(1) unsigned NOT NULL ,
-- -- `username`   varchar(100) NOT NULL ,
-- -- `phone_num`  varchar(100) NOT NULL ,
-- -- `mailing_id` int unsigned NOT NULL ,
-- -- PRIMARY KEY (`id`),
-- -- KEY `fkIdx_117` (`mailing_id`),
-- -- CONSTRAINT `FK_117` FOREIGN KEY `fkIdx_117` (`mailing_id`) REFERENCES `mailing_address` (`id`)
-- -- );
-- --
-- --
-- -- CREATE TABLE `applications`
-- -- (
-- -- `id`                  int unsigned NOT NULL AUTO_INCREMENT ,
-- -- `first_name`          varchar(100) NOT NULL ,
-- -- `middle_name`         varchar(100) ,
-- -- `last_name`           varchar(100) NOT NULL ,
-- -- `street`              varchar(100) NOT NULL ,
-- -- `street_2`            varchar(100) ,
-- -- `city`                varchar(100) NOT NULL ,
-- -- `state`               varchar(100) NOT NULL ,
-- -- `zip`                 varchar(100) NOT NULL ,
-- -- `record_relationship` varchar(100) ,
-- -- `purpose`             varchar(100) ,
-- -- `type`                varchar(100) ,
-- -- `form_type`           varchar(100) ,
-- -- `contact_type`        varchar(100) ,
-- -- `identication_img`    varchar(100) ,
-- -- `form_img`            varchar(100) ,
-- -- `comments`            text ,
-- -- `comment_date`        datetime ,
-- -- `user_id`             int unsigned NOT NULL ,
-- -- `status_id`           int unsigned NOT NULL ,
-- -- PRIMARY KEY (`id`),
-- -- KEY `fkIdx_132` (`status_id`),
-- -- CONSTRAINT `FK_132` FOREIGN KEY `fkIdx_132` (`status_id`) REFERENCES `statuses` (`status_id`),
-- -- KEY `fkIdx_72` (`user_id`),
-- -- CONSTRAINT `FK_72` FOREIGN KEY `fkIdx_72` (`user_id`) REFERENCES `users` (`id`)
-- -- );
-- --
-- --
-- CREATE TABLE `records`
-- (
-- `id`                 int unsigned NOT NULL AUTO_INCREMENT ,
-- `first_name`         varchar(100) ,
-- `middle_name`        varchar(100) ,
-- `last_name`          varchar(100) ,
-- `date_of_birth`      date ,
-- `date_of_death`      date ,
-- `sex`                varchar(10) ,
-- `birth_city`         varchar(100) ,
-- `birth_county`       varchar(100) ,
-- `death_city`         varchar(100) ,
-- `death_county`       varchar(100) ,
-- `parent1_first_name` varchar(100) ,
-- `parent1_mid_name`   varchar(100) ,
-- `parent1_last_name`  varchar(100) ,
-- `parent2_first_name` varchar(100) ,
-- `parent2_mid_name`   varchar(100) ,
-- `parent2_last_name`  varchar(100) ,
-- `date_of_request`    datetime ,
-- `app_id`             int unsigned NOT NULL ,
-- PRIMARY KEY (`id`),
-- KEY `fkIdx_120` (`app_id`),
-- CONSTRAINT `FK_120` FOREIGN KEY `fkIdx_120` (`app_id`) REFERENCES `applications` (`id`)
-- );
-- --
-- --
-- -- CREATE TABLE `statuses`
-- -- (
-- -- `status_id`   int unsigned NOT NULL ,
-- -- `status_desc` varchar(100) NOT NULL ,
-- -- PRIMARY KEY (`status_id`)
-- -- );
-- >>>>>>> master
