CREATE DATABASE tfdata;
USE tfdata;


CREATE TABLE IF NOT EXISTS usuario(
id bigint primary key not null auto_increment,
email varchar(100) not null,
senha varchar(100) not null,
role_user varchar(20) not null
);

CREATE INDEX idx_role_user ON usuario (role_user); -- Otimiza consultas

CREATE TABLE IF NOT EXISTS admin_user(
id_admin bigint primary key not null,
id_usuario_fk bigint not null,
foreign key(id_usuario_fk) references usuario(id)
);
CREATE TABLE IF NOT EXISTS cliente(
id_cliente bigint primary key not null auto_increment,
id_usuario_fk bigint not null,
nome varchar(256) not null,
cpf varchar(11) not null,
telefone varchar(20) not null,
id_endereco_fk bigint not null,
id_dieta_fk bigint,
plano boolean default false,
foreign key(id_usuario_fk) references usuario(id),
foreign key(id_endereco_fk) references endereco(id_endereco),
foreign key(id_dieta_fk) references dieta(id_dieta)
);

CREATE TABLE IF NOT EXISTS endereco(
id_endereco bigint primary key not null auto_increment,
rua varchar(100) not null,
numCasa int not null,
bairro varchar(100),
cidade varchar(100),
uf varchar(3)
);

CREATE TABLE IF NOT EXISTS dieta(
id_dieta bigint primary key not null auto_increment,
descricao varchar(200)
);

