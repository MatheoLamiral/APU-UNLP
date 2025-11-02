# Ejercicio 11

```sql
    explain select count(a.patient_id) 
    from 
        appointment a, patient p, doctor d, medical_review mr
    where 
        a.patient_id= p.patient_id 
        and a.patient_id= mr.patient_id 
        and a.appointment_date=mr.appointment_date
        and mr.doctor_id = d.doctor_id
        and d.doctor_specialty  = 'Cardiology'
        and p.patient_city =  'Rosario'
```

- El resultado de la ejecución del EXPLAIN es el siguiente:    

| id | select_type | table | partitions | type   | possible_keys       | key      | key_len | ref                                                            | rows | filtered | Extra        |
|----|--------------|--------|-------------|--------|---------------------|----------|----------|-----------------------------------------------------------------|------|-----------|---------------|
| 1  | SIMPLE       | mr     | NULL        | index  | PRIMARY,doctor_id   | doctor_id | 4       | NULL                                                           | 1    | 100.00    | Using index   |
| 1  | SIMPLE       | p      | NULL        | eq_ref | PRIMARY             | PRIMARY  | 4        | appointments.mr.patient_id                                     | 1    | 10.00     | Using where   |
| 1  | SIMPLE       | d      | NULL        | eq_ref | PRIMARY             | PRIMARY  | 4        | appointments.mr.doctor_id                                      | 1    | 10.00     | Using where   |
| 1  | SIMPLE       | a      | NULL        | eq_ref | PRIMARY             | PRIMARY  | 9        | appointments.mr.patient_id,appointments.mr.a...                | 1    | 100.00    | Using index   |


### Inciso a: ¿Qué atributos del plan de ejecución encuentra relevantes para evaluar la performance de la consulta?

Los atributos relevantes del plan de ejecución para evaluar la performance de la consulta incluyen:
- **type**: describe como se unen las tablas (joined). En la salida con formato JSON, estos se encuentran como valores de la propiedad `access_type`.
- **key**: muestra el índice que se está utilizando para acceder a la tabla.
- **rows**: número estimado de filas que se tendrán que leer para encontrar las filas coincidentes.
- **Extra**: contiene información adicional sobre cómo MySQL resuelve la consulta.
- **filtered**: indica el porcentaje de filas que se espera que pasen los filtros de la consulta.
- notar que `rows` muestra el número estimado de filas examinadas y `rows x filtered` muestra el número de filas que se unen con la siguiente tabla. Por ejemplo, si `rows` es 1000 y `filtered` es 10.00, entonces se espera que 100 filas pasen a la siguiente etapa de la consulta.

### Inciso b: Observe en particular el atributo type ¿cómo se están aplicando los JOIN entre las tablas involucradas?

Los JOIN entre las tablas involucradas se están aplicando utilizando:
- **eq_ref**: Una fila es leída de la tabla para cada combinación de filas de las tablas anteriores. Esto es el mejor tipo de join posible. Es usado cuando todas las partes de un índice son usadas por el join y el índice es un índice PK o unique. Normalmente se da con el operador `=`.
- **index**: Se lee el índice completo para encontrar las filas coincidentes. Esto es más eficiente que un full table scan (en el full table scan se lee toda la tabla), pero menos eficiente que otros tipos de joins como `ref` (usa el índice para buscar filas coincidentes) o `eq_ref`.

### Inciso c: Según lo que observó en los puntos anteriores, ¿qué mejoras se pueden realizar para optimizar la consulta? 

### Inciso d: Aplique las mejoras propuestas y vuelva a analizar el plan de ejecución. ¿Qué cambios observa?