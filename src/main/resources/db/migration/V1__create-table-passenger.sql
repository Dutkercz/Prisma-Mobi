create table passenger(
    id bigint not null auto_increment,
    name varchar(100) not null,
    cpf varchar(11) not null unique,
    active tinyint(1) not null default 1,
    gender varchar(100) not null,

    primary key(id)
);