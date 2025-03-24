create table vehicle(
    id bigint not null auto_increment,
    brand varchar(100) not null,
    model varchar(11) not null,
    plate varchar(7) not null unique,
    driver_id bigint not null unique,

    primary key(id),


    CONSTRAINT fk_vehicle_driver
    FOREIGN KEY (driver_id) REFERENCES driver(id)

);