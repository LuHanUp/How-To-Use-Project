create database mybatis_plus;

create table tbl_employee(
    id int primary key auto_increment,
    las_name varchar(50),
    email varchar(50),
    gender char(1),
    age int
);
insert into tbl_employee(las_name, email, gender, age) VALUES ('Tome','tom@atguigu.com',1,22);
insert into tbl_employee(las_name, email, gender, age) VALUES ('jerry','jerry@atguigu.com',0,25);
insert into tbl_employee(las_name, email, gender, age) VALUES ('black','black@atguigu.com',1,30);
insert into tbl_employee(las_name, email, gender, age) VALUES ('white','white@atguigu.com',0,35);