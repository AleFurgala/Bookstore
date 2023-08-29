--liquibase formatted sql
--changeset AleFurgala:1

CREATE TRIGGER `create_klienci_triggers`
AFTER INSERT ON `klienci`
FOR EACH ROW
INSERT INTO klienci_log (imie, nazwisko,adres, id_klienci, id_admin, rodzaj_operacji)
VALUES (NEW.imie, NEW.nazwisko, NEW.adres, NEW.id, NEW.id_admin, 'CREATE');