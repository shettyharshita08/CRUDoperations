//Create Schema
create schema operations;
use operations;

//Create table
create table user_information (id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY , name varchar(255) UNIQUE DEFAULT NULL , password varchar(255) DEFAULT NULL , email varchar(255) DEFAULT NULL , roles varchar(255) DEFAULT NULL);
create table cat_information (image_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY , filename varchar(255) DEFAULT NULL , type varchar(255) DEFAULT NULL , filepath varchar(255) DEFAULT NULL , user_id bigint , FOREIGN KEY(user_id) REFERENCES user_information(id));
