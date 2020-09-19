drop table if exists patienttoken;
create table patienttoken(
    id int primary key auto_increment,
    confirmationtoken varchar(40) not null,
    createdate timestamp not null
);

alter table patienttoken add column patient_id int null;
alter table patienttoken add foreign key (patient_id) references patient(id);