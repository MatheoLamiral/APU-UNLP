# Ejercicio 4

### Inciso a

```sql
    select patient_id, COUNT(doctor_id)
    from doctors_per_patients
    GROUP BY (patient_id)
```

### Inciso b

```sql
    select p.patient_name
    from patient p
    left join doctors_per_patients dp
    on dp.patient_id = p.patient_id
    where dp.doctor_id = NULL
```

### Inciso c

```sql
    select doctor_id, COUNT(patient_id)
    from doctors_per_patients 
    GROUP BY (doctor_id)
    having COUNT(patient_id) > 5
```