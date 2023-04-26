ALTER TABLE dealer.dealer ALTER COLUMN id SET DEFAULT nextval('dealer.dealer_sequence');

INSERT INTO dealer.dealer (dealer_id, name, description)
VALUES ('D345GFG', 'ABC Auto Sales', 'We specialize in high-end luxury cars.'),
       ('D678DFG', 'XYZ Motors', 'We offer a wide range of new and used vehicles.'),
       ('D903DDF', '123 Car Center', 'Your one-stop-shop for all your car buying needs.'),
       ('D567YTR', 'Smith Auto Group', 'We have a large selection of pre-owned vehicles.'),
       ('D098HGF', 'Johnson Motors', 'We pride ourselves on our exceptional customer service.');
