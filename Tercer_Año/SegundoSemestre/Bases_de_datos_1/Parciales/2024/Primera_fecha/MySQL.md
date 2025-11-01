```sql
    // DELIMITER

    CREATE PROCEDURE insertar_siniestro (IN #poliza INTEGER, IN descripcion_siniestro VARCHAR(255), IN #taller INTEGER, IN #perito INTEGER, IN nombre_perito VARCHAR(100), IN apellido_perito VARCHAR(100), IN evaluacion VARCHAR(255))
    BEGIN
        DECLARE fecha_actual DATE;
        DECLARE id_siniestro INT;
        START TRANSACTION;
        SET fecha_actual = CURDATE();
        -- Inserto el siniestro
        INSERT INTO Siniestro (fecha_denuncia, descripcion_siniestro, #taller, #poliza) 
        VALUES (fecha_actual, descripcion_siniestro, #taller, #poliza);
        -- Obtengo el id del siniestro recién insertado
        SET id_siniestro = LAST_INSERT_ID();
        -- Inserto el perito
        INSERT INTO Perito (#perito, nombre, apellido)
        VALUES (#perito, nombre_perito, apellido_perito);
        -- Inserto el peritaje
        INSERT INTO Peritaje (fecha_peritaje, #perito, #siniestro, evaluacion)
        VALUES (fecha_actual, #perito, id_siniestro, evaluacion);
        COMMIT;
    END;//

    DELIMITER ;
```