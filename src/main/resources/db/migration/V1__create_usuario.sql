create table usuario(
    iduser bigint primary key auto_increment,
    name varchar(45) not null,
    login varchar(20) not null,
    password varchar(100) not null,
    hash varchar(100) not null
);