CREATE TABLE IF NOT EXISTS kit (
    id_kit BIGINT PRIMARY KEY NOT NULL,
    kit_nome VARCHAR(200) NOT NULL,
    preco DOUBLE NOT NULL,
    categoria VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedido(
id_pedido bigint primary key not null, /*COMO CODIGO DE BARRAS*/
id_cliente_fk bigint not null,
kit_fk bigint not null,
foreign key(kit_fk) references kit(id_kit),
foreign key(id_cliente_fk) references cliente(id_cliente)
);

CREATE TABLE IF NOT EXISTS venda(
id_venda bigint primary key not null, /*COLOCAR ID SEMELHANTE A CODIGO DE BARRAS*/
id_cliente_fk bigint not null,
id_admin_user_fk bigint not null,
id_pedido_fk bigint not null,
entrega_programada_fk bigint not null,
tipo_pagamento_fk bigint not null,
data_admissao datetime not null,
data_pagamento datetime not null,
data_conclusao datetime not null,
venda_realizada boolean default false,
foreign key(id_cliente_fk) references cliente(id_cliente),
foreign key(id_admin_user_fk) references admin_user(id_admin),
foreign key(id_pedido_fk) references pedido(id_pedido),
foreign key(entrega_programada_fk) references entrega_programada(id_entrega),
foreign key(tipo_pagamento_fk) references tipo_pagamento(id_pagamento)

);
CREATE TABLE IF NOT EXISTS tipo_pagamento(
id_pagamento bigint primary key not null auto_increment,
descricao varchar(50) not null /*SE O CLIENTE TIVER PLANO ENTRA AQUI*/
);

CREATE TABLE IF NOT EXISTS entrega_programada(
id_entrega bigint primary key not null, /*CODIGO DE BARRAS*/
data_entrega datetime not null
);



CREATE TABLE IF NOT EXISTS nota_fiscal (
    id_nf BIGINT PRIMARY KEY AUTO_INCREMENT,
    idVenda BIGINT NOT NULL,
    dataVenda DATETIME NOT NULL
);

select * from venda;

-- Monitora e registra vendas confirmadas na tabela nota_fiscal
DELIMITER //
create trigger vendas_realizadas
after update on venda
for each row
begin
	if old.venda_realizada <> new.venda_realizada then
    insert into nota_fiscal(idVenda, dataVenda) values
    (new.id_venda, now());
    end if;
end; //
DELIMITER ;

/*										POPULANDO TABELAS              								*/
-- ENDEREÇOS
INSERT INTO endereco (rua, numCasa, bairro, cidade, uf)
VALUES
    ('Rua A', 100, 'Centro', 'São Paulo', 'SP'),
    ('Rua B', 200, 'Jardins', 'Rio de Janeiro', 'RJ'),
    ('Av. C', 300, 'Copacabana', 'Rio de Janeiro', 'RJ'),
    ('Rua D', 400, 'Boa Vista', 'Porto Alegre', 'RS'),
    ('Av. E', 500, 'Barra', 'Salvador', 'BA'),
	('Rua F', 600, 'Porto', 'Foz do Iguaçu','PR'),
	('Rua G',700, 'Vila C', 'Foz do Iguaçu','PR');


-- USUARIOS 
select * from usuario;-- Cliente 1
INSERT INTO usuario (id, email, senha, role_user)
VALUES (1, 'clientezoro@email.com', 'senha123', 'cliente');

INSERT INTO cliente (id_usuario_fk, nome, cpf, telefone, id_endereco_fk)
VALUES (1, 'Zoro', '12345678901', '(11) 98765-4321', 1);

-- Cliente 2
INSERT INTO usuario (id, email, senha, role_user)
VALUES (2, 'clientejotaro@email.com', 'senha456', 'cliente');

INSERT INTO cliente (id_usuario_fk, nome, cpf, telefone, id_endereco_fk)
VALUES (2, 'Jotaro', '23456789012', '(21) 98765-4321', 2);

-- Cliente 3
INSERT INTO usuario (id, email, senha, role_user)
VALUES (3, 'clientenami@email.com', 'senha789', 'cliente');

INSERT INTO cliente (id_usuario_fk, nome, cpf, telefone, id_endereco_fk)
VALUES (3, 'Nami', '34567890123', '(51) 98765-4321', 3);

-- Cliente 4
INSERT INTO usuario (id, email, senha, role_user)
VALUES (4, 'clientesabo@email.com', 'senhaabc', 'cliente');

INSERT INTO cliente (id_usuario_fk, nome, cpf, telefone, id_endereco_fk)
VALUES (4, 'Sabo', '45678901234', '(71) 98765-4321', 4);

-- Cliente 5
INSERT INTO usuario (id, email, senha, role_user)
VALUES (5, 'clienterobin@email.com', 'senhaxyz', 'cliente');

INSERT INTO cliente (id_usuario_fk, nome, cpf, telefone, id_endereco_fk)
VALUES (5, 'Robin', '56789012345', '(11) 98765-5432', 5);


-- ADD CLIENTES COM PLANOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

INSERT INTO usuario (id, email, senha, role_user)
VALUES (7, 'clienterukia@email.com', 'senhadsyz', 'cliente');

INSERT INTO cliente (id_usuario_fk, nome, cpf, telefone, id_endereco_fk,plano)
VALUES (7, 'Rukia', '46789012345', '(11) 28765-5432', 7,1);

INSERT INTO usuario (id, email, senha, role_user)
VALUES (8, 'clientedoflamingo@email.com', 'senhaxkct', 'cliente');

INSERT INTO cliente (id_usuario_fk, nome, cpf, telefone, id_endereco_fk,plano)
VALUES (8, 'Doflamingo', '56259012345', '(11) 98065-2432', 6,1);




-- ADMIN

INSERT INTO usuario (id, email, senha, role_user)
VALUES (6, 'admin1212@email.com', 'senhaABC', 'admin');

INSERT INTO admin_user (id_admin,id_usuario_fk)
VALUES (6,6);

-- DIETAS

INSERT INTO dieta (descricao)
VALUES
    ('Dieta Low Carb'),
    ('Dieta Vegetariana'),
    ('Dieta Mediterrânea'),
    ('Dieta Detox'),
    ('Dieta Sem Glúten');

-- KITS
INSERT INTO kit (id_kit, kit_nome, preco, categoria)
VALUES
    (1, 'Kit Fitness', 199.99, 'Fitness'),
    (2, 'Kit Café da Manhã', 49.99, 'Café da Manhã'),
    (3, 'Kit Churrasco', 149.99, 'Churrasco'),
    (4, 'Kit Vegetariano', 129.99, 'Vegetariano'),
    (5, 'Kit Lanches Saudáveis', 79.99, 'Lanches');

-- PAGAMENTO
INSERT INTO tipo_pagamento (descricao)
VALUES
    ('Cartão de Crédito'),
    ('Cartão de Débito'),
    ('Transferência Bancária'),
    ('Dinheiro'),
    ('Plano');

select * from tipo_pagamento;
-- ENTREGAS
INSERT INTO entrega_programada (id_entrega, data_entrega)
VALUES
    (1, '2024-04-15 10:00:00'),
    (2, '2024-04-17 14:30:00'),
    (3, '2024-04-20 12:00:00'),
    (4, '2024-04-22 11:00:00'),
    (5, '2024-04-25 16:00:00');

-- REALIZANDO UMA VENDA AO CLIENTE DE NOME JOTARO
-- Cria o pedido
INSERT INTO pedido (id_pedido, id_cliente_fk, kit_fk)
VALUES
    (1, 2, 1); -- Aqui, 2 é o id do cliente Jotaro e 1 é o id do Kit Fitness

-- Realizar a Venda 

INSERT INTO venda (id_venda, id_cliente_fk, id_admin_user_fk, id_pedido_fk, entrega_programada_fk, tipo_pagamento_fk, data_admissao, data_pagamento, data_conclusao)
VALUES
    (1, 2, 6, 1, 1, 1, NOW(), NOW(), NOW());

UPDATE venda
SET venda_realizada = TRUE
WHERE id_venda = 1; -- id da venda realizada


ALTER TABLE venda ADD preco_kit DOUBLE DEFAULT 0;


-- Definir a trigger para atualizar o preço total na tabela VENDA

DELIMITER //

CREATE TRIGGER atualiza_preco_total
BEFORE INSERT ON venda
FOR EACH ROW
BEGIN
    DECLARE preco_kit DOUBLE;

    -- Obter o preço do kit associado ao pedido
    SELECT preco INTO preco_kit
    FROM kit
    WHERE id_kit = (
        SELECT kit_fk
        FROM pedido
        WHERE id_pedido = NEW.id_pedido_fk
    );

    -- Calcular e atribuir o preço total na nova venda
    SET NEW.preco_kit = preco_kit;
END;
//

DELIMITER ;


-- ADICIONANDO NOVA VENDA PARA TESTAR VALOR TOTAL
select * from venda;
select * from pedido;
-- pedido 4 ja criado
INSERT INTO venda (id_venda, id_cliente_fk, id_admin_user_fk, id_pedido_fk, entrega_programada_fk, tipo_pagamento_fk, data_admissao, data_pagamento, data_conclusao)
VALUES
    (2, 7, 6, 4, 2, 1, NOW(), NOW(), NOW());

UPDATE venda
SET venda_realizada = TRUE
WHERE id_venda = 2; -- id da venda realizada


-- NOVA VENDA PARA TESTAR VALOR TOTAL PARA CLIENTE COM PLANO

INSERT INTO pedido (id_pedido, id_cliente_fk, kit_fk)
VALUES
    (60, 3, 4); -- Aqui, 2 é o id do cliente Jotaro e 1 é o id do Kit Fitness


INSERT INTO venda (id_venda, id_cliente_fk, id_admin_user_fk, id_pedido_fk, entrega_programada_fk, tipo_pagamento_fk, data_admissao, data_pagamento, data_conclusao)
VALUES
    (3, 5, 6, 60, 3, 5, NOW(), NOW(), NOW());

UPDATE venda
SET venda_realizada = TRUE
WHERE id_venda = 3; -- id da venda realizada


SELECT * FROM VENDA;

-- VENDA PARA CLIENTE COM PLANO


DELIMITER //

CREATE TRIGGER atualiza_preco_total_para_clientes_com_plano
BEFORE INSERT ON venda
FOR EACH ROW
BEGIN
    DECLARE preco_kit DOUBLE;

    -- Obter o preço do kit associado ao pedido
    SELECT preco INTO preco_kit
    FROM kit
    WHERE id_kit = (
        SELECT kit_fk
        FROM pedido
        WHERE id_pedido = NEW.id_pedido_fk
    );

    -- Verificar se o tipo de pagamento é um plano (ID 5)
    IF NEW.tipo_pagamento_fk = 5 THEN
        SET preco_kit = 0; -- Se for um plano, o preço do kit é zerado
    END IF;

    -- Calcular e atribuir o preço total na nova venda
    SET NEW.preco_kit = preco_kit;
END;
//

DELIMITER ;











/*===================================== TESTES DE SELEÇÕES ===================================================================*/
/* Selecionando os usuarios do tipo Admin*/
SELECT id, email
FROM usuario
WHERE role_user = 'admin'; -- filtro

/* Resgatando todos os usuarios do tipo Cliente*/
SELECT id, email
FROM usuario
WHERE role_user = 'cliente'; 

 /*Resgatando a quantidade de usuarios de cada tipo*/
SELECT role_user, COUNT(*) --  Conta a quantidade de cada tipo de usuario. COUNT(*) conta o número de linhas para cada valor único da coluna.
FROM usuario
GROUP BY role_user; -- Agrupa os resultados da consulta com base nos valores únicos da coluna role_user. Que seriam Admin e Cliente.

/* Uso de inner join com tabela associativa */
select * from usuario as u
inner join cliente on cliente.id_cliente=u.id
inner join pedido on pedido.id_cliente_fk=u.id;

/* Selecionando endereço do cliente pelo ID*/
select * from endereco as e inner join cliente as c where c.id_endereco_fk=e.id_endereco;

/*Resgate de todas as vendas entre duas datas*/
select * from venda as v where v.data_conclusao between '2024-04-11 12:00:00' and '2024-05-15 12:00:00';

/*retorne todos os tipos de kits os quais ja foram comprados anteriormente*/
select * from kit
inner join pedido on pedido.kit_fk=kit.id_kit
inner join cliente on cliente.id_cliente=pedido.id_cliente_fk;

/*retorne o total de clientes que estao dentro do PLANO*/

select count(c.id_cliente) from cliente as c where plano=true;

/*retorne a quantidade de clientes com nomes que contenham a letra A*/
select count(c.nome) from cliente as c where c.nome like'%a%';

/*busca com filtro nominal parcial*/
select c.id_cliente, c.nome from cliente as c where c.nome like'%ta%';

/*retorando em ordem descrescente todos os usuarios*/
SELECT *
FROM usuario
ORDER BY id
DESC;

/*resgatando as notas fiscais*/
select * from nota_fiscal;

/*resgatando todas as vendas confirmadas*/
select * from venda as v where v.venda_realizada>=1;




-- PLANEJAMENTO DO MÉTODO DE PLANOS


