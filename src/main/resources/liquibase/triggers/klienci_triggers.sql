--liquibase formatted sql
--changeset AleFurgala:1

CREATE TRIGGER `create_klienci_triggers`
AFTER INSERT ON `klienci`
FOR EACH ROW
INSERT INTO klienci_log (imie, nazwisko,adres, id_klienci, id_admin, rodzaj_operacji, data)
VALUES (NEW.imie, NEW.nazwisko, NEW.adres, NEW.id, NEW.id_admin, 'CREATE', NOW());

CREATE TRIGGER `delete_klienci_triggers`
AFTER DELETE ON `klienci`
FOR EACH ROW
INSERT INTO klienci_log (imie, nazwisko,adres, id_klienci, id_admin, rodzaj_operacji, data)
VALUES (OLD.imie, OLD.nazwisko, OLD.adres, OLD.id, OLD.id_admin, 'DELETE', NOW());

CREATE TRIGGER `update_klienci_triggers`
AFTER UPDATE ON `klienci`
FOR EACH ROW
INSERT INTO klienci_log (imie, nazwisko,adres, id_klienci, id_admin, rodzaj_operacji, data)
VALUES (OLD.imie, OLD.nazwisko, OLD.adres, OLD.id, OLD.id_admin, 'UPDATE', NOW());