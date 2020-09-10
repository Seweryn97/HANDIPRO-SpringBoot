drop table if exists tasks;
create table tasks(
    id int primary key auto_increment,
    videoname varchar (50) not null,
    csvname varchar (50) not null
);

alter table tasks add column patient_id int null;
alter table tasks add foreign key (patient_id) references patient(id);
