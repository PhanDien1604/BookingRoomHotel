insert into Hotel(id,name, address, rate) values (1, N'Khách sạn FanDien', N'696 Đ. Lạc Long Quân, Nhật Tân, Tây Hồ, Hà Nội, Việt Nam', '5 sao');
insert into Hotel(id,name, address, rate) values (2, N'Khách sạn HuuTien', N'05 P. Từ Hoa, Quảng An, Tây Hồ, Hà Nội, Việt Nam', '4 sao'); 
insert into Hotel(id,name, address, rate) values (3, N'Khách sạn NguyenToan', N'Keangnam Hanoi Landmark Tower Plot E6, Cau Giay Urban Area, Hà Nội, Việt Nam', '5 sao'); 

-- bảng room---------------------------------

insert into Room(id, name, type, price, status, hotel_id) values (1, N'201', N'VIP1', 1000000, N'Còn phòng', 1);
insert into Room(id, name, type, price, status, hotel_id) values (2, N'202', N'VIP0', 700000, N'Còn phòng', 1);
insert into Room(id, name, type, price, status, hotel_id) values (3, N'301', N'VIP1', 1100000, N'Hết phòng', 1);
insert into Room(id, name, type, price, status, hotel_id) values (4, N'302', N'VIP0', 800000, N'Còn phòng', 1);
insert into Room(id, name, type, price, status, hotel_id) values (5, N'201', N'VIP2', 1000000, N'Còn phòng', 2);
insert into Room(id, name, type, price, status, hotel_id) values (6, N'202', N'VIP0', 900000, N'Còn phòng', 2);
insert into Room(id, name, type, price, status, hotel_id) values (7, N'301', N'VIP1', 1000000, N'Còn phòng', 2);
insert into Room(id, name, type, price, status, hotel_id) values (8, N'302', N'VIP1', 1000000, N'Còn phòng', 2);
insert into Room(id, name, type, price, status, hotel_id) values (9, N'201', N'VIP2', 2000000, N'Hết phòng', 3);
insert into Room(id, name, type, price, status, hotel_id) values (10, N'202', N'VIP0', 800000, N'Còn phòng', 3);
insert into Room(id, name, type, price, status, hotel_id) values (11, N'301', N'VIP1', 1000000, N'Còn phòng', 3);
insert into Room(id, name, type, price, status, hotel_id) values (12, N'302', N'VIP2', 2000000, N'Hết phòng', 3);

-- bảng  user---------------------------------

insert into User(id, username, password, position, name, address, tel, email, note, hotel_id) values(1, N'lethanh', N'111',N'Nhân viên', N'Lê Khắc Thành', N'Hà Đông', '0414938499', 'lethanh@gmail.com', '1', 1); 
insert into User(id, username, password, position, name, address, tel, email, note, hotel_id) values(2, N'hoiquy', N'222',N'Quản lý', N'Nguyễn Hồi Quy', N'Hà Đông', '0653625435', 'hoiquy@gmail.com','1', 1);
insert into User(id, username, password, position, name, address, tel, email, note, hotel_id) values(3, N'tuyetnhung', N'333',N'Quản lý', N'Vũ Tuyết Nhung', N'Hà Đông', '0785628734', 'tuyetnhung@gmail.com', '1', 2);
insert into User(id, username, password, position, name, address, tel, email, note, hotel_id) values(4, N'huudang', N'444',N'Nhân viên', N'Nguyễn Hữu Đăng', N'Hà Đông', '0743675677', 'huudang@gmail.com', '1', 2);
insert into User(id, username, password, position, name, address, tel, email, note, hotel_id) values(5, N'duypham', N'555',N'Quản lý', N'Phạm Thành Duy', N'Hà Đông', '0589437588', 'duypham@gmail.com', '1', 2);
insert into User(id, username, password, position, name, address, tel, email, note, hotel_id) values(6, N'thaihoang', N'666',N'Nhân viên', N'Vũ Thái Hoàng', N'Hà Đông', '0183674638', 'thaihoang@gmail.com', '1', 3);
insert into User(id, username, password, position, name, address, tel, email, note, hotel_id) values(7, N'thanhthuy', N'777',N'Nhân viên', N'Nguyễn Thanh Thùy', N'Hà Đông', '0578273597', 'thanhthuy@gmail.com','1', 3);
insert into User(id, username, password, position, name, address, tel, email, note, hotel_id) values(8, N'chithanh', N'888',N'Quản lý', N'Trần Chí Thanh', N'Hà Đông', '0184734676', 'chithanh@gmail.com', '1', 3);

