ALTER TABLE vehicle.vehicle ALTER COLUMN id SET DEFAULT nextval('vehicle.vehicle_sequence');

INSERT INTO vehicle.vehicle (vin, brand, model, fuel_type, mileage_unit, kw, mileage)
VALUES ('VYJIMWKHLLIUSWQRR', 'Mercedes-Benz', 'S550', 'GASOLINE', 'MI', 279, 42000),
       ('AVBNZMOMKPDLELCJC', 'Mercedes-Benz', 'E300', 'GASOLINE', 'KM', 180, 82000),
       ('VRMZJDUMWTDDASAAK', 'Mercedes-Benz', 'E450', 'GASOLINE', 'MI', 270, 33000),
       ('ASLLDOAPMWRORWOCO', 'Mercedes-Benz', 'E300', 'GASOLINE', 'MI', 180, 70000),
       ('GJHIZGGZVYXRZDVRC', 'Mercedes-Benz', 'E350', 'DIESEL', 'MI', 212, 65000),
       ('PLZUZWYJUHUNGTUDD', 'Mercedes-Benz', 'GLC300', 'GASOLINE', 'KM', 190, 90000),
       ('MKETIBHSBXNJPGLTD', 'Mercedes-Benz', 'GLC300', 'DIESEL', 'MI', 180, 27000),
       ('QKFFPAAVGYGJXHBTM', 'Mercedes-Benz', 'GLC300', 'GASOLINE', 'KM', 190, 80000),
       ('WMRZVXCCJYFZFRYHY', 'Mercedes-Benz', 'GLC300', 'HYBRID', 'MI', 190, 60000),
       ('TFCMNMXBCULOUVEKC', 'Mercedes-Benz', 'E300', 'GASOLINE', 'MI', 180, 52000),
       ('JNVKGTCPLUJABCEXH', 'Mercedes-Benz', 'GLC300', 'DIESEL', 'KM', 190, 115000),
       ('XXSNNIOZROSVJZKFD', 'Mercedes-Benz', 'EQA250', 'ELECTRIC', 'MI', 190, 43000),
       ('ZUZZEJUOTDZOBMPHT', 'Mercedes-Benz', 'S560', 'GASOLINE', 'KM', 345, 75000),
       ('XXQCEVCJXRKMFYYGF', 'Mercedes-Benz', 'E300', 'GASOLINE', 'MI', 180, 20000),
       ('NNLULJSNQQFBTVEJG', 'Mercedes-Benz', 'S560', 'ELECTRIC', 'KM', 345, 88000),
       ('TPWGSZNTGGKAVZRTX', 'Mercedes-Benz', 'E450', 'HYBRID', 'MI', 270, 25000),
       ('PZLXJDTOMUIFZJWSD', 'Mercedes-Benz', 'EQS53', 'ELECTRIC', 'MI', 190, 10000),
       ('OJWTVEDKWJUENMTIW', 'Mercedes-Benz', 'GLC300', 'HYBRID', 'MI', 180, 38000),
       ('FJGJTHSGFKJHJKRTS', 'Mercedes-Benz', 'S560', 'DIESEL', 'KM', 345, 65000),
       ('LKIOJHYJHYTFRHJDF', 'Mercedes-Benz', 'S560', 'DIESEL', 'KM', 345, 75000);