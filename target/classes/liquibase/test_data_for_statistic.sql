--liquibase formatted sql
--changeset AleFurgala:1


INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Teofil' ,'Wilczyński', 'Rzeszow');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Wanda' , 'Wyszyńska' , 'Warszawa');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Izabla' , 'Siarzewska' , 'Łódź');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Tomasz' , 'Mazurek' , 'Szczecin');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Zbigniew' , 'Stonoga' , 'Bydgoszcz');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Marek' , 'Mostowiak' , 'Grabownica');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Aldona' , 'Lipińska' , 'Katowice');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Irena' , 'Wojciechowska' , 'Poznań');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Krystyna' , 'Ciepła' , 'Wrocław');
INSERT INTO klienci (id, imie, nazwisko, adres) VALUES(NULL, 'Grażyna' , 'Nowak' , 'Gdańsk');


INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Morderstwo' , 'Agata Christie' , 23 , 15);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Harry Poter' , 'J.K.Rowling' , 30 , 16);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Zemsta' , 'Aleksander Fredro' , 45 , 14);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Dziady' , 'Adam Mickiewicz' , 15 , 9);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Krzyżacy' , 'Henryk Sienkiewicz' , 20 , 6);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Katarynka' , 'Bolesław Prus' , 25 , 8);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Anna Karenina' , 'Lew Tołstoj' , 40 , 10);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Zbrodnia i Kara' , 'Fiodor Dostojewski' , 45 , 5);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Duma i uprzedzenie' , 'Jane Austin' , 30 , 10);
INSERT INTO ksiazki (id, tytul, autor, cena, ilosc) VALUES(NULL, 'Władca Pierścieni' , 'J.R.R. Tolkien' , 35 , 13);

INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 1 , 10 , '24072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 2 , 10 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 3 , 8 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 4 , 7 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 5 , 8 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 6 , 5 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 7 , 4 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 8 , 3 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 9 , 5 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 10 , 1 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 10 , 10 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 9 , 1 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 8 , 2 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 7 , 5 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 6 , 4 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 5 , 4 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 4 , 6 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 3 , 10 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 2 , 3 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 1 , 9 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 5 , 10 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 6 , 6 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 4 , 4 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 7 , 6 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 3 , 3 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 8 , 8 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 2 , 3 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 9 , 10 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 1 , 8 , '25072023');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 10 , 4 , '25072023');


