create database sistemaDB;
use sistemaDB;

create table if not exists usuarios(
id bigint primary key not null auto_increment,
email varchar(100) not null,
senha varchar(100) not null,
role varchar(100) not null /*todo*/
);

create table if not exists clientes(
id_cliente bigint primary key not null auto_increment,
id_usuario_fk bigint not null,
nome varchar(256) not null,
cpf varchar(11) not null,
telefone varchar(20) not null,
id_endereco_fk bigint not null,
id_dieta_fk bigint,
plano boolean,
foreign key(id_endereco_fk) references enderecos(id),
foreign key(id_usuario_fk) references usuarios(id),
foreign key(id_dieta_fk) references dietas(id)
);

create table if not exists enderecos(
id bigint primary key not null auto_increment,
rua varchar(100) not null,
numCasa int not null,
bairro varchar(100),
cidade varchar(100),
uf varchar(100)
);

create table if not exists dietas(
id bigint primary key not null auto_increment,
descricao varchar(200)
);
/* nao coloquei auto_increment pois sera um codigo de barras*/
/*===========================================================================================================================*/
create table if not exists pedidos(
id bigint primary key not null,
id_cliente_fk bigint not null,
id_admin_fk bigint not null,
id_kit_fk bigint not null,
data_admissao datetime not null,
data_entrega datetime not null,
data_pagamento datetime not null,
data_conclusao datetime not null,
tipo_pagamento varchar(100) not null,
foreign key(id_cliente_fk) references clientes(id_usuario_fk),
foreign key(id_kit_fk) references kits(id),
foreign key(id_admin_fk) references usuarios(id)
);


create table if not exists kits(
id bigint primary key not null,
kit_nome varchar(200) not null,
preco double not null,
categoria varchar(200) not null
);

create table if not exists entregas(
id bigint primary key not null,
entrega_programada timestamp(6),
pedido_id_fk bigint,
usuario_id_admin_fk bigint,
foreign key(pedido_id_fk) references pedidos(id),
foreign key(usuario_id_admin_fk) references usuarios(id)
);

/*=========================================== POPULANDO AS TABELAS =======================================================*/

INSERT INTO usuarios (email, senha, role)
VALUES
    ('admin@example.com', 'senhaadmin', 'admin'), -- Administrador
    ('cliente1@example.com', 'senhacliente1', 'cliente'), -- Cliente 1
    ('cliente2@example.com', 'senhacliente2', 'cliente'); -- Cliente 2

INSERT INTO enderecos (rua, numCasa, bairro, cidade, uf)
VALUES
    ('Rua Principal', 123, 'Centro', 'Cidade A', 'PR'),
    ('Avenida Secundária', 456, 'Bairro Novo', 'Cidade B', 'PR');

INSERT INTO dietas (descricao)
VALUES
    ('Dieta Vegetariana'),
    ('Dieta Low Carb'),
    ('Dieta Mediterrânea');

INSERT INTO kits (id, kit_nome, preco, categoria)
VALUES
    (1234,'Kit Saudável', 99.99, 'Saúde e Bem-estar'),
    (12345,'Kit Fitness', 129.99, 'Fitness e Musculação'),
    (123456,'Kit Vegan', 79.99, 'Alimentação Vegana');

INSERT INTO clientes (id_usuario_fk, nome, cpf, telefone, id_endereco_fk, id_dieta_fk, plano)
VALUES
    (2, 'João da Silva', '12345678900', '987654321', 1, 1, true), -- Cliente 1
    (3, 'Maria Oliveira', '98765432100', '123456789', 2, 2, false); -- Cliente 2
    
    

INSERT INTO pedidos (id, id_cliente_fk, id_kit_fk, data_admissao, data_entrega, data_pagamento, data_conclusao, tipo_pagamento)
VALUES
    (1, 2, 1234, '2024-04-10 10:00:00', '2024-04-15 10:00:00', '2024-04-14 15:00:00', '2024-04-15 12:00:00', 'Cartão de Crédito'), -- Pedido 1 (Cliente 1)
    (2, 3, 12345, '2024-04-12 09:00:00', '2024-04-17 09:00:00', '2024-04-16 14:00:00', '2024-04-17 10:00:00', 'Boleto'); -- Pedido 2 (Cliente 2)

INSERT INTO entregas (id, entrega_programada, pedido_id_fk, usuario_id_admin_fk)
VALUES
    (1, '2024-04-15 10:00:00', 1, 1), -- Entrega 1 (Pedido 1 administrado por Admin)
    (2, '2024-04-17 09:00:00', 2, 1); -- Entrega 2 (Pedido 2 administrado por Admin)


/*===================================== TESTES DE SELEÇÕES ===================================================================*/
/* Selecionando os usuarios do tipo Admin*/
SELECT id, email
FROM usuarios
WHERE role = 'admin';

/* Uso de inner join com tabela associativa */
select * from usuarios as u
inner join clientes on clientes.id_cliente=u.id
inner join pedidos on pedidos.id_cliente_fk=u.id;

/* Selecionando endereço do cliente pelo ID*/
select * from enderecos as e inner join clientes as c where c.id_endereco_fk=e.id;

/*Resgate de todas as vendas entre duas datas*/
select * from pedidos as p where p.data_conclusao between '2024-04-15 12:00:00' and '2024-05-15 12:00:00';

/*retorne todos os tipos de kits os quais ja foram comprados anteriormente*/
select * from kits
inner join pedidos on pedidos.id_kit_fk=kits.id
inner join clientes on clientes.id_cliente=pedidos.id_cliente_fk;

/*retorne o total de clientes que estao dentro do PLANO*/
select count(c.id_cliente) from clientes as c where plano=true;

/*retorne a quantidade de clientes com nomes que contenham a letra A*/
select count(c.nome) from clientes as c where c.nome like'%a%';

/*busca com filtro nominal parcial*/
select c.id_cliente, c.nome from clientes as c where c.nome like'%ri%';

/*retornando todos os usuarios em ordem decrescente*/
SELECT
    *
    FROM
    usuario
    ORDER BY
  id DESC;


/* DELETANDO TODAS AS VENDAS CONFIRMADAS */
DELIMITER // 
CREATE PROCEDURE buscaVendasRealizadas() 
BEGIN  
    SET @VALOR = 1;
    SELECT * FROM venda v
	WHERE v.venda_realizada = @VALOR; 
    
    DELETE FROM venda v WHERE v.venda_realizada=@VALOR;
END // 
DELIMITER ; 

CALL buscaVendasRealizadas() ;


CREATE INDEX idx_cliente_endereco ON clientes (id_endereco_fk);
CREATE INDEX idx_pedido_cliente ON pedidos (id_cliente_fk);

