--liquibase formatted sql
--changeset AleFurgala:1


INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Jan' ,'Nowak', 'Rzeszow');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Krystyna' , 'Kowalska' , 'Warszawa');

INSERT INTO konta_administratorow (id, login, haslo, nazwa_uzytkownika, email, rodzaj_konta) VALUES(NULL, 'admin','zJc4Y8ajxQ5hpxa4RtRi6g==','admin','test@test.com', 'admin');

INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Water' , 'Paula Hawkins' , 23 , 2);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'xyz' , 'abc' , 30 , 3);

INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 1 , 1 , '24072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 2 , 2 , '25072023');