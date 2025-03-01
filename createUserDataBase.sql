create database user_service;
use user_service;

create table users  (
        user_id integer not null auto_increment,
        user_name varchar(500) not null,
        user_email varchar(500) not null,
        password varchar(50)  not null,
        create_time date,
        last_login_time date,
		constraint user_pk primary key(user_id)
);

create table users_details
 (
       user_details_id integer not null auto_increment,
       user_id integer not null,
       cell_no varchar(500)  not null,
       address varchar(500) not null,
        city varchar(50)  not null,
        state varchar(100)  not null,
        pin_code integer not null  not null,
        amount_in_wallet integer not null  not null,
		constraint user_details_id_pk primary key(user_details_id),
        constraint user_id_fk foreign key(user_id) references users(user_id)
 );


 CREATE TABLE authorities (
   authorities_id int NOT NULL AUTO_INCREMENT,
   user_id integer not null,
   name varchar(50) NOT NULL,
   constraint authorities_id_pk primary key(authorities_id),
    constraint user_id_fk1 foreign key(user_id) references users(user_id)
   );