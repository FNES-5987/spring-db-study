/*
데이터베이스(스키마): myapp
MYSQL

*/
use myapp;
/* 테이블: 데이터를 저장할 수 있는 기본적인 공간
 create table 테이블(
 컬럼명 데이터타입 제약조건,
 ...,
 추가적인 제약조건(constraint)
)
*/
/* 
	DDL(Data Definion language)
	create ...
    SQL(Structured Query Language) : 시퀄
*/
create table contact (
-- varchar: variable charactor
-- 가변문자열, 255byte까지 넣을 수 있음.
email varchar(255) not null,
 image varchar(255),
 name varchar(255) not null,
 phone varchar(255) not null,
 primary key (email)
 ) engine=InnoDB;
 
 select length('홍'); -- 한글은 3byte
 select length('a'); -- 알파벳은 1byte

-- insert into 테이블 value(값 목록 ...);
-- 값 목록은 순서를 잘 맞춰야함.
insert into contact
value("hong@gmail.com", null, "홍길동", "010-0000-0000");
-- 1row(s) affectd: 1 행이 영향을 받았다.
-- 데이터 1건을 row(행) 또는 record(레코드)
-- 데이터 대한 속성을 column(열) 또는 field(필드)

-- 데이터 값 순서를 설정
-- insert into 테이블(컬럼명 목록) values (값 목록) 
insert into contact (name, phone, email, image)
value("고길동", "010-1234-5678", "swordmaster@gmail.com", null);

/* 
기존에 있는 Id로 insert시 에러가 발생함.
1. primary key 제약조건: 테이블내의 데이터 중 같은 값이 중복이 있으면 안됨.
2. null값이 될 수 있음.
*/
-- insert into contact (name, phone, email, image)
-- value("고길동", "010-1234-5678", "swordmaster@gmail.com", null);

-- 목록 조회
select * from contact;

-- 특정 컬럼으로 정렬하여 조회
-- asc(기본값): 순정렬, desc: 역정렬
select * from contact order by name asc;

-- 데이터베이스의 PK(index, clustered)
-- clustered index에 맞게 데이터가 정렬되어있음.
-- index(binaray tree)구조이고, 데이터(linked list) 구조이다.
-- index(목차) 트리를 탐색하여 데이터까지 찾아감

-- PK값으로 1건만 찾아옴.
-- where 조건식
-- where 컬럼명 = '컬럼값'
select * from contact where email = 'hong@gmail.com';
select * from contact;

-- 조건에 맞는 레코드 삭제
-- where 절의 조건식에는 PK컬럼 기준으로 나오는게 좋음.
-- 실수로 불필요한 레코드가ㅏ 지워지는 것을 방지할 수 있다.
delete from contact where email='hong@gmail.com';

-- 테이블 데이터 전체 삭제
-- 테이블 구조를 재생성(DDL)
-- DML(Data(데이터) Mainpulation(조작) Language(언어)): insert, delete
-- truncate는 transaction 로그를 쌓지 않음(복구 불가)
truncate table contact;

create table post (
 no bigint not null auto_increment,
 title varchar(255) not null,
 content varchar(255) not null,
 createdTime varchar(255) not null,
 primary key (no)
 ) engine=InnoDB;
 
  select * from post;
 -- insert into post (created_time, title) value(2,"제목");
 -- delete from post where no =2;