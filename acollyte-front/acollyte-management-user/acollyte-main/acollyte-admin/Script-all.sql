-- acollyte.absence_id_seq definition

-- DROP SEQUENCE acollyte.absence_id_seq;

CREATE SEQUENCE acollyte.absence_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- acollyte.celebration_id_seq definition

-- DROP SEQUENCE acollyte.celebration_id_seq;

CREATE SEQUENCE acollyte.celebration_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- acollyte.email_account_id_seq definition

-- DROP SEQUENCE acollyte.email_account_id_seq;

CREATE SEQUENCE acollyte.email_account_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- acollyte.mobile_number_id_seq definition

-- DROP SEQUENCE acollyte.mobile_number_id_seq;

CREATE SEQUENCE acollyte.mobile_number_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- acollyte.person_id_seq definition

-- DROP SEQUENCE acollyte.person_id_seq;

CREATE SEQUENCE acollyte.person_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- acollyte.raffle_id_seq definition

-- DROP SEQUENCE acollyte.raffle_id_seq;

CREATE SEQUENCE acollyte.raffle_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- acollyte.raffle_person_id_seq definition

-- DROP SEQUENCE acollyte.raffle_person_id_seq;

CREATE SEQUENCE acollyte.raffle_person_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- acollyte.service_id_seq definition

-- DROP SEQUENCE acollyte.service_id_seq;

CREATE SEQUENCE acollyte.service_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;


-- acollyte.service_person_id_seq definition

-- DROP SEQUENCE acollyte.service_person_id_seq;

CREATE SEQUENCE acollyte.service_person_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-------------------------------------------------------------------

-- acollyte.celebration definition

-- Drop table

-- DROP TABLE celebration;

CREATE TABLE celebration (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NULL,
	definite bool NULL,
	"date" date NULL,
	"day" int2 NULL,
	"month" int2 NULL,
	CONSTRAINT celebration_pkey PRIMARY KEY (id)
);


-- acollyte."exception" definition

-- Drop table

-- DROP TABLE "exception";

CREATE TABLE "exception" (
	id int4 NULL,
	person_id int4 NULL,
	"day" date NULL
);


-- acollyte.person definition

-- Drop table

-- DROP TABLE person;

CREATE TABLE person (
	"name" varchar(10485760) NULL,
	comunidad int4 NULL,
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	user_id varchar(50) NULL,
	CONSTRAINT person_pk PRIMARY KEY (id)
);


-- acollyte.service_type definition

-- Drop table

-- DROP TABLE service_type;

CREATE TABLE service_type (
	id int4 NOT NULL,
	service_type varchar(10485760) NULL,
	CONSTRAINT service_type_pk PRIMARY KEY (id)
);


-- acollyte.substitution definition

-- Drop table

-- DROP TABLE substitution;

CREATE TABLE substitution (
	id int4 NOT NULL,
	service_person_id int4 NULL,
	person_id int4 NULL
);


-- acollyte.absence definition

-- Drop table

-- DROP TABLE absence;

CREATE TABLE absence (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"day" date NULL,
	person_id int4 NULL,
	CONSTRAINT absence_pkey PRIMARY KEY (id),
	CONSTRAINT absence_person_fk FOREIGN KEY (person_id) REFERENCES person(id)
);


-- acollyte.email_account definition

-- Drop table

-- DROP TABLE email_account;

CREATE TABLE email_account (
	person_id int4 NULL,
	email_account varchar(50) NULL,
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	CONSTRAINT email_account_pkey PRIMARY KEY (id),
	CONSTRAINT email_person_fk FOREIGN KEY (person_id) REFERENCES person(id)
);


-- acollyte.mobile_number definition

-- Drop table

-- DROP TABLE mobile_number;

CREATE TABLE mobile_number (
	person_id int4 NULL,
	mobile_number bpchar(11) NULL,
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	CONSTRAINT mobile_number_pkey PRIMARY KEY (id),
	CONSTRAINT mobile_person_fk FOREIGN KEY (person_id) REFERENCES person(id)
);


-- acollyte.service definition

-- Drop table

-- DROP TABLE service;

CREATE TABLE service (
	service_name varchar(50) NULL,
	service_date timestamptz NULL,
	service_type int4 NOT NULL,
	eve bool NULL,
	fixed bool NULL,
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	needed int4 NULL,
	n_reserves int4 NULL,
	CONSTRAINT service_pk PRIMARY KEY (id),
	CONSTRAINT service_st_fk FOREIGN KEY (service_type) REFERENCES service_type(id)
);


-- acollyte.service_person definition

-- Drop table

-- DROP TABLE service_person;

CREATE TABLE service_person (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	service_id int4 NOT NULL,
	person_id int4 NOT NULL,
	reserve_id int4 NULL,
	can_go bool NULL,
	have_gone bool NULL,
	has_gone bool NULL,
	CONSTRAINT service_person_pkey PRIMARY KEY (id),
	CONSTRAINT sp_person_fk FOREIGN KEY (person_id) REFERENCES person(id),
	CONSTRAINT sp_reserve_fk FOREIGN KEY (reserve_id) REFERENCES person(id),
	CONSTRAINT sp_service_fk FOREIGN KEY (service_id) REFERENCES service(id)
);


-- acollyte.raffle definition

-- Drop table

-- DROP TABLE raffle;

CREATE TABLE raffle (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"date" timestamptz NOT NULL,
	raffle_date timestamp NOT NULL,
	"nCandidates" int4 NULL,
	"nReserves" int4 NULL,
	service_id int4 NULL,
	n_survived_candidates int4 NULL,
	n_survived_reserves int4 NULL,
	CONSTRAINT raffle_pk PRIMARY KEY (id),
	CONSTRAINT raffle_service_fk FOREIGN KEY (service_id) REFERENCES service(id)
);


-- acollyte.raffle_person definition

-- Drop table

-- DROP TABLE raffle_person;

CREATE TABLE raffle_person (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	selected_type int4 NOT NULL,
	can_go bool NULL,
	has_gone bool NULL,
	have_gone bool NULL,
	person_id int4 NOT NULL,
	raffle_id int4 NOT NULL,
	CONSTRAINT raffle_person_pkey PRIMARY KEY (id),
	CONSTRAINT rp_person_fk FOREIGN KEY (person_id) REFERENCES person(id),
	CONSTRAINT rp_raffle_fk FOREIGN KEY (raffle_id) REFERENCES raffle(id)
);

/*
CREATE UNIQUE INDEX service_pk ON acollyte.service USING btree (id);

CREATE UNIQUE INDEX raffle_person_pkey ON acollyte.raffle_person USING btree (id);

CREATE UNIQUE INDEX mobile_number_pkey ON acollyte.mobile_number USING btree (id);

CREATE UNIQUE INDEX raffle_pk ON acollyte.raffle USING btree (id);

CREATE UNIQUE INDEX email_account_pkey ON acollyte.email_account USING btree (id);

CREATE UNIQUE INDEX service_person_pkey ON acollyte.service_person USING btree (id);

CREATE UNIQUE INDEX person_pk ON acollyte.person USING btree (id);

CREATE UNIQUE INDEX service_type_pk ON acollyte.service_type USING btree (id);

CREATE UNIQUE INDEX celebration_pkey ON acollyte.celebration USING btree (id);

CREATE UNIQUE INDEX absence_pkey ON acollyte.absence USING btree (id);
