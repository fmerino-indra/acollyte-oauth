CREATE TABLE substitution (
		id INT4 NOT NULL,
		service_person_id INT4,
		person_id INT4
	);

CREATE TABLE email_account (
		id INT4 NOT NULL,
		person_id INT4,
		email_account VARCHAR(50)
	);

CREATE TABLE service_person (
		id INT4 NOT NULL,
		service_id INT4 NOT NULL,
		person_id INT4 NOT NULL,
		reserve_id INT4,
		can_go BOOL,
		have_gone BOOL,
		has_gone BOOL
	);

CREATE TABLE mobile_number (
		id INT4 NOT NULL,
		person_id INT4,
		mobile_number BPCHAR(11)
	);

CREATE TABLE exception (
		id INT4,
		person_id INT4,
		day DATE
	);

CREATE TABLE service (
		id INT4 NOT NULL,
		service_name VARCHAR(50),
		service_date DATE,
		service_type INT4 NOT NULL,
		eve BOOL,
		fixed BOOL,
		needed INT4,
		n_reserves INT4
	);

CREATE TABLE service_type (
		id INT4 NOT NULL,
		service_type VARCHAR(10485760)
	);

CREATE TABLE person (
		id INT4 NOT NULL,
		name VARCHAR(10485760),
		comunidad INT4
	);

CREATE INDEX fki_service_person_fk ON service_person (service_id ASC);

CREATE INDEX fki_service_person_reserve_fk ON service_person (reserve_id ASC);

CREATE INDEX fki_service_person_main_fk ON service_person (person_id ASC);

CREATE INDEX fki_absence_service ON substitution (service_person_id ASC);

CREATE INDEX fki_service_type_fk ON service (service_type ASC);

CREATE INDEX fki_absence_person ON substitution (person_id ASC);


--CREATE UNIQUE INDEX service_person_pkey ON service_person (id ASC);

--CREATE UNIQUE INDEX email_account_pkey ON email_account (id ASC);

--CREATE UNIQUE INDEX person_pkey ON person (id ASC);

--CREATE UNIQUE INDEX substitution_pkey ON substitution (id ASC);

--CREATE UNIQUE INDEX mobile_number_pkey ON mobile_number (id ASC);

--CREATE UNIQUE INDEX service_type_pkey ON service_type (id ASC);

--CREATE UNIQUE INDEX service_pkey ON service (id ASC);

-- Primary keys
ALTER TABLE email_account ADD CONSTRAINT email_account_pk PRIMARY KEY (id);

ALTER TABLE person ADD CONSTRAINT person_pk PRIMARY KEY (id);

ALTER TABLE service_person ADD CONSTRAINT service_person_pk PRIMARY KEY (id);

ALTER TABLE substitution ADD CONSTRAINT substitution_pk PRIMARY KEY (id);

ALTER TABLE service_type ADD CONSTRAINT service_type_pk PRIMARY KEY (id);

ALTER TABLE service ADD CONSTRAINT service_pk PRIMARY KEY (id);

ALTER TABLE mobile_number ADD CONSTRAINT mobile_number_pk PRIMARY KEY (id);


--Foreign keys
ALTER TABLE mobile_number ADD CONSTRAINT mobile_person_fk FOREIGN KEY (person_id)
	REFERENCES person (id);

ALTER TABLE service_person ADD CONSTRAINT service_person_service_fk FOREIGN KEY (service_id)
	REFERENCES service (id);

ALTER TABLE substitution ADD CONSTRAINT substitution_person_fk FOREIGN KEY (person_id)
	REFERENCES person (id);

ALTER TABLE service_person ADD CONSTRAINT service_person_main_fk FOREIGN KEY (person_id)
	REFERENCES person (id);

ALTER TABLE substitution ADD CONSTRAINT substitution_service_person_fk FOREIGN KEY (service_person_id)
	REFERENCES service_person (id);

ALTER TABLE email_account ADD CONSTRAINT email_person_fk FOREIGN KEY (person_id)
	REFERENCES person (id);

ALTER TABLE service_person ADD CONSTRAINT service_person_reserve_fk FOREIGN KEY (reserve_id)
	REFERENCES person (id);

ALTER TABLE service ADD CONSTRAINT service_type_fk FOREIGN KEY (service_type)
	REFERENCES service_type (id);

