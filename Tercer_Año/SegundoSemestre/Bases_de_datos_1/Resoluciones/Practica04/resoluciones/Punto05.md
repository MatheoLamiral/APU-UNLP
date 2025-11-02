# Ejercicio 5

```sql
    create table appointments_per_patient(
        idApP integer (11) auto_increment primary key,
        id_patient integer (11) not null, 
        foreign key (id_patient) references patient(patient_id),
        count_appointments integer (11),
        last_update datetime,
        user varchar (50)
    )
```