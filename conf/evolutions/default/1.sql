# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table commit_name (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_commit_name primary key (id)
);

create table arrrt (
  id                            bigint auto_increment not null,
  type                          varchar(255),
  arrrt1                        varchar(255),
  arrrt2                        varchar(255),
  arrrt3                        varchar(255),
  arrrt4                        varchar(255),
  arrrt5                        varchar(255),
  arrrt6                        varchar(255),
  arrrt7                        varchar(255),
  arrrt8                        varchar(255),
  arrrt9                        varchar(255),
  arrrt10                       varchar(255),
  arrrt11                       varchar(255),
  arrrt12                       varchar(255),
  arrrt13                       varchar(255),
  arrrt14                       varchar(255),
  arrrt15                       varchar(255),
  arrrt16                       varchar(255),
  constraint pk_arrrt primary key (id)
);

create table data_comment (
  id                            bigint auto_increment not null,
  comment_detail                varchar(255),
  comment_person                varchar(255),
  comment_date                  datetime(6),
  comment_header                varchar(255),
  other                         varchar(255),
  floor                         bigint,
  constraint pk_data_comment primary key (id)
);

create table picdata (
  id                            bigint auto_increment not null,
  piclink                       varchar(255),
  picvalue                      varchar(255),
  like_num                      bigint,
  dislike_num                   bigint,
  type_pic                      varchar(255),
  source_id                     varchar(255),
  constraint pk_picdata primary key (id)
);


# --- !Downs

drop table if exists commit_name;

drop table if exists arrrt;

drop table if exists data_comment;

drop table if exists picdata;

