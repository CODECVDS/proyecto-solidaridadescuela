/*
--Create Tables
create table if not exists category(
	id serial primary key,
	name varchar(50) unique not null,
	description varchar(500) not null,
	creationDate timestamp not null,
	status boolean not null,
	modificationdate timestamp not null,
	isvalid boolean not null,
	descriptinvalid varchar(500)
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
	name varchar(50) unique not null,
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
	name varchar(50) not null,
	creationDate timestamp not null,
	coments varchar(500) not null,
	answerto varchar(5) not null,
	toId int not null
	
);

--Foreign keys

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
insert into users (username,fullname,status,mail,userpassword,usertype) values ('admin','Administrador',true,'admin@gmail.com','a74a6393d8fba6e2178e4e36e32c73c5bef049290e03fdc3ada929eb03770138',1);
insert into users (username,fullname,status,mail,userpassword,usertype) values ('user','Estudiante',true,'usuario@gmail.com','75cee5b221098c39dc19feca49b7b7cfe46405057d0361b18726990a5f91bf25',2);
insert into users (username,fullname,status,mail,userpassword,usertype) values ('teach','Teacher',true,'teach@gmail.com','1057a9604e04b274da5a4de0c8f4b4868d9b230989f8c8c6a28221143cc5a755',3);
insert into users (username,fullname,status,mail,userpassword,usertype) values ('gradu','Graduate',true,'gradu@gmail.com','9ec0375ea56387e5c8f07dda07b90ab1e8b7ab762ad992e8b9c674fbfc5993f4',4);
insert into users (username,fullname,status,mail,userpassword,usertype) values ('adminis','Administrative',true,'adminis@gmail.com','51f6f029928f3d0d10b54fa819ffae296f32868d82047204008c1a11e3e7d478',5);

--Poblar nmax
insert into parameters (nconfigneed,nconfigoffer) values (5,5);

--Poblar category
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Materiales','Elementos usados en Ing. Civil',CURRENT_TIMESTAMP,true,CURRENT_TIMESTAMP,true,'');
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Libros','Aplicados a la econom�a',CURRENT_TIMESTAMP,true,CURRENT_TIMESTAMP,true,'');
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Deportes','Elementos deportivos, balones, pelotas, redes, equipamento, etc',CURRENT_TIMESTAMP,true,CURRENT_TIMESTAMP,true,'');
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Dinero','Solicitar u ofertar en la plataforma',CURRENT_TIMESTAMP,true,CURRENT_TIMESTAMP,false,'Seg�n pol�ticas esta no es una categor�a v�lida');
insert into category (name,description,creationdate,status,modificationdate,isvalid,descriptinvalid) values ('Sistemas y computadores','Elementos computacionales',CURRENT_TIMESTAMP,true,CURRENT_TIMESTAMP,true,'');

--Poblar offer
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (5,'RAM 8gb','Memoria RAM HyperX',CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'user');
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (2,'Microeconom�a1','Autor:Ferguson',CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'user');
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (5,'Teclado','Mini Teclado Inalambrico Bluetooth Mac, iPad, Pc, Celular',CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'teach');
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (3,'Disco','Disco Ultimate Frisbee Discraft Ultra Star 175 Gr Blanco',CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'teach');
insert into offer (category,name,description,creationdate,status,modificationdate,username) values (2,'L�gica','Autor: Wilmer Garz�n',CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'teach');

--Poblar necesidad
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (1,'materiales','Se necesita materiales para EG1',3,CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'user');
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (5,'mantenimiento','Se necesita hacer mantenimiento en los equipos del b0',5,CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'user');
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (5,'Dispositivos m�viles','Requerido para la materia de ARSW',4,CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'user');
insert into need (category,name,description,urgency,creationdate,status,modificationdate,username) values (2,'Ciencia ficci�n','Requerido para la materia CLYS',2,CURRENT_TIMESTAMP,'Active',CURRENT_TIMESTAMP,'user');


--Poblar answer
insert into answer (name,creationdate,coments,answerto,toid) values ('Solicitud de compra','2021-05-20 19:49:12','Confirmar lo m�s pronto posible por favor','Offer',1);
insert into answer (name,creationdate,coments,answerto,toid) values ('Oferta de servicio','2021-05-20 19:49:12','Yo trabajo en el �rea de mantenimiento de servidores y salas de sistemas','Need',2);
insert into answer (name,creationdate,coments,answerto,toid) values ('Solicitud de compra','2021-05-20 19:49:12','Si tiene m�s libros del mismo tema los puedo comprar','Offer',5);
insert into answer (name,creationdate,coments,answerto,toid) values ('Oferta de materiales','2021-05-20 19:49:12','Te ofrezco los materiales que us� cuando vi esa materia','Need',1);
insert into answer (name,creationdate,coments,answerto,toid) values ('Solicitud de compra','2021-05-20 19:49:12','Si tienes m�s discos los puedo comprar','Offer',4);

--Procedures
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
		
	end

$$;

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

	end

$$;

--Delete Tables
drop table need;
drop table users;
drop table rol;
drop table offer;
drop table answer;
drop table category;
drop table parameters;

--Delete Constraints
alter table need drop constraint fk_need_category;
alter table users drop constraint fk_usertype;
alter table offer drop constraint fk_offer_category;
alter table offer drop constraint fk_offer_user;
alter table need drop constraint fk_need_user;
*/