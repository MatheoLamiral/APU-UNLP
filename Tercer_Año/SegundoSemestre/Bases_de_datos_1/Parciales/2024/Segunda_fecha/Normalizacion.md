# Normalización 

**PARQUEDIVERSIONES**(id_atractivo, nombre_atractivo, descripcion_atractivo, id_categoria_atractivo, nombre_categoria, cuil_empleado, nombre_apellido_empleado, nro_legajo_empleado, dia_semana, id_agente_mantenimiento)

## Dependencias funcionales

DF1: `id_atractivo -> nombre_atractivo, descripcion_atractivo`   
DF2: `id_categoria_atractivo -> nombre_categoria`  
DF3: `nro_legajo_empleado -> cuil_empleado, nombre_apellido_empleado`  
DF4: `cuil_empleado -> nro_legajo_empleado, nombre_apellido_empleado`  
DF5: `id_atractivo, nro_legajo_empleado -> dia_semana`  
DF6: `id_atractivo, cuil_empleado -> dia_semana`  

**CC1: {id_atractivo, nro_legajo_empleado, id_categoria_atractivo, id_agente_mantenimiento}** -> la marco con __
**CC2: {id_atractivo, cuil_empleado, id_categoria_atractivo, id_agente_mantenimiento}** -> la marco con ""

## Análisis de BCNF

**PARQUEDIVERSIONES** no está en BCNF, ya que existe al menos una DF no trivial, por ejemplo DF1, cuyo determinante (id_atractivo) no es superclave del esquema

- Por lo tanto, particiono PARQUEDIVERSIONES teniendo en cuenta DF1:
  - **P1)** (<ins>**"id_atractivo"**</ins>, nombre_atractivo, descripcion_atractivo)
  - **P2)** (<ins>**"id_atractivo"**</ins>, <ins>**"id_categoria_atractivo"**</ins>, nombre_categoria, **"cuil_empleado"**, nombre_apellido_empleado, <ins>**nro_legajo_empleado**</ins>, dia_semana, <ins>**"id_agente_mantenimiento"**</ins>)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que P1 ∩ P2 = {id_atractivo} que es superclave en P1
  - No se pierden DFS ya que, por validación simple, DF1 vale en P1 y DF2, DF3, DF4, DF5, DF6 valen en P2

- **P1** está en BCNF ya que solo vale DF1, donde su determinante (id_atractivo) es superclave del esquema 
- **P2** no está en BCNF, ya que existe al menos una DF no trivial, por ejemplo DF2, cuyo determinante (id_categoria_atractivo) no es superclave del esquema

- Por lo tanto, particiono P2 teniendo en cuenta DF2:
  - **P3)** (<ins>**"id_categoria_atractivo"**</ins>, nombre_categoria)
  - **P4)** (<ins>**"id_atractivo"**</ins>, <ins>**"id_categoria_atractivo"**</ins>,**"cuil_empleado"**, nombre_apellido_empleado, <ins>**nro_legajo_empleado**</ins>, dia_semana, <ins>**"id_agente_mantenimiento"**</ins>)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que P3 ∩ P4 = {id_categoria_atractivo} que es superclave en P3
  - No se pierden DFS ya que, por validación simple, DF2 vale en P3 y DF3, DF4, DF5, DF6 valen en P4

- **P3** está en BCNF ya que solo vale DF2, donde su determinante (id_categoria_atractivo) es superclave del esquema
- **P4** no está en BCNF, ya que existe al menos una DF no trivial, por ejemplo DF3, cuyo determinante (nro_legajo_empleado) no es superclave del esquema

- Por lo tanto, particiono P4 teniendo en cuenta DF3:
  - **P5)** (<ins>**nro_legajo_empleado**</ins>, **"cuil_empleado"**, nombre_apellido_empleado)
  - **P6)** (<ins>**"id_atractivo"**</ins>, <ins>**"id_categoria_atractivo"**</ins>, <ins>**nro_legajo_empleado**</ins>, dia_semana, <ins>**"id_agente_mantenimiento"**</ins>)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que P5 ∩ P6 = {nro_legajo_empleado} que es superclave en P5
  - No se pierden DFS ya que, por validación simple, DF3 y DF4 valen en P5 y DF5 vale en P6. Además DF6 tampoco se pierde por más que ya no tengamos cuil_empleado en P6, ya que es posible acceder a cuil_empleado indirectamente a través de nro_legajo_empleado:
    - id_atractivo y dia_semana pueden accederse directamente ya que están en P6.
    - cuil_empleado no está en P6, sin embargo la dependencia funcinal no se pierde ya que existe una relación indirecta a través de la DF3 donde a partir de nro_legajo_empleado se puede acceder a cuil_empleado.
    - DF6 no quedó valida pero no se pierde
  - En este punto se perfila CC1 como clave primaria del esquema 

- **P5** está en BCNF ya que solo valen DF3 y DF4, donde sus determinantes (nro_legajo_empleado y cuil_empleado respectivamente) son superclaves del esquema
- **P6** no está en BCNF, ya que existe al menos una DF no trivial, por ejemplo DF5, cuyo determinante (id_atractivo, nro_legajo_empleado) no es superclave del esquema

