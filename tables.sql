create table category(
	id serial primary key,
	name varchar(50) unique not null,
	description varchar(500) not null,
	creationDate timestamp not null,
	status boolean not null,
	modificationdate timestamp not null
);

create table need(
	id serial primary key,
	category int,
	name varchar(50) unique not null,
	description varchar(500) not null,
	urgency varchar(10) not null,
	creationDate timestamp not null,
	status varchar(10) not null,
	modificationdate timestamp not null
);

create table rol(
	id serial primary key,
	name varchar(20)
);

create table users(
	username varchar(20) primary key,
	fullname varchar(50) not null,
	status boolean not null,
	mail varchar(50) not null,
	userpassword varchar(70) not null,
	usertype integer not null	
);

create table offer(
	id serial primary key,
	category int,
	name varchar(20) unique not null,
	description varchar(500) not null,	
	creationDate timestamp not null,
	status varchar(10) not null,
	modificationdate timestamp not null	
);


--foreign keys

alter table need
add constraint fk_category
	foreign key(category)
	references category(id);
	
alter table users
add constraint fk_usertype
	foreign key(usertype)
	references rol(id);
	
alter table offer
add constraint fk_category
	foreign key(category)
	references category(id);	

	
--Poblar

--Poblar rol
insert into rol (name) values ('Administrator');
insert into rol (name) values ('Student');
insert into rol (name) values ('Teacher');
insert into rol (name) values ('Graduate');
insert into rol (name) values ('Administrative');

--Poblar Usuario
insert into users (username,fullname,status,mail,userpassword,usertype) values ('user','Estudiante',true,'usuario@gmail.com','75cee5b221098c39dc19feca49b7b7cfe46405057d0361b18726990a5f91bf25',2);
insert into users (username,fullname,status,mail,userpassword,usertype) values ('admin','Administrador',true,'admin@gmail.com','a74a6393d8fba6e2178e4e36e32c73c5bef049290e03fdc3ada929eb03770138',1);

--Delete
drop table category;
drop table need;
drop table users;
drop table rol;
drop table offer;

