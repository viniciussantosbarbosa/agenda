/**
 * Radical Motos
 * @author Professor José de Assis
 */

drop database dbradicalmotos;
create database dbradicalmotos;
show databases;
use dbradicalmotos;



-- unique não permite valore duplicados
create table usuarios (
	iduser int primary key auto_increment,
	nome varchar(30) not null,
    login varchar(20) not null unique,
    senha varchar(250) not null,
    perfil varchar(5) not null
);


describe usuarios;

-- md5() gera um hash (criptografia)
insert into usuarios(nome,login,senha,perfil)
values ('Administrador','admin', md5('admin'),'admin');

select * from usuarios;

-- autenticar um usuário (logar)
select *from usuarios where login= 'admin' and senha = md5('admin'); 


insert into usuarios(nome,login,senha)
values ('Administrador','admin','admin');

-- md5() gera um hash (criptografia)
insert into usuarios(nome,login,senha)
values ('Paloma Oliveira','paloma',md5('123456'));
insert into usuarios(nome,login,senha)
values ('Kauan Oliveira','k',md5('123456'));

-- gerando um erro relacionado a unique
insert into usuarios(nome,login,senha)
values ('Paloma Duarte','p',md5('123@senac'));

-- CRUD [read]

select * from usuarios;			

-- autenticar um usuário (logar)
select * from usuarios where login = 'vini' and senha = '123';

update usuarios set nome = 'administrador', login='admin', senha='admin', perfil='admin' where iduser =1;

-- pesquisa avançada (semelheante a busca do google)
select * from usuarios where nome like '%' order by nome;	

select * from clientes where nome like '%' order by nome;


update usuarios set perfil='user' where iduser =14;

-- tabela de clientes 

drop table clientes;

create table clientes (
idcli int primary key auto_increment,
nome varchar (30) not null,
telefone varchar(10) not null,
endereco varchar(30) not null,
cidade varchar(30) not null,
cep varchar(30) not null,
bairro varchar(30) not null,
uf varchar(30) null,
complemento varchar(30) not null,
numero varchar(30) null
); 	


select * from clientes;		

describe clientes;

/******* RELACIONAMENTO DE TABELAS 1 - N *******/


-- decimal (10,2) tipo de dados (números não inteiros)
-- (10,2) (digitos, formatação de casas decimais)
-- timestamp default current_timestamp>> data e hora automática
-- foreign key(idcli) campo da tabela OS que será vinculado)
-- references clientes  
-- date (tipo de dados para trabalhar com datas)
create table servicos (
os int primary key auto_increment,
corveiculo varchar (30) not null,
placaveiculo varchar (30) not null,
modeloveiculo varchar (30) not null,
tipolavagem varchar(30),
valor decimal (10.2),
observacao varchar (250),
idcli int not null,
dataOS timestamp default current_timestamp,
dataEntrega date,
foreign key(idcli) references clientes (idcli)
);

describe servicos;

drop table servicos;

select * from servicos;	

insert into servicos (corveiculo,placaveiculo,modeloveiculo,tipolavagem,valor,observacao,idcli,dataEntrega,usuario)
values ('azul','123','fusca','ducha',120,'avaria lateral',1,20230523,'vini');



-- formantando a data (Brasil)
-- date_format() função para formatar a data 
-- %d/%m/%y ddmm/aaaa | %d/%m/%y dd/mm/aa | %H:%i HH:mm







