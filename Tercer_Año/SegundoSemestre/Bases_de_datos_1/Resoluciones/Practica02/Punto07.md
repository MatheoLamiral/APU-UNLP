# Ejercicio 7

MEDICION_AMBIENTAL(#medicion, #pozo, valor_medicion, #parametro, fecha_medicion, cuil_operario, #instrumento, nombre_parametro, valor_ref, descripcion_pozo, fecha_perforacion, apellido_operario, nombre_operario, fecha_nacimiento, marca_instrumento, modelo_instrumento, dominio_vehiculo, fecha_adquisicion)

## Dependencias funcionales

DF1: `#medicion -> #pozo, cuil_operario, fecha_medicion`  
DF2: `#parametro -> nombre_parametro, valor_ref`  
DF3: `#instrumento -> marca_instrumento, modelo_instrumento`  
DF4: `cuil_operario -> nombre_operario, apellido_operario, fecha_nacimiento`  
DF5: `dominio_vehiculo -> fecha_adquisicion`  
DF6: `#pozo -> descripcion_pozo, fecha_perforacion`  
DF7: `#medicion, #parametro -> valor_medicion`

**CC: {#medicion, #parametro, #instrumento, dominio_vahiculo}**

## Análisis de BCNF

MEDICION_AMBIENTAL no está en BCNF porque existe al menos una DF no trivial, por ej DF2, cuyo determinante (#parametro) no es superclave del esquema

- Por lo tanto, particiono por DF2:
  - **M1)** (**#parametro**, nombre_parametro, valor_ref)
  - **M2)** (**#medicion**, #pozo, valor_medicion, **#parametro**, fecha_medicion, cuil_operario, **#instrumento**, descripcion_pozo, fecha_perforacion, apellido_operario, nombre_operario, fecha_nacimiento, marca_instrumento, modelo_instrumento, **dominio_vehiculo**, fecha_adquisicion)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que M1 ∩ M2 es {#parametro}, que es clave en M1. No se pierden DFs dado que por validacion simple, DF2 vale en M1 y DF1, DF3, DF4, DF5, DF6, DF7 valen en M2

- **M1** está en BCNF ya que solo vale la DF2 donde su determinante {#parametro} es superclave del esquema
- **M2** no está en BCNF porque existe al menos una DF no trivial, por ej DF3, cuyo determinante (#instrumento) no es superclave del esquema

- Por lo tanto, particiono por DF3:
  - **M3)** (**#instrumento**, marca_instrumento, modelo_instrumento)
  - **M4)** (**#medicion**, #pozo, valor_medicion, **#parametro**, fecha_medicion, cuil_operario, **#instrumento**, descripcion_pozo, fecha_perforacion, apellido_operario, nombre_operario, fecha_nacimiento, **dominio_vehiculo**, fecha_adquisicion)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que M3 ∩ M4 es {#instrumento}, que es clave en M3. No se pierden DFs dado que por validación simple, DF3 vale en M3 y DF1, DF4, DF5, DF6, DF7 valen en M4

- **M3** está en BCNF ya que solo vale la DF2 donde su determinante {#instrumento} es superclave del esquema 
- **M4** no está en BCNF porque existe al menos una DF no trivial, por ej DF5, cuyo determinante (dominio_vahiculo) no es superclave del esquema

- Por lo tanto, particiono por DF5:
  - **M5)** (**dominio_vahiculo**, fecha_adquisicion)
  - **M6)** (**#medicion**, #pozo, valor_medicion, **#parametro**, fecha_medicion, cuil_operario, **#instrumento**, descripcion_pozo, fecha_perforacion, apellido_operario, nombre_operario, fecha_nacimiento, **dominio_vehiculo**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que M5 ∩ M6 es {dominio_vehiculo}, que es clave en M5. No se pierden DFs dado que por validación simple, DF5 vale en M5 y DF1, DF4, DF6, DF7 valen en M6

- **M5** está en BCNF ya que solo vale la DF5 donde su determinante {dominio_vehiculo} es superclave del esquema 
- **M6** no está en BCNF porque existe al menos una DF no trivial, por ej DF6, cuyo determinante (#pozo) no es superclave del esquema

- Por lo tanto, particiono por DF6:
  - **M7)** (**#pozo**, descripcion_pozo, fecha_perforacion)
  - **M8)** (**#medicion**, #pozo, valor_medicion, **#parametro**, fecha_medicion, cuil_operario, **#instrumento**, apellido_operario, nombre_operario, fecha_nacimiento, **dominio_vehiculo**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que M7 ∩ M8 es {#pozo}, que es clave en M7. No se pierden DFs dado que por validación simple, DF6 vale en M7 y DF1, DF4, DF7 valen en M8

- **M7** está en BCNF ya que solo vale la DF6 donde su determinante {#pozo} es superclave del esquema 
- **M8** no está en BCNF porque existe al menos una DF no trivial, por ej DF4, cuyo determinante (cuil_operario) no es superclave del esquema

- Por lo tanto, particiono por DF4:
  - **M9)** (**cuil_operario**, apellido_operario, nombre_operario, fecha_nacimiento)
  - **M10)** (**#medicion**, #pozo, valor_medicion, **#parametro**, fecha_medicion, cuil_operario, **#instrumento**, **dominio_vehiculo**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que M9 ∩ M10 es {#cuil_operario}, que es clave en M9. No se pierden DFs dado que por validación simple, DF4 vale en M9 y DF1, DF7 valen en M10

- **M9** está en BCNF ya que solo vale la DF4 donde su determinante {cuil_operario} es superclave del esquema
- **M10** no está en BCNF porque existe al menos una DF no trivial, por ej DF7, cuyo determinante (#medicion, #parametro) no es superclave del esquema

- Por lo tanto, particiono por DF7:
  - **M11)** (**#medicion**, **#parametro**, valor_medicion)
  - **M12)** (**#medicion**, #pozo, **#parametro**, fecha_medicion, cuil_operario, **#instrumento**, **dominio_vehiculo**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que M11 ∩ M12 es {#medicion, #parametro}, que es clave en M11. No se pierden DFs dado que por validación simple, DF7 vale en M11 y DF1 vale en M12

- **M11** está en BCNF ya que solo vale DF7 donde su determinante {#medicion, #parametro} es superclave del esquema 
- **M12** no está en BCNF porque existe al menos una DF no trivial, por ej DF1, cuyo determinante (#medicion) no es superclave del esquema

- Por lo tanto, particiono por DF1:
  - **M13)** (**#medicion**, #pozo, #parametro, fecha_medicion, cuil_operario)
  - **M14)** (**#medicion**, **#parametro**, **#instrumento**, **dominio_vehiculo**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que M13 ∩ M14 es {#medicion}, que es clave en M13. No se pierden DFs dado que por validación simple, DF1 vale en M13 y en M14 solo valen DFs triviales

- **M11** está en BCNF ya que solo vale la DF1 donde su determinante {#medicion} es superclave del esquema 
- **M12** está en BCNF ya que todos los atributos que la componen son iguales a la CC, por lo tanto cualquier DF que pueda hallarse va a ser trivial.

### Esquemas en BCNF

- **M1)** (**#parametro**, nombre_parametro, valor_ref)
- **M3)** (**#instrumento**, marca_instrumento, modelo_instrumento)
- **M5)** (**dominio_vahiculo**, fecha_adquisicion)
- **M7)** (**#pozo**, descripcion_pozo, fecha_perforacion)
- **M9)** (**cuil_operario**, apellido_operario, nombre_operario, fecha_nacimiento)
- **M11)** (**#medicion**, **#parametro**, valor_medicion)
- **M13)** (**#medicion**, #pozo, #parametro, fecha_medicion, cuil_operario)
- **M14)** (**#medicion**, **#parametro**, **#instrumento**, **dominio_vehiculo**)

- **Clave primaria = {#medicion, #parametro, #instrumento, dominio_vehiculo}**

## Dependencias multivaluadas a partir de M14

DM1: `#medicion ->> #parametro`  
DM2: `#medicion ->> #instrumento`  
DM3: `{} ->> dominio_vehiculo`

## Análisis de 4FN:

**M14** no está en 4FN ya que existe al menos una DM no trivial, por ej DM3.

- Por lo tanto, particiono M14 a partir de DM3:
  - **M15)** (**dominio_vehiculo**)
  - **M16)** (**#medicion**, **#parametro**, **#instrumento**)
  
- **M15** está e 4FN ya que solo vale DM3, que es trivial.
- **M16** no está en 4FN ya que existe al menos un DM no trivial, por ej DM2

- Por lo tanto, particiono M16 a partir de DM2:
  - **M17)** (**#medicion**, **#instrumento**)
  - **M18)** (**#medicion**, **#parametro**)
  
- **M15** está e 4FN ya que solo vale DM2, que es trivial.
- **M16** está e 4FN ya que solo vale DM1, que es trivial

### Esquemas en 4FN:

- **M1)** (**#parametro**, nombre_parametro, valor_ref)
- **M3)** (**#instrumento**, marca_instrumento, modelo_instrumento)
- **M5)** (**dominio_vahiculo**, fecha_adquisicion)
- **M7)** (**#pozo**, descripcion_pozo, fecha_perforacion)
- **M9)** (**cuil_operario**, apellido_operario, nombre_operario, fecha_nacimiento)
- **M11)** (**#medicion**, **#parametro**, valor_medicion)
- **M13)** (**#medicion**, #pozo, #parametro, fecha_medicion, cuil_operario)
- **M15)** (**dominio_vehiculo**)
- **M17)** (**#medicion**, **#instrumento**)
- **M18)** (**#medicion**, **#parametro**)

Como **M18** es proyección de **M11** se elimina, y como **M15** es proyección de **M5** también se elimina

### Esquemas en 4FN y que no son proyecciones de otros  

- **M1)** (**#parametro**, nombre_parametro, valor_ref)
- **M3)** (**#instrumento**, marca_instrumento, modelo_instrumento)
- **M5)** (**dominio_vahiculo**, fecha_adquisicion)
- **M7)** (**#pozo**, descripcion_pozo, fecha_perforacion)
- **M9)** (**cuil_operario**, apellido_operario, nombre_operario, fecha_nacimiento)
- **M11)** (**#medicion**, **#parametro**, valor_medicion)
- **M13)** (**#medicion**, #pozo, #parametro, fecha_medicion, cuil_operario)
- **M17)** (**#medicion**, **#instrumento**)