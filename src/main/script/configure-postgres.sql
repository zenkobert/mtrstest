CREATE DATABASE mtrstest;

CREATE TABLE public.tb_user (
	id bigint NOT NULL,
	mobile_number varchar(12) NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	gender varchar NULL,
	email varchar NOT NULL,
	date_of_birth date NULL
)
WITH (
	OIDS=FALSE
) ;
