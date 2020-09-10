drop table if exists tasks;
create table tasks(
    id int primary key auto_increment,
    videofilename varchar (50) not null,
    csvfilename varchar (50) not null,
    videodata BLOB (MAX) not null,
    csvdata BLOB (MAX) not null
);

alter table tasks add column patient_id int null;
alter table tasks add foreign key (patient_id) references patient(id);
