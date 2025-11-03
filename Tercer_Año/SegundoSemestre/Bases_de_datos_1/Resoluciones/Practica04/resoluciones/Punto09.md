# Ejercicio 9

```sql
    DELIMITER //

    create procedure create_appointment(patient_id in integer, doctor_id in integer, appointment_duration in integer, contact_phone in varchar(45), observations in varchar(150), medication_name in varchar(100))
    begin 
        declare actual_date datetime;

        declare continue handler for sqlexception
        begin
            rollback;
        end;

        start transaction;

        set actual_date = now();
        insert into appointment (patient_id, appointment_date, appointment_duration, contact_phone, observations)
        values (patient_id, actual_date, appointment_duration, contact_phone, observations);

        insert into medical_review (patient_id, appointment_date, doctor_id)
        values (patient_id, actual_date, doctor_id);

        insert into prescribed_medication (patient_id, appointment_date, medication_name)
        values (patient_id, actual_date, medication_name);

        commit;
    end//

    DELIMITER ;
```
-  Por corrección del enunciado, se cambió el parámetro `appointment_address` por `observations` en la tabla appointment, ya que la tabla no cuenta con el atributo appointment_address.