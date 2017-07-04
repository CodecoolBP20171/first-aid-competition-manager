create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(255), owner_id int4, primary key (id))
create table teams (id  serial not null, category varchar(255), competitionId int4 not null, name varchar(100), pin_code int4, team_number int4, primary key (id))
