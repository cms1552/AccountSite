create database moneyweb;

create table member(
	name varchar(10) not null,
	id varchar(10) not null,
	password varchar(10) not null,
	email varchar(10),
	phone varchar(10) not null,
	primary key(id)
);

create table moneynote(
	No int not null auto_increment primary key,
	id varchar(10) not null,
	email varchar(30),
	money int not null,
	ino char(3) not null,
	note varchar(20) not null,
	iodate date not null,
	memo varchar(50),
	confirm varchar(1) not null default 'n',
	foreign key(id) references member(id)
);

insert into moneynote(id, email, money, ino, note, iodate, memo, confirm) values('adsf', 'sdf', 324, 'in','note', '2021.04.23', 'none', 'n')
drop table moneynote;