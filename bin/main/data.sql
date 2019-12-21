Insert into User(user_id,email,userName,first_name,last_name,password,user_type,birth_date)
values (1,"walidashraf423@gmail.com","WalidAshraf","waleed","ashraf","pass1",1,STR_TO_DATE('09-04-2020 00:00:00','%m-%d-%Y %H:%i:%s'));

Insert into User(user_id,email,username,first_name,last_name,password,user_type,birth_date)
values (2,"OmarWagih@gmail.com","OmarWagih","Omar","Wagih","pass2",2,STR_TO_DATE('09-04-2020 00:00:00','%m-%d-%Y %H:%i:%s'));


Insert into Screen(screen_number, screen_rows, screen_columns)
values(1, 20, 30);

Insert into Screen(screen_number, screen_rows, screen_columns)
values(2, 100, 70);

Insert into Screen(screen_number, screen_rows, screen_columns)
values(3, 30, 70);



Insert into Movie(movie_id, genre, name, screen_number, runtime)
values(1, "Thriller", "The Shining", 1, 146);

Insert into Movie(movie_id, genre, name, screen_number, runtime)
values(2, "Action", "The Expendables", 2, 113);

Insert into Movie(movie_id, genre, name, screen_number, runtime)
values(3, "Comedy", "The Mask", 3, 101);

Insert Into Screening(screening_id, screen_number, screening_date, screening_time, movie_id)
values(1, 1, STR_TO_DATE('09-04-2020 00:00:00','%m-%d-%Y %H:%i:%s'), '09:30:00', 1);

Insert Into Screening(screening_id, screen_number, screening_date, screening_time, movie_id)
values(2, 1, STR_TO_DATE('09-04-2020 00:00:00','%m-%d-%Y %H:%i:%s'), '11:45:00', 1);

Insert Into Screening(screening_id, screen_number, screening_date, screening_time, movie_id)
values(3, 1, STR_TO_DATE('09-04-2020 00:00:00','%m-%d-%Y %H:%i:%s'), '14:00:00', 1);

Insert Into Screening(screening_id, screen_number, screening_date, screening_time, movie_id)
values(4, 1, STR_TO_DATE('09-05-2020 00:00:00','%m-%d-%Y %H:%i:%s'), '09:30:00', 1);

Insert Into Screening(screening_id, screen_number, screening_date, screening_time, movie_id)
values(5, 2, STR_TO_DATE('09-04-2020 00:00:00','%m-%d-%Y %H:%i:%s'), '09:30:00', 2);


Insert into Seat(seat_id, screening_id, row_num, col_num, ticket_number, user_id, reserved)
values(1, 1, 10, 15, 1000, 1, 1);


Insert into Seat(seat_id, screening_id, row_num, col_num, ticket_number, user_id, reserved)
values(2, 1, 10, 16, 1001, 1, 1);