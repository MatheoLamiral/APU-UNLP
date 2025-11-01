# Normalización

**POLIZAS**(#poliza, #cliente, cuil, nombre, direccion, telefono, tipo_seguro, fecha_inicio, fecha_fin, #siniestro, fecha_denuncia, estado_siniestro, #perito, #cuota, detalle_reparacion)

## Dependencias funcionales

DF1: `#poliza -> tipo_seguro, fecha_inicio, fecha_fin, #cliente`  
DF2: `#poliza -> tipo_seguro, fecha_inicio, fecha_fin, cuil`  
DF3: `#cliente -> nombre, direccion, telefono, cuil`  
DF4: `cuil -> nombre, direccion, telefono, #cliente`    
DF5: `#siniestro -> fecha_denuncia, estado_siniestro`  

**CC: {#poliza, #siniestro, #perito, #cuota, detalle_reparacion}**

## Análisis de BCNF

**POLIZAS** no está en BCNF ya que existe al menos una DF no trivial, por ejemplo DF5, cuyo determinante (#siniestro) no es superclave del esquema.

- Por lo tanto particiono POLIZAS teniendo en cuenta DF5:
  - **P1)** (**#siniestro**, fecha_denuncia, estado_siniestro)
  - **P2)** (**#poliza**, #cliente, cuil, nombre, direccion, telefono, tipo_seguro, fecha_inicio, fecha_fin, **#siniestro**, **#perito**, **#cuota**, **detalle_reparacion**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que P1 ∩ P2 = {#siniestro} que es superclave de P1.
  - No se pierden DFs ya que, por validación simple, DF5 vale en P1 y DF1, DF2, DF3, DF4 valen en P2.

- **P1** está en BCNF ya que solo vale DF5, donde su determinante (#siniestro) es superclave del esquema.
- **P2** no está en BCNF ya que existe al menos una DF no trivial, por ejemplo DF3, cuyo determinante (#cliente) no es superclave del esquema.

- Por lo tanto particiono P2 teniendo en cuenta DF3:
  - **P3)** (**#cliente**, nombre, direccion, telefono, cuil)
  - **P4)** (**#poliza**, #cliente, tipo_seguro, fecha_inicio, fecha_fin, **#siniestro**, **#perito**, **#cuota**, **detalle_reparacion**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que P3 ∩ P4 = {#cliente} que es superclave de P3.
  - No se pierden DFs ya que, por validación simple, DF3 y DF4 valen en P3 y DF1 vale en P4. Además DF2 tampoco se pierde por más que ya no tengamos cuil en P4, ya que es posible acceder a cuil indirectamente a través de #cliente:
    - tipo_seguro, fecha_inicio y fecha_fin pueden accederse directamente ya que están en P4.
    - cuil no está en P4, sin embargo la dependencia funcinal no se pierde ya que existe una relación indirecta a través de la DF1 donde a partir de #poliza se puede acceder a #cliente y la DF3 donde a partir de #cliente se puede acceder a cuil.
    - DF2 no quedó valida pero no se pierde

- **P3** está en BCNF ya que solo valen DF3 y DF4, donde sus determinantes (#cliente y cuil respectivamente) son superclaves del esquema.
- **P4** no está en BCNF ya que existe al menos una DF no trivial, por ejemplo DF1, cuyo determinante (#poliza) no es superclave del esquema.

- Por lo tanto particiono P4 teniendo en cuenta DF1:
  - **P5)** (**#poliza**, tipo_seguro, fecha_inicio, fecha_fin, #cliente)
  - **P6)** (**#poliza**, **#siniestro**, **#perito**, **#cuota**, **detalle_reparacion**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que P5 ∩ P6 = {#poliza} que es superclave de P5.
  - No se pierden DFs ya que, por validación simple, DF1 vale en P5. Además DF2 tampoco se pierde por más que ya no tengamos cuil en P5, ya que es posible acceder a cuil indirectamente a través de #cliente:
    - tipo_seguro, fecha_inicio y fecha_fin pueden accederse directamente ya que están en P5.
    - cuil no está en P5, sin embargo la dependencia funcinal no se pierde ya que existe una relación indirecta a través de la DF1 donde a partir de #poliza se puede acceder a #cliente y la DF3 donde a partir de #cliente se puede acceder a cuil.
    - DF2 no quedó valida pero no se pierde

- **P5** está en BCNF ya que solo vale DF1, donde su determinante (#poliza) es superclave del esquema.
- **P6** está en BCNF ya que todos los atributos que lo componen son iguales a la CC, por lo que cualquier DF que pueda hallarse va a ser trivial

### Esquemas en BCNF

- **P1)** (**#siniestro**, fecha_denuncia, estado_siniestro)
- **P3)** (**#cliente**, nombre, direccion, telefono, cuil)
- **P5)** (**#poliza**, tipo_seguro, fecha_inicio, fecha_fin, #cliente)
- **P6)** (**#poliza**, **#siniestro**, **#perito**, **#cuota**, **detalle_reparacion**)

**CP: {#poliza, #siniestro, #perito, #cuota, detalle_reparacion}**

## Dependencias multivaluadas válidas en P6

DM1: `#poliza ->> #cuota`  
DM2: `#siniestro ->> #perito`  
DM3: `#siniestro, #poliza ->> detalle_reparacion`  

## Análisis de 4NF

**P6** no está en 4NF ya que existe al menos una DM no trivial, por ejemplo DM2.

- Por lo tanto particiono P6 teniendo en cuenta DM2:
  - **P7)** (**#siniestro**, **#perito**)
  - **P8)** (**#poliza**, **#siniestro**, **#cuota**, **detalle_reparacion**)

- **P7** está en 4NF ya que solo vale DM2 que es trivial.
- **P8** no está en 4NF ya que existe al menos una DM no trivial, por ejemplo DM1.

- Por lo tanto particiono P8 teniendo en cuenta DM1:
  - **P9)** (**#poliza**, **#cuota**)
  - **P10)** (**#poliza**, **#siniestro**, **detalle_reparacion**)

- **P9** está en 4NF ya que solo vale DM1 que es trivial.
- **P10** está en 4NF ya que solo vale DM3 que es trivial.

### Esquemas en 4NF

- **P1)** (**#siniestro**, fecha_denuncia, estado_siniestro)
- **P3)** (**#cliente**, nombre, direccion, telefono, cuil)
- **P5)** (**#poliza**, tipo_seguro, fecha_inicio, fecha_fin, #cliente)
- **P7)** (**#siniestro**, **#perito**)
- **P9)** (**#poliza**, **#cuota**)
- **P10)** (**#poliza**, **#siniestro**, **detalle_reparacion**)



