create table cliente (id integer not null auto_increment, dat_lan datetime(6), email varchar(255) not null, nome varchar(255) not null, obs varchar(255), senha varchar(255) not null, primary key (id)) engine=InnoDB;
create table product (id varchar(255) not null, name varchar(255), price integer, primary key (id)) engine=InnoDB;
create table user (id integer not null auto_increment, dat_lan datetime(6), email varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB;
create table topic (id varchar(255) not null, image tinyblob, long_description varchar(255) not null, short_description varchar(255) not null, title varchar(255) not null, primary key (id)) engine=InnoDB;
create table user (id integer not null auto_increment, biometric_data varchar(255), created_at datetime(6), email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB;
