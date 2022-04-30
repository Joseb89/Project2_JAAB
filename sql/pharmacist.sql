create table if not exists pharmacist(
	pharmacist_id BIGINT primary key,
	first_name VARCHAR(20) not null,
    last_name VARCHAR(30) not null,
    user_email VARCHAR(30) not null,
    user_password VARCHAR(100) not null
);

select * from pharmacist