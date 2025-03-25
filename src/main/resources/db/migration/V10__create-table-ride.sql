create table ride(
    id bigint not null unique auto_increment,
    latitude_destination double not null,
    longitude_destination double not null,
    latitude_origin double not null,
    longitude_origin double not null,
    ride_date timestamp not null,
    passenger_id bigint not null,
    driver_id bigint not null,

    primary key (id),

    CONSTRAINT fk_passenger FOREIGN KEY (passenger_id) REFERENCES passenger(id),
    CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES driver(id)


);