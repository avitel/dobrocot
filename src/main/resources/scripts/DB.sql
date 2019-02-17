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
	color_id integer not null
);

alter table car owner to postgres;

create unique index car_id_uindex
	on car (id);



create table class
(
	id serial not null
		constraint carclass_pk
			primary key,
	name varchar(10) not null
);

alter table class owner to postgres;

create unique index carclass_id_uindex
	on class (id);



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



create table person
(
	id serial not null
	constraint person_pk
		primary key,
	name varchar(50),
	login varchar(20),
	password varchar(30),
	isseller boolean,
	iscustomer boolean
);

alter table person owner to postgres;

create unique index person_id_uindex
	on person (id);

create index person_name_index
	on person (name);



create table place
(
	id serial not null
		constraint place_pk
			primary key,
	description varchar(50)
);

alter table place owner to postgres;

create index place_description_index
	on place (description);

create unique index place_id_uindex
	on place (id);



create table offer
(
	id serial not null,
	date timestamp,
	seller int,
	car int,
	dayprice int,
	place int
);

create index offer_car_index
	on offer (car);

create index offer_dayprice_index
	on offer (dayprice);

create unique index offer_id_uindex
	on offer (id);

create index offer_place_index
	on offer (place);

create index offer_seller_index
	on offer (seller);

alter table offer
	add constraint offer_pk
		primary key (id);



create table "order"
(
	id serial not null,
	date timestamp,
	seller int,
	customer int,
	offer int,
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



create table incomingPayment
(
	id serial not null,
	date timestamp,
	"order" int,
	seller int,
	amount int
);

create unique index incomingPayment_id_uindex
	on incomingPayment (id);

create index incomingPayment_seller_index
	on incomingPayment (seller);

alter table incomingPayment
	add constraint incomingPayment_pk
		primary key (id);



create table outgoingPayment
(
	id serial not null,
	date timestamp,
	"order" int,
	seller int,
	amount int
);

create unique index outgoingPayment_id_uindex
	on outgoingPayment (id);

create index outgoingPayment_seller_index
	on outgoingPayment (seller);

alter table outgoingPayment
	add constraint outgoingPayment_pk
		primary key (id);












