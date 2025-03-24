create table driver(
    id bigint not null auto_increment,
    name varchar(100) not null,
    cpf varchar(11) not null unique,
    active tinyint(1) not null default 1,
    gender varchar(100) not null,
    vehicle varchar(100) not null,
    user_id bigint not null unique,

    primary key(id),


    CONSTRAINT fk_driver_user
    FOREIGN KEY (user_id) REFERENCES users(id)

);