# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table picdata (
  id                            bigint,
  piclink                       varchar(255),
  picvalue                      varchar(255)
);


# --- !Downs

drop table if exists picdata;

