create database javawallet;

use javawallet;

create table userInfo(
user_id varchar(20) not null primary key,
user_pw varchar(20) not null,
user_name varchar(50) not null,
wallet1 varchar(16),
wallet2 varchar(16),
wallet3 varchar(16)
);

create table wallet(
wallet_name varchar(20) not null primary key,
wallet_value double default 0,
wallet_create Date
);

create table recode(
recode_num int not null primary key auto_increment,
sender varchar(16),
reciepient varchar(16),   
recode_value double,
send_date Date
);
   
show tables;

insert into userinfo(user_id,user_pw,user_name) values ('YooSeongJae','tjdwo123','YooSeongJae');

update userInfo set wallet1 = '11414086801011' where user_id = 'YooSeongJae';
update userInfo set wallet2 = '11414086801012' where user_id = 'YooSeongJae';

insert into wallet(wallet_name,wallet_value,wallet_create) values ('11414086801011',100000,now());
insert into wallet(wallet_name,wallet_value,wallet_create) values ('11414086801012',100000,now());

select * from wallet;
