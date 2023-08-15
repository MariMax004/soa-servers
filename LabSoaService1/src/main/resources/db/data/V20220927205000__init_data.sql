insert into coordinates (id, x, y)
VALUES (nextval('coordinates_seq'), '1', '1'),
       (nextval('coordinates_seq'), '2', '2');


insert into location (id, x, y, z, name)
VALUES (1, '1', '1', '1', '1');

insert into address (id, street, zip_code, location_id)
VALUES (1, '1', '1', 1);

insert into venue (id, name, capacity)
VALUES (1, 'Theatre', '100'), (2, 'Concert hall', '1400'), (3, 'Cafe', '50'), (4, 'Cinema', '111'), (5, 'Olympic hall', '2500');

insert into event (id, name, members)
VALUES (nextval('event_seq'), 'Dua Lipa Concert ', 'Ivan, Oleg'),
       (nextval('event_seq'), 'Birthday party', 'Olga, Andrei'),
       (nextval('event_seq'), 'Festival', 'Petr'),
       (nextval('event_seq'), 'Night party', 'Egor');

insert into person (id, name)
VALUES (nextval('person_seq'), 'Kate'),
       (nextval('person_seq'), 'Vlad'),
       (nextval('person_seq'), 'Petr'),
       (nextval('person_seq'), 'Alisa'),
       (nextval('person_seq'), 'Andrey'),
       (nextval('person_seq'), 'Polina');

insert into ticket (id, name, coordinates_id, event_id, creation_date, price, type, venue_id, person_id)
VALUES (nextval('ticket_seq'), 'Ticket 1', 1, 1, '2022-09-09', '1200', 'USUAL', 1,1);
insert into ticket (id, name, coordinates_id, event_id, creation_date, price, type, venue_id, person_id)
VALUES (nextval('ticket_seq'), 'Ticket 2', 2, 2, '2022-09-09', '500', 'VIP', 2,2),
       (nextval('ticket_seq'), 'Ticket 3', 2, 3, '2022-09-09', '980', 'BUDGETARY', 3,3),
       (nextval('ticket_seq'), 'Ticket 4', 2, 4, '2022-09-09', '2000', 'VIP', 4,4),
       (nextval('ticket_seq'), 'Ticket 50', 2, 1, '2022-09-09', '200', 'BUDGETARY', 5,5),
       (nextval('ticket_seq'), 'Ticket 6Long ticket very long', 2, 2, '2022-09-09', '1980', 'CHEAP', 1,6);






