create sequence coordinates_seq;
create table coordinates
(
    id int primary key,
    x  int,
    y  float
);
create table location
(
    id   int primary key,
    x    int,
    y    float,
    z    float,
    name text
);

create table address
(
    id          int primary key,
    street      text,
    zip_code    text,
    location_id int,
    FOREIGN KEY (location_id) REFERENCES location (id)
);
create table venue
(
    id       int primary key,
    name     text,
    capacity int
);
create sequence event_seq;
create table event
(
    id      int primary key,
    name    text,
    members text
);

create sequence person_seq;
create table person
(
    id        int primary key,
    name      text
);


create sequence ticket_seq;
create table ticket
(
    id             int primary key,
    name           text,
    coordinates_id int,
    event_id       int,
    creation_date  date,
    price          float,
    type           varchar(64),
    venue_id       int,
    person_id      int,
    FOREIGN KEY (coordinates_id) REFERENCES coordinates (id),
    FOREIGN KEY (venue_id) REFERENCES venue (id),
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (person_id) REFERENCES person (id)
);



