```sql
    select p.patient_id, p.patient_name 
    from patient p
    where not exists(
        select 1 from appointment a
        where p.primary_phone <> a.contact_phone and p.patient_id = a.patient_id
    )
```

- En el segundo select obtengo todos los pacientes que usaron su número secundario para alguna cita, y en el select principal filtro todos los pacientes que no están en ese conjunto.