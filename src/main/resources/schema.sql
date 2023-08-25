--liquibase formatted sql
--changeset AleFurgala:1

CREATE TABLE klienci (id INT AUTO_INCREMENT, imie VARCHAR(255), nazwisko VARCHAR(255), adres VARCHAR(255), PRIMARY KEY (id));
CREATE TABLE konta_administratorow (id INT AUTO_INCREMENT, login VARCHAR(255), haslo VARCHAR(255), nazwa_uzytkownika VARCHAR(255), PRIMARY KEY (id));
CREATE TABLE ksiazki (id INT AUTO_INCREMENT, tytul VARCHAR(255), autor VARCHAR(255), cena INT, ilosc INT, PRIMARY KEY (id));
CREATE TABLE zamowienia (id INT AUTO_INCREMENT, id_klienci INT, id_ksiazki INT, data VARCHAR(255), PRIMARY KEY (id), FOREIGN KEY (id_klienci) REFERENCES klienci, FOREIGN KEY (id_ksiazki) REFERENCES ksiazki);


