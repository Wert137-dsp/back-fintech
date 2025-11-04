drop sequence seq_usuario;
drop sequence seq_gasto;
drop sequence seq_conta;

create sequence seq_usuario
start with 1
increment by 1
nocache
nocycle;

create sequence seq_gasto
start with 1
increment by 1
nocache
nocycle;

create sequence seq_conta
start with 1
increment by 1
nocache
nocycle;

