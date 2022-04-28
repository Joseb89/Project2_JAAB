CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    user_email VARCHAR(30),
    user_password VARCHAR(100),
    user_role VARCHAR(11)
);