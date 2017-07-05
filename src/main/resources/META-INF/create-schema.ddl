create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), task_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competition_id int4, task_id int4, primary key (id))
create table sub_task (id  serial not null, name varchar(100), score int2, primary key (id))
create table sub_task_of_tasks (tasks_id int4 not null, subTasks_id int4 not null, primary key (tasks_id, subTasks_id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int2, primary key (id))
create table teams (id  serial not null, category varchar(255), name varchar(100), pin_code int4, team_number int4, competition_id int4, primary key (id))
create table users (id  bigserial not null, email varchar(100), full_name varchar(50), password varchar(15), user_name varchar(15), competition_id int4, primary key (id))
alter table protests add constraint FK6faqf2xa6mga7otralcduos7b foreign key (task_id) references tasks
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKnbulne0bfj46trxctqp31wdy4 foreign key (competition_id) references competitions
alter table stations add constraint FKe9qo4g56tlbi86saau7norr43 foreign key (task_id) references tasks
alter table sub_task_of_tasks add constraint FKac3u6hjdjjog0oyfos23toam9 foreign key (subTasks_id) references sub_task
alter table sub_task_of_tasks add constraint FKopbku2nd2yfffrwcpt9169gt3 foreign key (tasks_id) references tasks
alter table teams add constraint FKl8qf5a495ra0s5l9gjo157h7g foreign key (competition_id) references competitions
alter table users add constraint FKn0dau3siu0cpmqqft6iktch42 foreign key (competition_id) references competitions
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), task_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competition_id int4, task_id int4, primary key (id))
create table sub_task (id  serial not null, name varchar(100), score int8, primary key (id))
create table sub_task_of_tasks (tasks_id int4 not null, subTasks_id int4 not null, primary key (tasks_id, subTasks_id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int2, primary key (id))
create table teams (id  serial not null, category varchar(255), name varchar(100), pin_code int4, team_number int4, competition_id int4, primary key (id))
create table users (id  bigserial not null, email varchar(100), full_name varchar(50), password varchar(15), user_name varchar(15), competition_id int4, primary key (id))
alter table protests add constraint FK6faqf2xa6mga7otralcduos7b foreign key (task_id) references tasks
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKnbulne0bfj46trxctqp31wdy4 foreign key (competition_id) references competitions
alter table stations add constraint FKe9qo4g56tlbi86saau7norr43 foreign key (task_id) references tasks
alter table sub_task_of_tasks add constraint FKac3u6hjdjjog0oyfos23toam9 foreign key (subTasks_id) references sub_task
alter table sub_task_of_tasks add constraint FKopbku2nd2yfffrwcpt9169gt3 foreign key (tasks_id) references tasks
alter table teams add constraint FKl8qf5a495ra0s5l9gjo157h7g foreign key (competition_id) references competitions
alter table users add constraint FKn0dau3siu0cpmqqft6iktch42 foreign key (competition_id) references competitions
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), task_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competition_id int4, task_id int4, primary key (id))
create table sub_task (id  serial not null, name varchar(100), score int8, primary key (id))
create table sub_task_of_tasks (tasks_id int4 not null, subTasks_id int4 not null, primary key (tasks_id, subTasks_id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int4, team_id int4, primary key (id))
create table teams (id  serial not null, category varchar(255), name varchar(100), pin_code int4, team_number int4, competition_id int4, primary key (id))
create table users (id  bigserial not null, email varchar(100), full_name varchar(50), password varchar(15), user_name varchar(15), competition_id int4, primary key (id))
alter table protests add constraint FK6faqf2xa6mga7otralcduos7b foreign key (task_id) references tasks
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKnbulne0bfj46trxctqp31wdy4 foreign key (competition_id) references competitions
alter table stations add constraint FKe9qo4g56tlbi86saau7norr43 foreign key (task_id) references tasks
alter table sub_task_of_tasks add constraint FKac3u6hjdjjog0oyfos23toam9 foreign key (subTasks_id) references sub_task
alter table sub_task_of_tasks add constraint FKopbku2nd2yfffrwcpt9169gt3 foreign key (tasks_id) references tasks
alter table team_results add constraint FKsoncmvtxf60p7dj7eiu6sx7e2 foreign key (team_id) references teams
alter table teams add constraint FKl8qf5a495ra0s5l9gjo157h7g foreign key (competition_id) references competitions
alter table users add constraint FKn0dau3siu0cpmqqft6iktch42 foreign key (competition_id) references competitions
create table competitions (id  serial not null, date_of_event timestamp, location varchar(255), name varchar(100), owner_id int4, primary key (id))
create table protests (id  serial not null, decision varchar(255), justification varchar(255), task_id int4, team_id int4, primary key (id))
create table stations (id  serial not null, description varchar(1000), name varchar(50), number int4, competition_id int4, task_id int4, primary key (id))
create table sub_task (id  serial not null, name varchar(100), score int8, primary key (id))
create table sub_task_of_tasks (tasks_id int4 not null, subTasks_id int4 not null, primary key (tasks_id, subTasks_id))
create table tasks (id  serial not null, name varchar(100), score int4, primary key (id))
create table team_results (id  serial not null, result_score int4, team_id int4, primary key (id))
create table teams (id  serial not null, category varchar(255), name varchar(100), pin_code int4, team_number int4, competition_id int4, primary key (id))
create table users (id  bigserial not null, email varchar(100), full_name varchar(50), password varchar(15), user_name varchar(15), competition_id int4, primary key (id))
alter table protests add constraint FK6faqf2xa6mga7otralcduos7b foreign key (task_id) references tasks
alter table protests add constraint FKr8f6hgye65cqlw84o02tlp59q foreign key (team_id) references teams
alter table stations add constraint FKnbulne0bfj46trxctqp31wdy4 foreign key (competition_id) references competitions
alter table stations add constraint FKe9qo4g56tlbi86saau7norr43 foreign key (task_id) references tasks
alter table sub_task_of_tasks add constraint FKac3u6hjdjjog0oyfos23toam9 foreign key (subTasks_id) references sub_task
alter table sub_task_of_tasks add constraint FKopbku2nd2yfffrwcpt9169gt3 foreign key (tasks_id) references tasks
alter table team_results add constraint FKsoncmvtxf60p7dj7eiu6sx7e2 foreign key (team_id) references teams
alter table teams add constraint FKl8qf5a495ra0s5l9gjo157h7g foreign key (competition_id) references competitions
alter table users add constraint FKn0dau3siu0cpmqqft6iktch42 foreign key (competition_id) references competitions
