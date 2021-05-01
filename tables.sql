/*
create table if not exists category(
	id serial primary key,
	name varchar(50) unique not null,
	description varchar(500) not null,
	creationDate timestamp not null,
	status boolean not null,
	modificationdate timestamp not null
);

create table if not exists need(
	id serial primary key,
	category int,
	name varchar(50) unique not null,
	description varchar(500) not null,
	urgency varchar(10) not null,
	creationDate timestamp not null,
	status varchar(10) not null,
	modificationdate timestamp not null,
	username varchar(20) not null
);

create table if not exists rol(
	id serial primary key,
	name varchar(20)
);

create table if not exists users(
	username varchar(20) primary key,
	fullname varchar(50) not null,
	status boolean not null,
	mail varchar(50) not null,
	userpassword varchar(70) not null,
	usertype integer not null
);

create table if not exists offer(
	id serial primary key,
	category int,
	name varchar(20) unique not null,
	description varchar(500) not null,
	creationDate timestamp not null,
	status varchar(10) not null,
	modificationdate timestamp not null,
	username varchar(20) not null
);


create table if not exists parameters(
	id serial primary key,
	nconfigneed int not null,
	nconfigoffer int not null
);

create table if not exists Answer(
	id serial primary key,
	name varchar(20) not null,
	creationDate timestamp not null,
	coments varchar(500) not null,
	offer int not null,
	need int not null
	
);



--foreign keys

alter table need
add constraint fk_need_category
	foreign key(category)
	references category(id);
	
alter table users
add constraint fk_usertype
	foreign key(usertype)
	references rol(id);
	
alter table offer
add constraint fk_offer_category
	foreign key(category)
	references category(id);

alter table offer
add constraint fk_offer_user
	foreign key(username)
	references users(username);

alter table need 
add constraint fk_need_user
	foreign key(username)
	references users(username);

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

--Poblar nmax
insert into parameters (nconfigneed,nconfigoffer) values (5,5)

--Delete
drop table category;
drop table need;
drop table users;
drop table rol;
drop table offer;
drop table nmax;

--Poblar offer

--Poblar necesidad
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (1,'materiales','Se necesita materiales para EG1',3,'2021/04/24','Active','2021/04/24','user');
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (1,'mantenimiento','Se hacer mantenimiento en los equipos del b0',5,'2021/04/24','Active','2021/04/24','user');


create or replace procedure confirm_noffers(cat int, n varchar, des varchar, st varchar, usname varchar)
language plpgsql
as $$

	declare
		noffers	integer;
		ncount	integer;	
	
	begin
		--noffers := 
		select nconfigoffer into noffers from parameters;
		--ncount := 
		select count(*) into ncount from offer where username = 'admin';
				
	
		if (noffers > ncount) then
			INSERT INTO offer (category,name,description,creationdate,status,modificationdate,username)
        	VALUES (cat,n,des,CURRENT_TIMESTAMP,st,CURRENT_TIMESTAMP,usname);			
		
		elsif (noffers <= ncount) then
			raise exception 'numero maximo de offertas registradas';
		
		end if;
		
	end;

$$



create or replace procedure confirm_nneeds(cat int, n varchar, des varchar, st varchar, usname varchar)
language plpgsql
as $$

	declare
		nneeds	integer;
		ncount	integer;	
	
	begin
		--nneeds := 
		select nconfigneed into nneeds from parameters;
		--ncount := 
		select count(*) into ncount from need where username = 'admin';
				
	
		if (nneeds > ncount) then
			INSERT INTO need (category,name,description,creationdate,status,modificationdate,username)
        	VALUES (cat,n,des,CURRENT_TIMESTAMP,st,CURRENT_TIMESTAMP,usname);			
		
		elsif (nneeds <= ncount) then
			raise exception 'numero maximo de necesidades registradas';
		
		end if;
		
	end;

$$

*/
