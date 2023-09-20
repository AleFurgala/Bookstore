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

INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 1 , 10 , '2023-01-05 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 2 , 10 , '2023-01-14 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 3 , 8 , '2023-01-28 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 4 , 7 , '2023-02-01 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 5 , 8 , '2023-02-13 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 6 , 5 , '2023-02-24 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 7 , 4 , '2023-03-04 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 8 , 3 , '2023-03-07 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 9 , 5 , '2023-03-12 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 10 , 1 , '2023-04-20 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 10 , 10 , '2023-04-21 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 9 , 1 , '2023-04-29 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 8 , 2 , '2023-05-03 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 7 , 5 , '2023-05-13 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 6 , 4 , '2023-05-25 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 5 , 4 , '2023-05-30 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 4 , 6 , '2023-06-14 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 3 , 10 , '2023-06-17 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 2 , 3 , '2023-06-23 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 1 , 9 , '2023-07-17 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 5 , 10 , '2023-07-19 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 6 , 6 , '2023-07-20 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 4 , 4 , '2023-08-20 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 7 , 6 , '2023-08-23 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 3 , 3 , '2023-08-29 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 8 , 8 , '2023-08-30 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 2 , 3 , '2023-09-01 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 9 , 10 , '2023-09-13 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 1 , 8 , '2023-09-27 19:36:53');
INSERT INTO zamowienia (id, id_klienci, id_ksiazki, data) VALUES(NULL, 10 , 4 , '2023-09-20 19:36:53');


