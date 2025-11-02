-- Cursor para insertar las appointments de un patient
-- en la tabla appointments_per_patient
DELIMITER //

create procedure insert_count_appointments ()
begin

    declare aux_patient_id integer;
    declare aux_date datetime;
    declare aux_user varchar(50);
    declare aux_count integer;
    declare fin integer default 0;

    declare patient_cursor cursor for
        select p.patient_id, COUNT(*)
        from appointment p
        group by (p.patient_id);

    declare continue handler for not found set fin = 1;

    start transaction;

    open patient_cursor;

    loop_cursor:loop
        fetch next from patient_cursor into aux_patient_id, aux_count;
        if fin = 1 then
            leave loop_cursor;
        end if;
        insert into appointments_per_patient(id_patient, count_appointments, last_update, user)
        values (aux_patient_id, aux_count, now(), current_user());
    end loop;
    close patient_cursor;
    commit;
end//

DELIMITER ;

--crea un appointment junto con la medical_review y prescribed_medication correspondiente 
--a los parametros proporcionados

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