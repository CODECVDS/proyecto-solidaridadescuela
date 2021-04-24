create table category(
	id serial primary key,
	name varchar(20) unique not null,
	description varchar(100) not null,
	creationDate timestamp not null,
	status boolean not null,
	modificationdate timestamp not null
);

create table need(
	id serial primary key,
	category int,
	name varchar(20) unique not null,
	description varchar(100) not null,
	urgency varchar(10) not null,
	creationDate timestamp not null,
	status varchar(10) not null,
	modificationdate timestamp not null
);

create table users(
	username varchar(20) primary key,
	fullname varchar(50) not null,
	status boolean not null,
	mail varchar(50) not null,
	userpassword varchar(70) not null,
	usertype integer not null
);

create table rol(
	id serial primary key,
	name varchar(20)
);


--Poblar

insert into rol (name) values ('Administrator');
insert into rol (name) values ('Student');
insert into rol (name) values ('Teacher');
insert into rol (name) values ('Graduate');
insert into rol (name) values ('Administrative');

--Delete
drop table category;
drop table need;
drop table users;
drop table rol;
