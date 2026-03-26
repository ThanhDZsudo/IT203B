create database HospitalManagementDB;
use HospitalManagementDB;

-- 1. bảng bệnh nhân
create table patients (
    p_id int primary key,
    p_name varchar(255),
    status varchar(50) -- 'đang điều trị', 'đã xuất viện'
);

-- 2. bảng giường bệnh
create table beds (
    bed_id varchar(10) primary key,
    p_id int,
    status varchar(50), -- 'đang sử dụng', 'trống'
    foreign key (p_id) references patients(p_id)
);

-- 3. bảng hóa đơn
create table invoices (
    inv_id int auto_increment primary key,
    p_id int,
    amount decimal(18,2),
    inv_date timestamp default current_timestamp,
    foreign key (p_id) references patients(p_id)
);

-- thêm dữ liệu mẫu
insert into patients values (101, 'nguyễn văn a', 'đang điều trị');
insert into beds values ('g01', 101, 'đang sử dụng');