-- bảng client -----------------------------------

insert into Client(id, username, password, name, address, tel, email, note) values(1, N'bathai', N'101', N'Nguyễn Bá Thái', N'Hà Đông', '0414938499', 'bathai@gmail.com', null);
insert into Client(id, username, password, name, address, tel, email, note) values(2, N'huutoan', N'202', N'Trần Hữu Toản', N'Hà Đông', '0593485875', 'huutoan@gmail.com', null);
insert into Client(id, username, password, name, address, tel, email, note) values(3, N'danhhuu', N'303', N'Trần Danh Hữu', N'Hà Đông', '0529375977', 'danhhuu@gmail.com', null);
insert into Client(id, username, password, name, address, tel, email, note) values(4, N'ngochang', N'404', N'Nguyễn Ngọc Hằng', N'Hà Đông', '0543878574', 'hang@gmail.com', null);
insert into Client(id, username, password, name, address, tel, email, note) values(5, N'thuytrang', N'505', N'Lê Thùy Trang', N'Hà Đông', '0594328575', 'trang@gmail.com', null);

-- bảng booking-------------------------------------

insert into Booking(id,  begin_date, end_date, note, user_id, client_id ) values(1 ,'2022-03-21', '2022-03-27', null, 5, 3);
insert into Booking(id,  begin_date, end_date, note, user_id, client_id ) values(2 ,'2022-03-21', '2022-03-27', null, 6, 2);
insert into Booking(id,  begin_date, end_date, note, user_id, client_id ) values(3 ,'2022-03-21', '2022-03-27', null, 1, 4);

-- bảng bookedroom --------------------------------------

insert into booked_room(id, date, note, room_id, booking_id) values(1, '2022-03-19', null, 7, 1);
insert into booked_room(id, date, note, room_id, booking_id) values(2, '2022-03-19', null, 11, 2);
insert into booked_room(id, date, note, room_id, booking_id) values(3, '2022-03-19', null, 1, 3);

-- bảng images ----------------------------------------

insert into Image(path, room_id) values("/images/hotel-1/201_1.jpg", 1);
insert into Image(path, room_id) values("/images/hotel-1/201_2.jpg", 1);
insert into Image(path, room_id) values("/images/hotel-1/201_3.jpg", 1);
insert into Image(path, room_id) values("/images/hotel-1/202_1.jpg", 2);
insert into Image(path, room_id) values("/images/hotel-1/202_2.jpg", 2);
insert into Image(path, room_id) values("/images/hotel-1/301_1.jpg", 3);
insert into Image(path, room_id) values("/images/hotel-1/301_2.jpg", 3);
insert into Image(path, room_id) values("/images/hotel-1/302_1.jpg", 4);
insert into Image(path, room_id) values("/images/hotel-1/302_2.jpg", 4);

insert into Image(path, room_id) values("/images/hotel-2/201_1.jpg", 5);
insert into Image(path, room_id) values("/images/hotel-2/201_2.jpg", 5);
insert into Image(path, room_id) values("/images/hotel-2/202_1.jpg", 6);
insert into Image(path, room_id) values("/images/hotel-2/202_2.jpg", 6);
insert into Image(path, room_id) values("/images/hotel-2/301_1.jpg", 7);
insert into Image(path, room_id) values("/images/hotel-2/301_2.jpg", 7);
insert into Image(path, room_id) values("/images/hotel-2/302_1.jpg", 8);
insert into Image(path, room_id) values("/images/hotel-2/302_2.jpg", 8);
insert into Image(path, room_id) values("/images/hotel-2/302_3.jpg", 8);

insert into Image(path, room_id) values("/images/hotel-3/201_1.jpg", 9);
insert into Image(path, room_id) values("/images/hotel-3/201_2.jpg", 9);
insert into Image(path, room_id) values("/images/hotel-3/202_1.jpg", 10);
insert into Image(path, room_id) values("/images/hotel-3/202_2.jpg", 10);
insert into Image(path, room_id) values("/images/hotel-3/301_1.jpg", 11);
insert into Image(path, room_id) values("/images/hotel-3/301_2.jpg", 11);
insert into Image(path, room_id) values("/images/hotel-3/301_3.jpg", 11);
insert into Image(path, room_id) values("/images/hotel-3/302_1.jpg", 12);
insert into Image(path, room_id) values("/images/hotel-3/302_2.jpg", 12);
insert into Image(path, room_id) values("/images/hotel-3/302_3.jpg", 12);
