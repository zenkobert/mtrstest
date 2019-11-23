CREATE DATABASE mtrstest;

CREATE TABLE public.tb_user (
	id serial PRIMARY KEY,
	mobile_number varchar(13) UNIQUE NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	gender varchar(10) NULL,
	email varchar(355) UNIQUE NOT NULL,
	date_of_birth date NULL
)
WITH (
	OIDS=FALSE
) ;
