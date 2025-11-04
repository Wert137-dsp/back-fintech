drop table t_usuario cascade constraint purge ;
drop table t_gasto cascade constraint purge ;
drop table t_conta cascade constraint purge;

create table T_USUARIO(
    id number(18) not null,
    nome varchar2(50) not null,
    descricao varchar2(255),
    email varchar2(50) not null,
    senha varchar2(255) not null,
    data_nascimento date not null,
    data_registro timestamp,
    data_atualizacao timestamp,
    
    constraint pk_usuario primary key (id)
    
);

create table T_GASTO(
    
    id number(18) not null,
    nome varchar2(50) not null,
    descricao varchar2(255),
    tipo varchar2(30) not null,
    valor number(10, 2) not null,
    data_gasto date not null,
    data_registro timestamp,
    data_atualizacao timestamp,
    id_usuario number(18) not null,
    
    constraint pk_gasto primary key (id),
    constraint fk_usuario_gasto foreign key(id_usuario)
        references t_usuario(id)
);

create table T_CONTA(
    id number(18) not null,
    num_agencia varchar2(20) not null,
    num_conta varchar(20) not null,
    tipo_conta varchar2(50) not null,
    saldo_atual number(10,2) not null,
    data_abertura date not null,
    data_registro timestamp,
    data_atualizacao timestamp,
    id_usuario number(18) not null,
    
    constraint pk_conta primary key (id),
    constraint fk_usuario_conta foreign key (id_usuario)
        references t_usuario(id)
);