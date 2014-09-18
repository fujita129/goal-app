# --- First database schema

# --- !Ups

create table day_target (
  id                        bigint not null,
  name                      varchar(255),
  date                      timestamp,
  user                      varchar(255),
  constraint pk_day_target primary key (id))
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_userinfo primary key (id))
;

create sequence user_seq start with 1000;

create sequence day_target_seq start with 1000;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists day_target;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists day_target_seq;

drop sequence if exists user_seq;