create table user (
  id varchar(50) not null,
  name varchar(50),
  primary key (id)
);

create table film (
   id varchar(50) not null,
   name varchar(50),
   primary key (id)
);

create table user_film (
   id varchar(50) not null,
   film_id varchar(50) not null,
   user_id varchar(50) not null,
   watched varchar(5) not null,
   foreign key (film_id) references film,
   foreign key (user_id) references user
);