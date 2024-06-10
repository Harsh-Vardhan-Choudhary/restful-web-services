insert into user_details(id, birth_Date, name) values 
(1001, current_date(), 'Harsh'),
(1002, current_date(), 'Naman'),
(1003, current_date(), 'Adivya');


insert into post(id, description, user_id) values
(2001, 'Learn AWS', 1001),
(2002, 'Learn Devops', 1002),
(2003, 'Learn Docker', 1003);