--liquibase formatted sql
--changeset AleFurgala:1

CREATE TRIGGER `create_ksiazki_triggers`
AFTER INSERT ON `ksiazki`
FOR EACH ROW
INSERT INTO ksiazki_log (tytul, autor, id_ksiazki, id_admin, rodzaj_operacji, data)
VALUES (NEW.tytul, NEW.autor, NEW.id, NEW.id_admin, 'CREATE', NOW());

CREATE TRIGGER `delete_ksiazki_triggers`
AFTER DELETE ON `ksiazki`
FOR EACH ROW
INSERT INTO ksiazki_log (tytul, autor, id_ksiazki, id_admin, rodzaj_operacji, data)
VALUES (OLD.tytul, OLD.autor, OLD.id, OLD.id_admin, 'DELETE', NOW());

CREATE TRIGGER `update_ksiazki_triggers`
AFTER UPDATE ON `ksiazki`
FOR EACH ROW
INSERT INTO ksiazki_log (autor, tytul, id_ksiazki, id_admin, rodzaj_operacji, data)
VALUES (OLD.tytul, OLD.autor, OLD.id, OLD.id_admin, 'UPDATE', NOW());