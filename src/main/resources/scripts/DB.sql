create table engine
(
	id serial not null
		constraint engine_pk
			primary key,
	name varchar(20) not null
);

alter table engine owner to postgres;

create unique index engine_id_uindex
	on engine (id);



create table color
(
	id serial not null
		constraint carcolor_pk
			primary key,
	name varchar(20) not null
);

alter table color owner to postgres;

create unique index carcolor_id_uindex
	on color (id);



create table mark
(
	id serial not null
		constraint carmark_pk
			primary key,
	name varchar(30) not null
);

alter table mark owner to postgres;

create unique index carmark_id_uindex
	on mark (id);



create table car
(
	id serial not null
		constraint car_pk
			primary key,
	owner_id integer not null,
	mark_id integer not null,
	model_id integer not null,
	assembledate timestamp,
	engine_id integer not null,
	numberofseats integer not null,
	color_id integer not null,
	dayprice integer not null
);

alter table car owner to postgres;

create unique index car_id_uindex
	on car (id);



create table model
(
	id serial not null
		constraint carmodel_pk
			primary key,
	id_mark integer not null,
	name varchar(30) not null
);

alter table model owner to postgres;

create unique index carmodel_id_uindex
	on model (id);



create table if not exists person
(
	id serial not null
		constraint person_pk
			primary key,
	name varchar(50),
	login varchar(20),
	password varchar(64),
	isseller boolean,
	iscustomer boolean,
	role varchar(20)
);

create unique index if not exists person_id_uindex
	on person (id);

create index if not exists person_name_index
	on person (name);




create table "order"
(
	id serial not null,
	date timestamp,
	seller int,
	customer int,
	car int,
	begindate timestamp,
	enddate timestamp,
	price int
);

create index order_customer_index
	on "order" (customer);

create unique index order_id_uindex
	on "order" (id);

create index order_seller_index
	on "order" (seller);

alter table "order"
	add constraint order_pk
		primary key (id);














