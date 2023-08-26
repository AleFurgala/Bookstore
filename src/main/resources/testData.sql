
INSERT INTO  klienci (imie, nazwisko, adres) VALUES('Jan' ,'Nowak', 'Rzeszow');
INSERT INTO  klienci (imie, nazwisko, adres) VALUES('Krystyna' , 'Kowalska' , 'Warszawa');

INSERT INTO konta_administratorow (login, haslo, nazwa_uzytkownika) VALUES('admin','zJc4Y8ajxQ5hpxa4RtRi6g==','admin');

INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('Water' , 'Paula Hawkins' , 23 , 2);
INSERT INTO ksiazki (tytul, autor, cena, ilosc) VALUES('xyz' , 'abc' , 30 , 3);

INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(1 , 1 , '24072023');
INSERT INTO zamowienia (id_klienci, id_ksiazki, data) VALUES(2 , 2 , '25072023');