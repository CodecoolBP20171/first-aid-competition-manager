create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(255), owner_id int4, primary key (id))
create table teams (id  serial not null, category varchar(255), competitionId int4 not null, name varchar(100), pin_code int4, team_number int4, primary key (id))
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(255), owner_id int4, primary key (id))
create table teams (id  serial not null, category varchar(255), competitionId int4 not null, name varchar(100), pin_code int4, team_number int4, primary key (id))
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), taskId_id int4, teamId_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competitionID_id int4, primary key (id))
create table sub_task_items (id  serial not null, name varchar(100), score int2, primary key (id))
create table sub_task_of_tasks (id  serial not null, score int4, primary key (id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int2, primary key (id))
create table teams (id  serial not null, category varchar(255), competitionId int4 not null, name varchar(100), pin_code int4, team_number int4, primary key (id))
create table users (full_name  serial not null, email varchar(100), password varchar(15), user_name varchar(15), primary key (full_name))
alter table protests add constraint FKmmxe71pehngyg7v8s18kns3lm foreign key (taskId_id) references tasks
alter table protests add constraint FKp9qo0oaplh9oci6q53kdawjs6 foreign key (teamId_id) references teams
alter table stations add constraint FKjx0gj5nvrk1tdergjunbpjskw foreign key (competitionID_id) references competitions
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), task_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competitionID_id int4, primary key (id))
create table sub_task_items (id  serial not null, name varchar(100), score int2, primary key (id))
create table sub_task_of_tasks (id  serial not null, score int4, primary key (id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int2, primary key (id))
create table teams (id  serial not null, category varchar(255), competitionId int4 not null, name varchar(100), pin_code int4, team_number int4, primary key (id))
create table users (full_name  serial not null, email varchar(100), password varchar(15), user_name varchar(15), primary key (full_name))
alter table protests add constraint FK6faqf2xa6mga7otralcduos7b foreign key (task_id) references tasks
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKjx0gj5nvrk1tdergjunbpjskw foreign key (competitionID_id) references competitions
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), task_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competitionID_id int4, primary key (id))
create table sub_task_items (id  serial not null, name varchar(100), score int2, primary key (id))
create table sub_task_of_tasks (id  serial not null, score int4, primary key (id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int2, primary key (id))
create table teams (id  serial not null, category varchar(255), competitionId int4 not null, name varchar(100), pin_code int4, team_number int4, primary key (id))
create table users (full_name  serial not null, email varchar(100), password varchar(15), user_name varchar(15), primary key (full_name))
alter table protests add constraint FK6faqf2xa6mga7otralcduos7b foreign key (task_id) references tasks
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKjx0gj5nvrk1tdergjunbpjskw foreign key (competitionID_id) references competitions
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), task_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competition_id int4, primary key (id))
create table sub_task_items (id  serial not null, name varchar(100), score int2, primary key (id))
create table sub_task_of_tasks (id  serial not null, score int4, primary key (id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int2, primary key (id))
create table teams (id  serial not null, category varchar(255), competitionId int4 not null, name varchar(100), pin_code int4, team_number int4, primary key (id))
create table users (full_name  serial not null, email varchar(100), password varchar(15), user_name varchar(15), competition_id int4, primary key (full_name))
alter table protests add constraint FK6faqf2xa6mga7otralcduos7b foreign key (task_id) references tasks
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKnbulne0bfj46trxctqp31wdy4 foreign key (competition_id) references competitions
alter table users add constraint FKn0dau3siu0cpmqqft6iktch42 foreign key (competition_id) references competitions
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), task_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competition_id int4, primary key (id))
create table sub_task_items (id  serial not null, name varchar(100), score int2, primary key (id))
create table sub_task_of_tasks (id  serial not null, score int4, primary key (id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int2, primary key (id))
create table teams (id  serial not null, category varchar(255), competitionId int4 not null, name varchar(100), pin_code int4, team_number int4, primary key (id))
create table users (full_name  serial not null, email varchar(100), password varchar(15), user_name varchar(15), competition_id int4, primary key (full_name))
alter table protests add constraint FK6faqf2xa6mga7otralcduos7b foreign key (task_id) references tasks
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKnbulne0bfj46trxctqp31wdy4 foreign key (competition_id) references competitions
alter table users add constraint FKn0dau3siu0cpmqqft6iktch42 foreign key (competition_id) references competitions
