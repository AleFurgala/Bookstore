--liquibase formatted sql
--changeset AleFurgala:1

CREATE TABLE klienci(
id INT AUTO_INCREMENT,
imie VARCHAR(255),
nazwisko VARCHAR(255),
adres VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE konta_administratorow(
id INT AUTO_INCREMENT,
login VARCHAR(255),
haslo VARCHAR(255),
nazwa_uzytkownika VARCHAR(255),
rodzaj_konta VARCHAR(255),
PRIMARY KEY (id)
);

CREATE TABLE ksiazki(
id INT AUTO_INCREMENT,
tytul VARCHAR(255),
autor VARCHAR(255),
cena INT,
ilosc INT,
PRIMARY KEY (id)
);

CREATE TABLE zamowienia(
id INT AUTO_INCREMENT,
id_klienci INT,
id_ksiazki INT,
data VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id_klienci) REFERENCES klienci(id),
FOREIGN KEY (id_ksiazki) REFERENCES ksiazki(id)
);

--changeset AleFurgala:2

ALTER TABLE klienci ADD id_admin INT;
ALTER TABLE `klienci` ADD FOREIGN KEY (`id_admin`) REFERENCES `konta_administratorow`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ksiazki ADD id_admin INT;
ALTER TABLE `ksiazki` ADD FOREIGN KEY (`id_admin`) REFERENCES `konta_administratorow`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE zamowienia ADD id_admin INT;
ALTER TABLE `zamowienia` ADD FOREIGN KEY (`id_admin`) REFERENCES `konta_administratorow`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;


