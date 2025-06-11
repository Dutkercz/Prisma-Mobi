  -- USERS
  CREATE TABLE users (
      id BIGINT NOT NULL AUTO_INCREMENT,
      login VARCHAR(100) NOT NULL UNIQUE,
      password VARCHAR(100) NOT NULL,
      roles VARCHAR(100) NOT NULL,
      PRIMARY KEY (id)
  );

  -- PASSENGER
  CREATE TABLE passenger (
      id BIGINT NOT NULL AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      cpf VARCHAR(11) NOT NULL UNIQUE,
      active TINYINT(1) NOT NULL DEFAULT 1,
      gender VARCHAR(100) NOT NULL,
      user_id BIGINT UNIQUE,
      PRIMARY KEY (id),
      CONSTRAINT fk_passenger_user FOREIGN KEY (user_id) REFERENCES users(id)
  );

  -- DRIVER
  CREATE TABLE driver (
      id BIGINT NOT NULL AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      cpf VARCHAR(11) NOT NULL UNIQUE,
      active TINYINT(1) NOT NULL DEFAULT 1,
      gender VARCHAR(100) NOT NULL,
      user_id BIGINT NOT NULL UNIQUE,
      car_brand VARCHAR(100) NOT NULL,
      car_model VARCHAR(100) NOT NULL,
      manufacture_year VARCHAR(100) NOT NULL,
      car_plate VARCHAR(7) NOT NULL UNIQUE,
      PRIMARY KEY (id),
      CONSTRAINT fk_driver_user FOREIGN KEY (user_id) REFERENCES users(id)
  );

  -- RIDE
  CREATE TABLE ride (
      id BIGINT NOT NULL AUTO_INCREMENT,
      latitude_destination DOUBLE NOT NULL,
      longitude_destination DOUBLE NOT NULL,
      latitude_origin DOUBLE NOT NULL,
      longitude_origin DOUBLE NOT NULL,
      ride_request_date TIMESTAMP NOT NULL,
      ride_accept_date TIMESTAMP,
      ride_start_date TIMESTAMP,
      ride_finish_date TIMESTAMP,
      passenger_id BIGINT NOT NULL,
      driver_id BIGINT,
      ride_status VARCHAR(12) NOT NULL,
      total_price DOUBLE,
      cancelled_by VARCHAR(12),
      ride_comment VARCHAR(100),
      PRIMARY KEY (id),
      CONSTRAINT fk_passenger FOREIGN KEY (passenger_id) REFERENCES passenger(id),
      CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES driver(id)
  );
