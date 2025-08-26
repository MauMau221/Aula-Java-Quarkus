-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


insert into book (id, titulo, autor, editora, anoLancamento, estaDisponivel) values (1, 'Harry', 'Potter', 'Sei la', 1998, true);
insert into book (id, titulo, autor, editora, anoLancamento, estaDisponivel) values (2, 'Homem de Ferro', 'Marvel', 'Marvel', 1990, true);
insert into book (id, titulo, autor, editora, anoLancamento, estaDisponivel) values (3, 'O caçador de pipas', 'Kabu', 'Raiassu', 1998, true);
insert into book (id, titulo, autor, editora, anoLancamento, estaDisponivel) values (4, 'O poço', 'ele lá ', 'alguma', 1198, true);