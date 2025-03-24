ALTER TABLE driver DROP COLUMN vehicle;


alter table driver add (
brand varchar(100) not null,
model varchar(100) not null,
manufacture_year varchar(100) not null,
plate varchar(7) not null unique
);