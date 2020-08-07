drop table if exists registration;
create table registration (
    id int primary key auto_increment,
    name varchar (20) not null ,
    surname varchar (30) not null ,
    email varchar (30) not null ,
    password varchar (30) not null ,
    repeatedpassword varchar (30) not null,
    confirmedemail bit
)