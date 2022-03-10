CREATE TABLE client(client_id SERIAL PRIMARY KEY, last_name varchar, first_name varchar,
                    email varchar, city varchar, age int, sex varchar);
CREATE TABLE specialization(specialization_id SERIAL PRIMARY KEY, specialization_name varchar);
CREATE TABLE therapist(therapist_id SERIAL PRIMARY KEY, last_name varchar, first_name varchar,
                       email varchar, city varchar, age int, sex varchar, experience int,
                       specialization_id int REFERENCES specialization(specialization_id));
CREATE TABLE therapy_session(session_id SERIAL PRIMARY KEY, client_id int REFERENCES client(client_id),
                             therapist_id int REFERENCES therapist(therapist_id), session_date date, session_time time);
