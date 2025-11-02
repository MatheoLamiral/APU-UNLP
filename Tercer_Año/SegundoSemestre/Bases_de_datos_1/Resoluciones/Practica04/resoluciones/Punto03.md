```sql
    create view doctors_per_patients as(
        select patient.patient_id, doctor.doctor_id
        from patient 
        inner join doctor
        on patient_city = doctor_city
    )
``` 
```sql
    create view doctors_per_patients as(
        select patient.patient_id, doctor.doctor_id
        from patient p, doctor d 
        where p.patient_city = d.doctor_city
    )
```

- La segunda opción es mucho más costosa en terminos de eficiencia, normalmente se usa inner join