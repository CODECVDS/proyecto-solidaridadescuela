/*
create table if not exists category(
	id serial primary key,
	name varchar(50) unique not null,
	description varchar(500) not null,
	creationDate timestamp not null,
	status boolean not null,
	modificationdate timestamp not null,
	isvalid boolean not null,
	descriptinvalid varchar(500) not null
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

create table if not exists answer(
	id serial primary key,
	name varchar(20) not null,
	creationDate timestamp not null,
	coments varchar(500) not null,
	answerto varchar(5) not null,
	toId int not null
	
);




--foreign keys

alter table need
add constraint fk_need_category
	foreign key(category)
	references category(id) on delete cascade;
	
alter table users
add constraint fk_usertype
	foreign key(usertype)
	references rol(id) on delete cascade;
	
alter table offer
add constraint fk_offer_category
	foreign key(category)
	references category(id) on delete cascade;

alter table offer
add constraint fk_offer_user
	foreign key(username)
	references users(username) on delete cascade;

alter table need 
add constraint fk_need_user
	foreign key(username)
	references users(username) on delete cascade;

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
drop table need;
drop table users;
drop table rol;
drop table offer;
drop table nmax;
drop table answer;
drop table category;

--Poblar offer
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (5,'RAM 8gb','Memoria RAM HyperX','2021/05/17','Active','2021/05/17','user');
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (2,'Microeconomía1','Autor:Ferguson','2021/05/17','Active','2021/05/17','user');
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (5,'Teclado','Mini Teclado Inalambrico Bluetooth Mac, iPad, Pc, Celular','2021/05/19','Active','2021/05/19','teach');
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (3,'Disco','Disco Ultimate Frisbee Discraft Ultra Star 175 Gr Blanco','2021/05/19','Active','2021/05/19','teach');
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (2,'Lógica','Autor: Wilmer Garzón','2021/05/19','Active','2021/05/19','teach');

--Poblar necesidad
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (1,'materiales','Se necesita materiales para EG1',3,'2021/05/17','Active','2021/05/17','user');
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (5,'mantenimiento','Se necesita hacer mantenimiento en los equipos del b0',5,'2021/05/17','Active','2021/05/17','user');
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (5,'Dispositivos móviles','Requerido para la materia de ARSW',4,'2021/05/19','Active','2021/05/19','user');
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (2,'Ciencia ficción','Requerido para la materia CLYS',2,'2021/05/19','Active','2021/05/19','user');

--Poblar category
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Materiales','Elementos usados en Ing. Civil','2021/05/17',true,'2021/05/17',true,'');
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Libros','Aplicados a la economía','2021/05/17',true,'2021/05/17',true,'');
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Deportes','Elementos deportivos, balones, pelotas, redes, equipamento, etc','2021/05/17',true,'2021/05/17',true,'');
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Dinero','Solicitar u ofertar en la plataforma','2021/05/17',true,'2021/05/17',false,'Según políticas esta no es una categoría válida');
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Sistemas y computadores','Elementos computacionales','2021/05/17',true,'2021/05/17',true,'');

create or replace procedure confirm_noffers(cat int, n varchar, des varchar, usname varchar)
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
        	VALUES (cat,n,des,CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,usname);			
		
		elsif (noffers <= ncount) then
			raise exception 'numero maximo de ofertas registradas';
		
		end if;
		
	end;

$$


create or replace procedure confirm_nneeds(cat int, n varchar, des varchar,urg int, usname varchar)
language plpgsql
as $$

	declare
		nneeds	integer;
		ncount	integer;	
	
	begin
		--nneeds := 
		select nconfigneed into nneeds from parameters;
		--ncount := 
		select count(*) into ncount from need where username = usname;
				
	
		if (nneeds > ncount) then
			INSERT INTO need (category,name,description,urgency,creationdate,status,modificationdate,username)
        	VALUES (cat,n,des,urg,CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,usname);			
		
		elsif (nneeds <= ncount) then
			raise exception 'numero maximo de necesidades registradas';
		
		end if;
		
	end;

$$
*/

