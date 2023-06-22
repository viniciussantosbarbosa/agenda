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

drop table clientes;

/******* RELACIONAMENTO DE TABELAS 1 - N *******/


-- decimal (10,2) tipo de dados (números não inteiros)
-- (10,2) (digitos, formatação de casas decimais)
-- timestamp default current_timestamp>> data e hora automática
-- foreign key(idcli) campo da tabela OS que será vinculado)
-- references clientes  
-- date (tipo de dados para trabalhar com datas)
create table servicos (
os int primary key auto_increment,
corveiculo varchar (30) not null, -- sempre tem aspas 
placaveiculo varchar (30) not null,
modeloveiculo varchar (30) not null,
tipodelavagem varchar (30) not null,
valor decimal (10.2) not null,
dataOS timestamp default current_timestamp,
observacao varchar (250),
idcli int not null,
idlav int,
usuario varchar(30) not null,
foreign key(idcli) references clientes (idcli),
foreign key(idlav) references lavadores (idlav)
);

select * from fornecedores;
describe fornecedores;

select * from produtos;
drop table produtos;
describe produtos;

create table produtos(
idprod int primary key auto_increment,
nomeproduto varchar (30) not null, -- sempre tem aspas 
descricao varchar (30) not null,
codigo varchar (30) not null,
loc varchar (30) not null,
quant varchar (30) not null,
tipo varchar (30) not null,
dataEntrada timestamp default current_timestamp,
dataSaida date,
quantminima varchar (250),
valor decimal (10.2) not null,
idfor int,
foreign key(idfor) references fornecedores (idfor)
);


create table servicos (
idfor int primary key auto_increment,
razao varchar (30) not null, -- sempre tem aspas 
email varchar (30) not null,
cnpj varchar (30) not null,
site varchar (30) not null,
cep decimal (10.2) not null,
endereco timestamp default current_timestamp,
numero varchar (250),
bairro int not null,
cidade int,
telefone varchar(30) not null,
complemento varchar(30) not null
);




update servicos set corveiculo='verde', placaveiculo='fbp-123', modeloveiculo='admin', tipolavagem='admin', valor=1,dataSaida ='20230523',observacao='admin',idcli='1',usuario='ze' where os =1;


insert into servicos (corveiculo,placaveiculo,modeloveiculo,tipolavagem,valor,dataSaida,observacao,idcli,usuario)
values ('azul','123','fusca','ducha',120,20230523,'avaria lateral',1,'eric');



describe servicos;

drop table servicos;

select * from servicos;	

create table lavadores (
idlav int primary key auto_increment,
nome varchar (30) not null, 
telefone varchar (30) not null,
rg varchar (30) not null
);

insert into lavadores (nome,telefone,rg)
values ('vini','123','321654');



select * from lavadores;

drop table tecnicos;

drop table lavadores;


insert into tecnicos(nome,apelido,telefone)
values ('ze','zezinho','123');







-- formatando a data (Brasil)
-- date_format() função para formatar a data
-- %d/%m/%Y dd/mm/aaaa | %d/%m/%y dd/mm/aa | %H:%i HH:mm
select os,equipamento,defeito,diagnostico,statusOS,
valor,date_format(dataOS,'%d/%m/%Y') as data_entrada,
date_format(dataSaida,'%d/%m/%Y') as data_saida,
idcli,iduser from servicos;


/** Relatórios (select) **/

-- faturamento
select sum(valor) as faturamento from servicos;

-- Clientes
select nome,telefone from clientes order by nome;

-- pendencias 
-- formato 1 
select * from servicos where statusOS = 'Aguardando mecânico';

-- formato 2
select os,corveiculo,placaveiculo,
date_format(dataOS,'%d/%m/%Y') as data_entrada,
idcli from servicos
where statusOS = 'Aguardando mecânico';

-- formato 3
select os,corveiculo,placaveiculo,
date_format(dataOS,'%d/%m/%Y') as data_entrada,
id from servicos
inner join clientes 
on servicos.idcli = clientes.idcli
where statusOS = 'Aguardano mecânico';

-- rascunho 
select * from servicos inner join clientes 
on servicos.idcli = clientes.idcli
where statusOS = 'Aguardano mecânico'; 

-- relatório de serviços entregues
select servicos.os,servicos.moto,servicos.defeito,
servicos.diagnostico,mecanicos.nome as mecânico,
servicos.valor,
date_format(servicos.dataOS,'%d/%m/%Y - %H:%i') as data_entrada,
date_format(servicos.dataSaida,'%d/%m/%Y') as data_saida,
clientes.nome as cliente, clientes.fone
from servicos
inner join clientes
on servicos.idcli = clientes.idcli
inner join mecanicos
on servicos.idmec = mecanicos.idmec
where statusOS = 'Entregue';

/** Impressão da OS **/
select servicos.os,
clientes.nome as cliente, clientes.telefone
from servicos
inner join clientes
on servicos.idcli = clientes.idcli
inner join lavadores
on servicos.idlav = lavadores.idlav
where os = 1;

select servicos.os,
servicos.usuario as usuário,
servicos.corveiculo,servicos.placaveiculo,servicos.modeloveiculo as status_OS,servicos.tipodelavagem,
lavadores.nome as lavadores,
servicos.valor,
clientes.nome as cliente, clientes.telefone
from servicos
inner join clientes
on servicos.idcli = clientes.idcli
inner join lavadores
on servicos.idlav = lavadores.idlav
where os = 1;

select servicos.os,
servicos.placaveiculo,servicos.modeloveiculo,servicos.corveiculo,servicos.tipodelavagem,
servicos.valor,
clientes.nome as cliente, clientes.telefone
from servicos
inner join clientes
on servicos.idcli = clientes.idcli
inner join lavadores
on servicos.idlav = lavadores.idlav
where os = 1;




-- obtendo o valor do PK último registro adicionado

select max(os) from servicos;
select max(idcli) from clientes;






