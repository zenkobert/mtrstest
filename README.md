# Spring Boot + Thymeleaf Registration Page


## Tech Stack

* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Spring Boot 2.2.1](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Thymeleaf 3.0.11](https://www.thymeleaf.org/documentation.html)
* [Spring 5 MVC REST](https://spring.io/guides/gs/rest-service/)
* [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)
* [Lombok (Reduce Boiler Plate Code)](https://projectlombok.org/)
* [PostgreSQL 9.5](https://www.postgresql.org/)
* [Maven](https://maven.apache.org/)
* Unit Test :
  * [JUnit 4](https://junit.org/junit4/)
  * [Mockito 3](https://site.mockito.org/)

### Prerequisites

Install PostgreSQL then run below script in the SQL editor

```
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

```

### Configuration

Below is the default database related configuration in application.properties file.
Feel free to edit it according to your local database setting.

```
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/mtrstest
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.show-sql=true
```
