create database EmergencyRoomDB;
use EmergencyRoomDB;

create table vitals (
    p_id int primary key,
    patient_name varchar(255),
    temperature double,
    heart_rate int
);

insert into vitals (p_id, patient_name, temperature, heart_rate) 
values (01, 'nguyen tien thanh', 36.5, 80);