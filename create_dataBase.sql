//create a database for the application..
create database order_service;
// change the database schema
use  order_service;

//create a table for orders
create table orders
 (
    order_id integer not null auto_increment,
    customer_id integer not null,
    customer_email  varchar(500) not null,
	order_status varchar(50) not null,
	transaction_date date,
	constraint orders_pk primary key(order_id)
);

// create table items
create table items
 (
    item_id integer not null auto_increment,
    order_id integer not null,
	item_code varchar(50) not null,
	item_quantity varchar(50) not null,
	item_price varchar(50) not null,
	constraint items_pk primary key(item_id),
    constraint items_fk1 foreign key (order_id) references orders(order_id)
);

query to select & check tables
