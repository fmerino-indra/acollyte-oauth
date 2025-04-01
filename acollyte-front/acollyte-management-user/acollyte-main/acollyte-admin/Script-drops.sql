ALTER TABLE mobile_number DROP CONSTRAINT mobile_person_fk;

ALTER TABLE service_person DROP CONSTRAINT service_person_service_fk;

ALTER TABLE substitution DROP CONSTRAINT substitution_person_fk;

ALTER TABLE service_person DROP CONSTRAINT service_person_main_fk;

ALTER TABLE substitution DROP CONSTRAINT substitution_service_person_fk;

ALTER TABLE email_account DROP CONSTRAINT email_person_fk;

ALTER TABLE service_person DROP CONSTRAINT service_person_reserve_fk;

ALTER TABLE service DROP CONSTRAINT service_type_fk;


ALTER TABLE email_account DROP CONSTRAINT email_account_pk;

ALTER TABLE person DROP CONSTRAINT person_pk;

ALTER TABLE service_person DROP CONSTRAINT service_person_pk;

ALTER TABLE substitution DROP CONSTRAINT substitution_pk;

ALTER TABLE service_type DROP CONSTRAINT service_type_pk;

ALTER TABLE service DROP CONSTRAINT service_pk;

ALTER TABLE mobile_number DROP CONSTRAINT mobile_number_pk;


DROP INDEX fki_service_person_fk;

DROP INDEX fki_service_person_reserve_fk;

DROP INDEX fki_absence_service;

DROP INDEX fki_service_person_main_fk;

DROP INDEX fki_service_type_fk;

DROP INDEX fki_absence_person;


--DROP INDEX service_person_pkey;

--DROP INDEX email_account_pkey;

--DROP INDEX person_pkey;

--DROP INDEX substitution_pkey;

--DROP INDEX mobile_number_pkey;

--DROP INDEX service_type_pkey;

--DROP INDEX service_pkey;



DROP TABLE substitution;

DROP TABLE email_account;

DROP TABLE service_person;

DROP TABLE mobile_number;

DROP TABLE exception;

DROP TABLE service;

DROP TABLE service_type;

DROP TABLE person;
