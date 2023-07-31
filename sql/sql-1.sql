/*
데이터베이스(스키마): myapp
MYSQL

*/
use myapp;
-- 테이블: 데이터를 저장할 수 있는 기본적인 공간
-- create table 테이블(
create table contact (
email varchar(255) not null,
 image varchar(255),
 name varchar(255),
 phone varchar(255),
 primary key (email)
 ) engine=InnoDB;
