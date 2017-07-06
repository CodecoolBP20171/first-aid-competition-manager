create table competitions (id  serial not null, date_of_event date, location varchar(255), name varchar(100), owner_id int8, primary key (id))
create table exercise (id  serial not null, description varchar(1000), name varchar(100), primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), exercise_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competition_id int4, exercise_id int4, primary key (id))
create table tasks (id  serial not null, defaultTaks boolean, name varchar(100), score int8, prerequisiteTask_id int4, primary key (id))
create table tasks_of_exercises (exercise_id int4 not null, task_id int4 not null)
create table team_results (id  serial not null, result_score int4, team_id int4, primary key (id))
create table teams (id  serial not null, category varchar(255), name varchar(100), pin_code int4, team_number int4, competition_id int4, primary key (id))
create table users (id  bigserial not null, email varchar(100), full_name varchar(50), password varchar(15), user_name varchar(15), primary key (id))
alter table competitions add constraint FKhd9nwl9ni49dqywsshrfu72yy foreign key (owner_id) references users
alter table protests add constraint FKsglr7jcfeogkxadod525xwux6 foreign key (exercise_id) references exercise
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKnbulne0bfj46trxctqp31wdy4 foreign key (competition_id) references competitions
alter table stations add constraint FK3v7hdrjvv591lx1cqsyg519y7 foreign key (exercise_id) references exercise
alter table tasks add constraint FKrxkfbt1e9osexiihmjxffi7ef foreign key (prerequisiteTask_id) references tasks
alter table tasks_of_exercises add constraint FKdw7tgdr7t1a1pfdj8frf3wi5r foreign key (task_id) references tasks
alter table tasks_of_exercises add constraint FKcr61sp4l4rq7v5nsdennjlof6 foreign key (exercise_id) references exercise
alter table team_results add constraint FKsoncmvtxf60p7dj7eiu6sx7e2 foreign key (team_id) references teams
alter table teams add constraint FKl8qf5a495ra0s5l9gjo157h7g foreign key (competition_id) references competitions
