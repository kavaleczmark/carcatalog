CREATE TABLE Brands (
    id int PRIMARY KEY,
    name varchar(30),
    country varchar(70)
);
CREATE TABLE Cars (
    id int PRIMARY KEY,
    brand_id int,
    name varchar(30),
    year_of_manufacture varchar(30),
    FOREIGN KEY (brand_id) REFERENCES Brands(id)
);