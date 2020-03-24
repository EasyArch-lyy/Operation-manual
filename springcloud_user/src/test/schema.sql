# drop table user if exists;

create table springcloud.user(id bigint PRIMARY KEY AUTO_INCREMENT,username varchar(40),name varchar(20),age int(3),balance decimal (10,2))CHARSET utf8;
