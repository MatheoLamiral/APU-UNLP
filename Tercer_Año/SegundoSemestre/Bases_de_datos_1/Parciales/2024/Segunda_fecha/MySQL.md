# MySQL

```sql
DELIMITER //

CREATE TRIGGER nueva_oferta
AFTER UPDATE ON Oferta
FOR EACH ROW
BEGIN
    INSERT INTO Oferta_Historial (#oferente, #subasta, monto, fecha_oferta, fecha_auditoria)
    VALUES (OLD.#oferente, OLD.#subasta, OLD.monto, OLD.fecha_oferta, CURDATE());
END;//

DELIMITER ;
```