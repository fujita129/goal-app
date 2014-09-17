# --- First database schema

# --- !Ups

create table day_target (
  id                        bigint not null,
  name                      varchar(255),
  date                      timestamp,
  constraint pk_day_target primary key (id))
;

create sequence day_target_seq start with 1000;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists day_target;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists day_target_seq;