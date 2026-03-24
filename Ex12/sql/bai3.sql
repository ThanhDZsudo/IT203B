create database HospitalFeeDB;
use HospitalFeeDB;

create table surgeries (
    surgery_id int primary key,
    surgery_name varchar(255),
    base_cost decimal(18,2)
);

insert into surgeries 
values (01, 'phẫu thuật nội soi', 150000.00);

delimiter $$
create procedure get_surgery_fee(in p_id int, out p_total decimal(10,2))
begin
    select (base_cost * 1.1) into p_total 
    from surgeries 
    where surgery_id = p_id;
end $$
delimiter ;