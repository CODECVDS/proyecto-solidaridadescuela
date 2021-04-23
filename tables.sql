create table if not exists category(
	id serial primary key,
	name varchar(20) unique not null,
	description varchar(100) not null,
	creationDate timestamp not null,
	status boolean not null,
	modificationdate timestamp not null
);

create table if not exists need(
	id serial primary key,
	category int,
	name varchar(20) unique not null,
	description varchar(100) not null,
	urgency varchar(10) not null,
	creationDate timestamp not null,
	status varchar(10) not null,
	modificationdate timestamp not null
);

create table if not exists users(
	username varchar(20) primary key,
	fullname varchar(50) not null,
	status boolean not null,
	mail varchar(30) not null,
	userpassword varchar(20) not null,
	usertype integer not null
);

create table if not exists rol(
	id serial primary key,
	name varchar(20)
);