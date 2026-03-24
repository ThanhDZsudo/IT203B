create database HospitalSecurityDB;
use HospitalSecurityDB;

create table doctors (
    id int auto_increment primary key,
    doctor_code varchar(50) ,
    password varchar(50) ,
    full_name varchar(255)
);

insert into doctors (doctor_code, password, full_name) values 
('bs1', 'thanh123', 'nguyễn tiến thành'),
('bs2', '123456', 'bác sĩ bình');