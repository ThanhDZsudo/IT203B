create database bankdb;
use bankdb;

create table accounts (
    accountid varchar(10) primary key,
    fullname varchar(50),
    balance decimal(18, 2)
);

insert into accounts values 
('acc01', 'nguyễn tiến thành', 50000), 
('acc02', 'bành trọng tú', 2000);

delimiter $$
create procedure sp_updatebalance(
    in p_id varchar(10),
    in p_amount decimal(18, 2)
)
begin
    update accounts set balance = balance + p_amount where accountid = p_id;
end $$
delimiter ;