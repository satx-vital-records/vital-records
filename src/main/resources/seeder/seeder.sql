



CREATE TABLE `mailing_address`
(
`id`         int unsigned NOT NULL AUTO_INCREMENT ,
`first_name` varchar(100) ,
`last_name`  varchar(100) ,
`street`     varchar(100) ,
`street_2`   varchar(100) ,
`city`       varchar(100) ,
`state`      varchar(100) ,
`zip`        varchar(100) ,
PRIMARY KEY (`id`)
);

CREATE TABLE `users`
(
`id`         int unsigned NOT NULL AUTO_INCREMENT ,
`first_name` varchar(100) NOT NULL ,
`last_name`  varchar(100)) NOT NULL ,
`email`      varchar(100) unique NOT NULL DEFAULT UNIQUE ,
`password`   varchar(255) NOT NULL ,
`role`       tinyint(1) unsigned NOT NULL ,
`username`   varchar(100) NOT NULL ,
`phone_num`  varchar(100) NOT NULL ,
`mailing_id` int unsigned NOT NULL ,
PRIMARY KEY (`id`),
KEY `fkIdx_117` (`mailing_id`),
CONSTRAINT `FK_117` FOREIGN KEY `fkIdx_117` (`mailing_id`) REFERENCES `mailing_address` (`id`)
);


CREATE TABLE `applications`
(
`id`                  int unsigned NOT NULL AUTO_INCREMENT ,
`first_name`          varchar(100) NOT NULL ,
`middle_name`         varchar(100) ,
`last_name`           varchar(100) NOT NULL ,
`street`              varchar(100) NOT NULL ,
`street_2`            varchar(100) ,
`city`                varchar(100) NOT NULL ,
`state`               varchar(100) NOT NULL ,
`zip`                 varchar(100) NOT NULL ,
`record_relationship` varchar(100) ,
`purpose`             varchar(100) ,
`type`                varchar(100) ,
`form_type`           varchar(100) ,
`contact_type`        varchar(100) ,
`identication_img`    varchar(100) ,
`form_img`            varchar(100) ,
`comments`            text ,
`comment_date`        datetime ,
`user_id`             int unsigned NOT NULL ,
`status_id`           int unsigned NOT NULL ,
PRIMARY KEY (`id`),
KEY `fkIdx_132` (`status_id`),
CONSTRAINT `FK_132` FOREIGN KEY `fkIdx_132` (`status_id`) REFERENCES `statuses` (`status_id`),
KEY `fkIdx_72` (`user_id`),
CONSTRAINT `FK_72` FOREIGN KEY `fkIdx_72` (`user_id`) REFERENCES `users` (`id`)
);


CREATE TABLE `records`
(
`id`                 int unsigned NOT NULL AUTO_INCREMENT ,
`first_name`         varchar(100) ,
`middle_name`        varchar(100) ,
`last_name`          varchar(100) ,
`date_of_birth`      date ,
`date_of_death`      date ,
`sex`                varchar(10) ,
`birth_city`         varchar(100) ,
`birth_county`       varchar(100) ,
`death_city`         varchar(100) ,
`death_county`       varchar(100) ,
`parent1_first_name` varchar(100) ,
`parent1_mid_name`   varchar(100) ,
`parent1_last_name`  varchar(100) ,
`parent2_first_name` varchar(100) ,
`parent2_mid_name`   varchar(100) ,
`parent2_last_name`  varchar(100) ,
`date_of_request`    datetime ,
`app_id`             int unsigned NOT NULL ,
PRIMARY KEY (`id`),
KEY `fkIdx_120` (`app_id`),
CONSTRAINT `FK_120` FOREIGN KEY `fkIdx_120` (`app_id`) REFERENCES `applications` (`id`)
);


CREATE TABLE `statuses`
(
`status_id`   int unsigned NOT NULL ,
`status_desc` varchar(100) NOT NULL ,
PRIMARY KEY (`status_id`)
);