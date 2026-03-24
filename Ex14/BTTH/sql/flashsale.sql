drop database if exists flashsaledb;
create database flashsaledb;
use flashsaledb;

-- 1. tạo bảng
create table users (
    user_id int auto_increment primary key,
    username varchar(50) not null,
    password varchar(50) not null,
    email varchar(100) unique not null
);

create table products (
    product_id int auto_increment primary key,
    product_name varchar(255) not null,
    price decimal(18,2) not null,
    stock int not null check (stock >= 0),
    category varchar(100)
);

create table orders (
    order_id int auto_increment primary key,
    user_id int not null,
    order_date timestamp default current_timestamp,
    total_amount decimal(18,2) not null check (total_amount >= 0),
    foreign key (user_id) references users(user_id)
);

create table order_details (
    detail_id int auto_increment primary key,
    order_id int not null,
    product_id int not null,
    quantity int not null check (quantity > 0),
    unit_price decimal(18,2) not null check (unit_price >= 0),
    foreign key (order_id) references orders(order_id),
    foreign key (product_id) references products(product_id)
);

-- 2. chèn 10 khách hàng mẫu
insert into users (username, password, email) values 
('nguyễn tiến thành', 'thanh123', 'thanh@gmail.com'),
('trần văn bình', 'pass123', 'binh.tv@gmail.com'),
('lê thị cẩm', 'pass123', 'cam.lt@gmail.com'),
('phạm quang dũng', 'pass123', 'dung.pq@gmail.com'),
('hoàng ngọc em', 'pass123', 'em.hn@gmail.com'),
('vũ đức phong', 'pass123', 'phong.vd@gmail.com'),
('đặng thu hà', 'pass123', 'ha.dt@gmail.com'),
('bùi xuân hinh', 'pass123', 'hinh.bx@gmail.com'),
('ngô thanh vân', 'pass123', 'van.nt@gmail.com'),
('lý hải', 'pass123', 'hai.ly@gmail.com');

-- 3. chèn 10 sản phẩm
insert into products (product_name, price, stock, category) values 
('iphone 15 pro max (flash sale)', 25000000.00, 10, 'điện thoại'),
('samsung galaxy s24 ultra', 28000000.00, 50, 'điện thoại'),
('macbook pro m3 14 inch', 40000000.00, 20, 'laptop'),
('sony playstation 5 slim', 12000000.00, 100, 'game'),
('apple watch series 9', 10000000.00, 30, 'phụ kiện'),
('tai nghe airpods pro 2', 5500000.00, 200, 'phụ kiện'),
('bàn phím cơ logitech mx', 3500000.00, 40, 'phụ kiện'),
('chuột logitech mx master 3s', 2500000.00, 60, 'phụ kiện'),
('màn hình lg ultragear', 8000000.00, 25, 'máy tính'),
('loa bluetooth marshall', 6500000.00, 15, 'âm thanh');

-- 4. chèn đơn hàng mẫu (để test báo cáo top buyer)
insert into orders (user_id, total_amount) values 
(2, 28000000.00),
(3, 40000000.00),
(4, 12000000.00),
(2, 5500000.00), 
(10, 8000000.00);

insert into order_details (order_id, product_id, quantity, unit_price) values 
(1, 2, 1, 28000000.00),
(2, 3, 1, 40000000.00),
(3, 4, 1, 12000000.00),
(4, 6, 1, 5500000.00),
(5, 9, 1, 8000000.00);

-- 5. stored procedure & function
delimiter //
create procedure sp_get_top_buyers()
begin
    select u.username, sum(o.total_amount) as total_spent
    from users u join orders o on u.user_id = o.user_id
    group by u.user_id order by total_spent desc limit 5;
end //
delimiter ;

delimiter //
create function func_calculate_category_revenue(cat_name varchar(100)) 
returns decimal(18,2)
deterministic
begin
    declare total_rev decimal(18,2);
    select coalesce(sum(od.quantity * od.unit_price), 0) into total_rev
    from order_details od join products p on od.product_id = p.product_id
    where p.category = cat_name;
    return total_rev;
end //
delimiter ;