# Ejercicio 11

**ORGANIZACION_EVENTOS**(#evento, fecha_evento, motivo_evento, #salon, nombre_salon, #grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador, #persona_staff, nombre_persona_staff, telefono_persona_staff, rol_persona_staff)

## Dependencias funcionales

DF1: `#evento -> fecha_evento, motivo_evento, #salon, #grupo`  
DF2: `#salon -> nombre_salon`    
DF3: `#grupo -> nombre_grupo, nro_integrantes_grupo, #organizador`  
DF4: `#organizador -> nombre_organizador, telefono_organizador, años_exp_organizador`  
DF5: `#organizador, fecha_evento -> #grupo`  
DF6: `#persona_staff -> nombre_persona_staff, telefono_persona_staff, rol_persona_staff`  

**CC: {#evento, #persona_staff}**

## Análisis de BCNF

**ORGANIZACION_EVENTOS** no está en BCNF ya que existe al menos una DF no trivial, por ej DF2, cuyo determinante (#salon) no es superclave del esquema

- Por lo tanto, particiono por DF2:
  - **E1)** (**#salon**, nombre_salon)
  - **E2)** (**#evento**, fecha_evento, motivo_evento, #salon, #grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador, **#persona_staff**, nombre_persona_staff, telefono_persona_staff, rol_persona_staff)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que E1 ∩ E2 es {#salon}, que es clave en E1.
  - No se pierden DFs dado que por validacion simple, DF2 vale en E1 y DF1, DF3, DF4, DF5, DF6 valen en E2

- **E1** está en BCNF ya que solo vale la DF2 donde su determinante {#salon} es superclave del esquema.
- **E2** no está en BCNF porque existe al menos una DF no trivial, por ej DF4, cuyo determinante (#organizador) no es superclave del esquema

- Por lo tanto, particiono por DF4:
  - **E3)** (**#organizador**, nombre_organizador, telefono_organizador, años_exp_organizador)
  - **E4)** (**#evento**, fecha_evento, motivo_evento, #salon, #grupo, nombre_grupo, nro_integrantes_grupo, #organizador, **#persona_staff**, nombre_persona_staff, telefono_persona_staff, rol_persona_staff)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que E3 ∩ E4 es {#organizador}, que es clave en E3.
  - No se pierden DFs dado que por validacion simple, DF4 vale en E3 y DF1, DF3, DF5, DF6 valen en E4

- **E3** está en BCNF ya que solo vale la DF4 donde su determinante {#organizador} es superclave del esquema.
- **E4** no está en BCNF porque existe al menos una DF no trivial, por ej DF6, cuyo determinante (#persona_staff) no es superclave del esquema

- Por lo tanto, particiono por DF6:
  - **E5)** (**#persona_staff**, nombre_persona_staff, telefono_persona_staff, rol_persona_staff)
  - **E6)** (**#evento**, fecha_evento, motivo_evento, #salon, #grupo, nombre_grupo, nro_integrantes_grupo, #organizador, **#persona_staff**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que E5 ∩ E6 es {#persona_staff}, que es clave en E5.
  - No se pierden DFs dado que por validacion simple, DF6 vale en E5 y DF1, DF3, DF5 valen en E6

- **E5** está en BCNF ya que solo vale la DF6 donde su determinante {#persona_staff} es superclave del esquema.
- **E6** no está en BCNF porque existe al menos una DF no trivial, por ej DF1, cuyo determinante (#evento) no es superclave del esquema

- Por lo tanto, particiono por DF1:
  - **E7)** (**#evento**, fecha_evento, motivo_evento, #salon, #grupo)
  - **E8)** (**#evento**, nombre_grupo, nro_integrantes_grupo, #organizador, **#persona_staff**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que E7 ∩ E6 es {#evento}, que es clave en E7.
  - Por validación simple, DF1 vale en E7. Pero no podemos afirmar, por validación simple, que DF3 y DF5 valen en E6, ya que sus determinantes (#grupo y #organizador respectivamente) no están presentes en E6. Por lo tanto, debemos verificar mediante el algoritmo.
  - Algoritmo para analizar pérdida de DFs:
    ```
        Res = x
        Mientras Res cambia
            Para i = 1 to cant_de_particiones_realizadas
                Res = Res ∪ ((Res ∩ Ri)+ ∩ Ri)
    ```
  - Aplición del algoritmo a DF3:
    ```
        Res = {#grupo}
        Mientras Res cambia
        //Primera Iteración
        i = 1
        Res = {#grupo} ∪ (({#grupo} ∩ E1)+ ∩ E1)
            = {#grupo} ∪ (∅ ∩ E1)
            = {#grupo} ∪ ∅
            = {#grupo}
        i = 3
        Res = {#grupo} ∪ (({#grupo} ∩ E3)+ ∩ E3)
            = {#grupo} ∪ (∅ ∩ E3)
            = {#grupo} ∪ ∅
            = {#grupo}
        i = 5
        Res = {#grupo} ∪ (({#grupo} ∩ E5)+ ∩ E5)
            = {#grupo} ∪ (∅ ∩ E5)
            = {#grupo} ∪ ∅
            = {#grupo}
        i = 7
        Res = {#grupo} ∪ (({#grupo} ∩ E7)+ ∩ E7)
            = {#grupo} ∪ ({#grupo}+ ∩ E7)
            = {#grupo} ∪ ({#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∩ E7)
            = {#grupo} ∪ ∅
            = {#grupo}
        i = 8
        Res = {#grupo} ∪ (({#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∩ E8)+ ∩ E8)
            = {#grupo} ∪ ({nombre_grupo, nro_integrantes_grupo, #organizador} ∩ E8)
            = {#grupo} ∪ {nombre_grupo, nro_integrantes_grupo, #organizador}
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador}
        //Res cambió, repito el while
        //Segunda Iteración
        i = 1
        Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∪ (({#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∩ E1)+ ∩ E1)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∪ (∅ ∩ E1)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∪ ∅
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador}
        i = 3
        Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∪ (({#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∩ E3)+ ∩ E3)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∪ ({#organizador}+ ∩ E3)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∪ ({#organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∩ E3)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∪ {#organizador, nombre_organizador, telefono_organizador, años_exp_organizador}
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador}
        i = 5
        Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∪ (({#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∩ E5)+ ∩ E5)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∪ (∅ ∩ E5)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∪ ∅
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador}
        i = 7
        Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∪ (({#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∩ E7)+ ∩ E7)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∪ (∅ ∩ E7)
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∪ ∅
            = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador}
    ```
  - Algoritmo de clausura de un conjunto de atributos:
    ```
        Res = {#grupo}
        Mientras Res cambia
            Para cada DF A -> B
                Si A ⊆ Res
                    Res = Res ∪ B

        //Primera Iteración
        DF1: #evento -> fecha_evento, motivo_evento, #salon, #grupo
                Si #evento ⊆ Res
                    No se cumple
        //Segunda Iteración
        DF2: #salon -> nombre_salon
            Si #salon ⊆ Res
                No se cumple
        //Tercera Iteración
        DF3: #grupo -> nombre_grupo, nro_integrantes_grupo, #organizador
            Si #grupo ⊆ Res
                Res = {#grupo} ∪ {nombre_grupo, nro_integrantes_grupo, #organizador}
                Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador}
        //Cuarta Iteración
        DF4: #organizador -> nombre_organizador, telefono_organizador, años_exp_organizador
            Si #organizador ⊆ Res
                Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador} ∪ {nombre_organizador, telefono_organizador, años_exp_organizador}
                Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador}
        //Quinta Iteración
        DF5: #organizador, fecha_evento -> #grupo
            Si #organizador, fecha_evento ⊆ Res
                No se cumple
        //Sexta Iteración
        DF6: #persona_staff -> nombre_persona_staff, telefono_persona_staff, rol_persona_staff
            Si #persona_staff ⊆ Res
                No se cumple
        //Res cambió, repito el while
        //Primera Iteración
        DF1: #evento -> fecha_evento, motivo_evento, #salon, #grupo
                Si #evento ⊆ Res
                    No se cumple
        //Segunda Iteración
        DF2: #salon -> nombre_salon
            Si #salon ⊆ Res
                No se cumple
        //Tercera Iteración
        DF3: #grupo -> nombre_grupo, nro_integrantes_grupo, #organizador
            Si #grupo ⊆ Res
                Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∪ {nombre_grupo, nro_integrantes_grupo, #organizador}
                Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador}
        //Cuarta Iteración
        DF4: #organizador -> nombre_organizador, telefono_organizador, años
            Si #organizador ⊆ Res
                Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador} ∪ {nombre_organizador, telefono_organizador, años_exp_organizador}
                Res = {#grupo, nombre_grupo, nro_integrantes_grupo, #organizador, nombre_organizador, telefono_organizador, años_exp_organizador}
        //Quinta Iteración
        DF5: #organizador, fecha_evento -> #grupo
            Si #organizador, fecha_evento ⊆ Res
                No se cumple
        //Sexta Iteración
        DF6: #persona_staff -> nombre_persona_staff, telefono_persona_staff, rol_persona_staff
            Si #persona_staff ⊆ Res
                No se cumple
        //Res no cambió, termino
    ``` 


### Esquemas en BCNF

## Dependencias multivaluadas

## Análisis de 4NF

### Esquemas en 4NF

### Esquemas en 4NF y que no son proyecciones de otros