create database LabResultsDB;
use LabResultsDB;

create table results (
    id int auto_increment primary key,
    data varchar(255),
    test_time timestamp default current_timestamp
);