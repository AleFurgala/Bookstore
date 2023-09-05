--liquibase formatted sql
--changeset AleFurgala:1

CREATE TABLE klienci_log(
id INT AUTO_INCREMENT,
imie VARCHAR(255),
nazwisko VARCHAR(255),
adres VARCHAR(255),
id_klienci INT,
id_admin INT,
rodzaj_operacji VARCHAR(255),
data VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE ksiazki_log(
id INT AUTO_INCREMENT,
tytul VARCHAR(255),
autor VARCHAR(255),
id_ksiazki INT,
id_admin INT,
rodzaj_operacji VARCHAR(255),
data VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE zamowienia_log(
id INT AUTO_INCREMENT,
id_klienci INT,
id_ksiazki INT,
data_zamowienia VARCHAR(255),
id_admin INT,
rodzaj_operacji VARCHAR(255),
data VARCHAR(255),
PRIMARY KEY (id)
);