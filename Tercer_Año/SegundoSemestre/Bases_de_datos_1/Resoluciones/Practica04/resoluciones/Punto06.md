# Ejercicio 6

```sql
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

        declare exit handler for sqlexception
        begin
            rollback;
        end;

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
```


