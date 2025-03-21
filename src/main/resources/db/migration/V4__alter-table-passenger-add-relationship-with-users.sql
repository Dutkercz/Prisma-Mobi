ALTER TABLE passenger
ADD COLUMN user_id BIGINT;

ALTER TABLE passenger
ADD CONSTRAINT fk_passenger_user
FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE passenger
ADD CONSTRAINT unique_user_id UNIQUE (user_id);
