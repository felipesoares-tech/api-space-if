create table topic (id varchar(255), image longblob, long_description varchar(255) not null, short_description varchar(255) not null, title varchar(255) not null,user_id integer, primary key (id)) engine=InnoDB;
create table user (id integer not null auto_increment, created_at datetime(6), email varchar(255),name varchar(255) not null, password varchar(255),biometric_data varchar(255), primary key (id)) engine=InnoDB;