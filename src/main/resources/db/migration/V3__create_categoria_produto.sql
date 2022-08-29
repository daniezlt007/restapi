create table categoria(
    idcategoria bigint primary key auto_increment,
    descricao varchar(40)
);

create table produto(
    idproduto bigint primary key auto_increment,
    descricao varchar(50),
    preco decimal(10,2),
    categoria_id bigint references categoria(idcategoria)
);

insert into categoria (descricao) values ("Bebidas");
insert into categoria (descricao) values ("Lacticínios");
insert into categoria (descricao) values ("Doces");

insert into produto (descricao, preco, categoria_id) values ("Coca cola Lata", 4.50, 1);
insert into produto (descricao, preco, categoria_id) values ("Leite caixa", 7.50, 2);
insert into produto (descricao, preco, categoria_id) values ("Bombom Sonho de Valsa", 0.50, 3);

insert into produto (descricao, preco, categoria_id) values ("Fanta Lata", 6.50, 1);
insert into produto (descricao, preco, categoria_id) values ("Queijo", 9.50, 2);
insert into produto (descricao, preco, categoria_id) values ("Chocolate Lacta", 9.50, 3);