--liquibase formatted sql
--changeset AleFurgala:1

CREATE TRIGGER `create_zamowienia_triggers`
AFTER INSERT ON `zamowienia`
FOR EACH ROW
INSERT INTO zamowienia_log (id_klienci, id_ksiazki, data_zamowienia, id_admin, rodzaj_operacji, data)
VALUES (NEW.id_klienci, NEW.id_ksiazki, NEW.data, NEW.id_admin, 'CREATE', NOW());

CREATE TRIGGER `delete_zamowienia_triggers`
AFTER DELETE ON `zamowienia`
FOR EACH ROW
INSERT INTO zamowienia_log (id_klienci, id_ksiazki, data_zamowienia, id_admin, rodzaj_operacji, data)
VALUES (OLD.id_klienci, OLD.id_ksiazki, OLD.data, OLD.id_admin, 'DELETE', NOW());

CREATE TRIGGER `update_zamowienia_triggers`
AFTER UPDATE ON `zamowienia`
FOR EACH ROW
INSERT INTO zamowienia_log (id_klienci, id_ksiazki, data_zamowienia, id_admin, rodzaj_operacji, data)
VALUES (OLD.id_klienci, OLD.id_ksiazki, OLD.data, OLD.id_admin, 'UPDATE', NOW());