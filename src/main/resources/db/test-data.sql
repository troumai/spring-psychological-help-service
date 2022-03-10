INSERT INTO specialization(specialization_name)VALUES ('Motivation');
INSERT INTO specialization(specialization_name)VALUES ('Burnout');
INSERT INTO specialization(specialization_name)VALUES ('Stress');

INSERT INTO public.therapist(age, city, email, experience, first_name, last_name, sex, specialization_id)
VALUES (26, 'Almaty', 'timothee@gmail.com', 5, 'Timothée', 'Chalamet', 'male', 1);

INSERT INTO public.therapist(age, city, email, experience, first_name, last_name, sex, specialization_id)
VALUES (32, 'Moscow', 'zendaya@gmail.com', 10, 'Zendaya', 'Coleman', 'female', 2);

INSERT INTO public.therapist(age, city, email, experience, first_name, last_name, sex, specialization_id)
VALUES (20, 'Almaty', 'tom@gmail.com', 1, 'Tom', 'Holland', 'male', 3);

INSERT INTO client(age, city, email, first_name, last_name, sex)
VALUES (20, 'Almaty', 'abimoldayevat@gmail.com', 'Tomiris', 'Abimoldayeva', 'female');

INSERT INTO client(age, city, email, first_name, last_name, sex)
VALUES (33, 'Tokyo', 'chris@gmail.com', 'Chris', 'Brown', 'male');

INSERT INTO client(age, city, email, first_name, last_name, sex)
VALUES (20, 'Kiev', 'john@gmail.com', 'John', 'Legend', 'male');

INSERT INTO therapy_session(client_id, therapist_id, session_date, session_time)
VALUES ( 1, 1, '2022-03-01', '15:00');

INSERT INTO therapy_session(client_id, therapist_id, session_date, session_time)
VALUES ( 2, 2, '2022-03-02', '16:00');

INSERT INTO therapy_session(client_id, therapist_id, session_date, session_time)
VALUES ( 3, 3, '2022-03-02', '17:00');