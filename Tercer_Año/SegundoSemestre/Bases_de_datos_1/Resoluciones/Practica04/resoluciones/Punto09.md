# Ejercicio 9

```sql
    DELIMITER //

    CREATE PROCEDURE create_appointment(patient_id INT, doctor_id INT, appointment_duration INT, contact_phone VARCHAR(45), observations VARCHAR(150), medication_name VARCHAR(100))
    BEGIN
        DECLARE actual_date DATETIME;
        START TRANSACTION;
        
        SET actual_date = NOW();
        INSERT INTO appointment (patient_id, appointment_date, appointment_duration, contact_phone, observations)
        VALUES (patient_id, actual_date, appointment_duration, contact_phone, observations);

        INSERT INTO medical_review (patient_id, appointment_date, doctor_id)
        VALUES (patient_id, actual_date, doctor_id);

        INSERT INTO prescribed_medication (patient_id, appointment_date, medication_name)
        VALUES (patient_id, actual_date, medication_name);

        COMMIT;
    END;//

    DELIMITER ;
```
-  Por corrección del enunciado, se cambió el parámetro `appointment_address` por `observations` en la tabla appointment, ya que la tabla no cuenta con el atributo appointment_address.