- Por lo tanto, particiono P6 teniendo en cuenta DF5:
  - **P7)** (<ins>**id_atractivo**</ins>, <ins>**nro_legajo_empleado**</ins>, dia_semana)
  - **P8)** (<ins>**id_atractivo**</ins>, <ins>**nro_legajo_empleado**</ins>, <ins>**id_categoria_atractivo**</ins>, <ins>**id_agente_mantenimiento**</ins>)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que P7 ∩ P8 = {id_atractivo, nro_legajo_empleado} que es superclave en P7
  - No se pierden DFS ya que, por validación simple, DF5 vale en P7. Además DF6 tampoco se pierde por más que ya no tengamos cuil_empleado en P7, ya que es posible acceder a cuil_empleado indirectamente a través de nro_legajo_empleado:
    - id_atractivo y dia_semana pueden accederse directamente ya que están en P7.
    - cuil_empleado no está en P7, sin embargo la dependencia funcinal no se pierde ya que existe una relación indirecta a través de la DF3 donde a partir de nro_legajo_empleado se puede acceder a cuil_empleado.
    - DF6 no quedó valida pero no se pierde

- **P7** está en BCNF ya que solo vale DF5, donde su determinante (id_atractivo, nro_legajo_empleado) es superclave del esquema
- **P8** está en BCNF ya que es igual a CC1, por lo que cualquier DF que pueda hallarse va a ser trivial

### Esquemas en BCNF

- **P1)** (<ins>**"id_atractivo"**</ins>, nombre_atractivo, descripcion_atractivo)
- **P3)** (<ins>**"id_categoria_atractivo"**</ins>, nombre_categoria)
- **P5)** (<ins>**nro_legajo_empleado**</ins>, cuil_empleado, nombre_apellido_empleado)
- **P7)** (<ins>**id_atractivo**</ins>, <ins>**nro_legajo_empleado**</ins>, dia_semana)
- **P8)** (<ins>**id_atractivo**</ins>, <ins>**nro_legajo_empleado**</ins>, <ins>**id_categoria_atractivo**</ins>, <ins>**id_agente_mantenimiento"**</ins>)

**CP{id_atractivo, nro_legajo_empleado, id_categoria_atractivo, id_agente_mantenimiento}**

## Dependencias multivaluadas válidas en P8

- DM1: `id_atractivo ->> id_categoria_atractivo`
- DM2: `id_atractivo ->> nro_legajo_empleado`
- DM3: `{} ->> id_agente_mantenimiento`

## Análisis de 4NF

**P8** no está en 4NF ya que existe al menos una DM no trivial, por ejemplo DM3.

- Por lo tanto, particiono P8 teniendo en cuenta DM3:
  - **P9)** (<ins>**id_agente_mantenimiento"**</ins>)
  - **P10)** (<ins>**id_atractivo**</ins>, <ins>**nro_legajo_empleado**</ins>, <ins>**id_categoria_atractivo"**</ins>)

- **P9** está en 4NF ya que solo vale DM3 que es trivial.
- **P10** no está en 4NF ya que existe al menos una DM no trivial, por ejemplo DM2.

- Por lo tanto, particiono P10 teniendo en cuenta DM2:
  - **P11)** (<ins>**id_atractivo"**</ins>, <ins>**nro_legajo_empleado"**</ins>)
  - **P12)** (<ins>**id_atractivo"**</ins>, <ins>**id_categoria_atractivo"**</ins>)

- **P11** está en 4NF ya que solo vale DM2 que es trivial.
- **P12** está en 4NF ya que solo vale DM1 que es trivial.

### Esquemas en 4NF

- **P1)** (<ins>**"id_atractivo"**</ins>, nombre_atractivo, descripcion_atractivo)
- **P3)** (<ins>**"id_categoria_atractivo"**</ins>, nombre_categoria)
- **P5)** (<ins>**nro_legajo_empleado"**</ins>, cuil_empleado, nombre_apellido_empleado)
- **P7)** (<ins>**id_atractivo"**</ins>, <ins>**nro_legajo_empleado"**</ins>, dia_semana)
- **P9)** (<ins>**id_agente_mantenimiento"**</ins>)
- **P11)** (<ins>**id_atractivo"**</ins>, <ins>**nro_legajo_empleado"**</ins>)
- **P12)** (<ins>**id_atractivo"**</ins>, <ins>**id_categoria_atractivo"**</ins>)

- como P11 es proyección de P7, se elimina 

### Esquemas en 4NF que no son proyecciones de otros

- **P1)** (<ins>**"id_atractivo"**</ins>, nombre_atractivo, descripcion_atractivo)
- **P3)** (<ins>**"id_categoria_atractivo"**</ins>, nombre_categoria)
- **P5)** (<ins>**nro_legajo_empleado"**</ins>, cuil_empleado, nombre_apellido_empleado)
- **P7)** (<ins>**id_atractivo"**</ins>, <ins>**nro_legajo_empleado"**</ins>, dia_semana)
- **P9)** (<ins>**id_agente_mantenimiento"**</ins>)
- **P12)** (<ins>**id_atractivo"**</ins>, <ins>**id_categoria_atractivo"**</ins>)

