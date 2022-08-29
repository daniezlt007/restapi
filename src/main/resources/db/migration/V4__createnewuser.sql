CREATE TABLE usuario (
    id bigint NOT NULL primary key auto_increment,
    email varchar(150) NOT NULL,
    nome varchar(100) NOT NULL,
    senha varchar(255) NOT NULL,
    token varchar(255)
);