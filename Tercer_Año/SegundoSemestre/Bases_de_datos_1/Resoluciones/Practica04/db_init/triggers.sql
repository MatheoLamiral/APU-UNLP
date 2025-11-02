-- Actualiza la tabla appointments_per_patient cuando al insertar en la tabla appointment
DELIMITER //

create trigger agregar_appointment
after insert on appointment
for each row 
begin
    if exists (
        select 1 
        from appointment
        inner join appointments_per_patient
        on id_patient = new.patient_id
    )
    then
        update appointments_per_patient set 
        count_appointments = count_appointments +1, 
        last_update = now(),
        user = current_user()
        where id_patient = new.patient_id;
    else
        insert into appointments_per_patient 
			(id_patient, count_appointments, last_update, user) 
        values
			(new.patient_id, 1, now(), current_user());
    end if;
end;//

DELIMITER ;