# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table day_target (
  id                        bigint not null,
  name                      varchar(255),
  date                      timestamp,
  usrname                   varchar(255),
  maker                     varchar(255),
  constraint pk_day_target primary key (id))
;

create table goal (
  id                        bigint not null,
  name                      varchar(255),
  usrname                   varchar(255),
  constraint pk_goal primary key (id))
;

create table usr_info (
  id                        bigint not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_usr_info primary key (id))
;

create sequence day_target_seq;

create sequence goal_seq;

create sequence usr_info_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists day_target;

drop table if exists goal;

drop table if exists usr_info;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists day_target_seq;

drop sequence if exists goal_seq;

drop sequence if exists usr_info_seq;

