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