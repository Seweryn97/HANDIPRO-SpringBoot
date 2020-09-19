drop table if exists patient;
create table patient (
    id int primary key auto_increment,
    name varchar (20) not null ,
    surname varchar (30) not null ,
    email varchar (30) not null ,
    password varchar (30) not null ,
    repeatedpassword varchar (30) not null,
    confirmedemail bit not null
);

alter table patient add column physiotherapist_id int null;
alter table patient add foreign key (physiotherapist_id) references physiotherapist(id);
