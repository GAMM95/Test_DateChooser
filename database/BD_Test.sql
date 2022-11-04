create database test;
use test;
create table prueba(
	id int auto_increment primary key,
    fecha date null
);

drop table prueba;
select * from test.prueba;