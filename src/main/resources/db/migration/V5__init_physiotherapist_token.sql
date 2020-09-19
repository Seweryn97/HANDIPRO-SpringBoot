drop table if exists physiotherapisttoken;
create table physiotherapisttoken(
    id int primary key auto_increment,
    confirmationtoken varchar(40) not null,
    createdate timestamp not null
);

alter table physiotherapisttoken add column physiotherapist_id int null;
alter table physiotherapisttoken add foreign key (physiotherapist_id) references physiotherapist(id);