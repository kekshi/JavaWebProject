drop database itat_shop;
create database itat_shop;
use itat_shop;
create table t_user(
	id int(10) primary key auto_increment,
	username varchar(100),
	password varchar(100),
	nickname varchar(100)
);
GRANT ALL ON itat_shop.* to 'root'@'localhost' IDENTIFIED BY '123456';