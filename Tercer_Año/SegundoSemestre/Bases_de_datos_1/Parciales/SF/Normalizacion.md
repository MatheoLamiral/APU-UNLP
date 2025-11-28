# Normalización

**SUSCRIPCION**(#suscripcion, email, nombre_usuario, #plan, nombre_plan, texto_condiciones, precio, email_adicional, nombre_adicional, #contenido, titulo, sinopsis, duracion, fecha_adicional)

## Dependencias funcionales

DF1: `#suscripcion -> email, #plan`  
DF2: `#suscripcion, email_adicional -> fecha_adicional`
DF3: `#plan -> nombre_plan, texto_condiciones, precio`
DF4: `#contenido -> titulo, sinopsis, duracion`
DF5: `email -> nombre_usuario`
DF6: `email_adicional -> nombre_adicional`

**CC:{#suscripcion, email_adicional, #contenido}**

## Análisis de BCNF

**SUSCRIPCION** no está en BCNF ya que existe al menos una DF no trivial, por ej DF6, cuyo determinante (email_adicional) no es superclave del esquema

- Por lo tanto particiono SUSCRIPCION teniendo en cuenta DF6:
  - **S1)**(**email_adicional**, nombre_adicional)
  - **S2)**(**#suscripcion**, email, nombre_usuario, #plan, nombre_plan, texto_condiciones, precio, **email_adicional**, **#contenido**, titulo, sinopsis, duracion, fecha_adicional)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que S1 ∩ S2 = {email_adicional} que es superclave en S1
  - No se pierden DFs ya que, por validación simple, DF6 vale en S1 y DF1, DF2, DF3, DF4 y DF5 valen en S2

- **S1** está en BCNF ya que solo vale en ella DF6, cuyo determinante (email_adicional) es superclave del esquema
- **S2** no está en BCNF ya que existe al menos una DF no trivial, por ej DF5, cuyo determinante (email) no es superclave del esquema 

- Por lo tanto particiono S2 teniendo en cuenta DF5:
  - **S3)**(**email**, nombre_usuario)
  - **S4)**(**#suscripcion**, email, #plan, nombre_plan, texto_condiciones, precio, **email_adicional**, **#contenido**, titulo, sinopsis, duracion, fecha_adicional)
- Análisis de pérdida de infromación y DFs:
  - No se pierde información ya que S3 ∩ S4 = {email} que es superclave en S3
  - No se pierden DFs ya que, por validación simple, DF5 vale en S3 y DF1, DF2, DF3 y DF4 valen en S4

- **S3** está en BCNF ya que solo vale en ella DF5, cuyo determinante (email) es superclave del esquema
- **S4** no está en BCNF ya que existe al menos una DF no trivial, por ej DF4, cuyo determinante (#contenido) no es superclave del esquema 

- Por lo tanto particiono S4 teniendo en cuenta DF4:
  - **S5)**(**#contenido**, titulo, sinopsis, duracion)
  - **S6)**(**#suscripcion**, email, #plan, nombre_plan, texto_condiciones, precio, **email_adicional**, **#contenido**, fecha_adicional)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que S5 ∩ S6 = {#contenido} que es superclave en S5
  - No se pierden DFs ya que, por validación simple, DF4 vale en S5 y DF1, DF2 y DF3 valen en S6

- **S5** está en BCNF ya que solo vale en ella DF4, cuyo determinante (#contenido) es superclave del esquema
- **S6** no está en BCNF ya que existe al menos una DF no trivial, por ej DF3, cuyo determinante (#plan) no es superclave del esquema

- Por lo tanto particiono S6 teniendo en cuenta DF3:
  - **S7)**(**#plan**, nombre_plan, texto_condiciones, precio)
  - **S8)**(**#suscripcion**, email, #plan, **email_adicional**, **#contenido**, fecha_adicional)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que S7 ∩ S8 = {#plan} que es superclave en S7
  - No se pierden DFs ya que, por validación simple, DF3 vale en S7 y DF1 y DF2 valen en S8

- **S7** está en BCNF ya que solo vale en ella DF3, cuyo determinante (#plan) es superclave del esquema
- **S8** no está en BCNF ya que existe al menos una DF no trivial, por ej DF2, cuyo determinante (email) no es superclave del esquema

- Por lo tanto particiono S8 teniendo en cuenta DF2:
  - **S9)**(**#suscripcion**, **email_adicional**, fecha_adicional)
  - **S10)**(**#suscripcion**, email, #plan, **email_adicional**, **#contenido**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que S9 ∩ S10 = {#suscripcion, email_adicional} que es superclave en S9
  - No se pierden DFs ya que, por validación simple, DF2 vale en S9 y DF1 vale en S10

- **S9** está en BCNF ya que solo vale en ella DF2, cuyo determinante (#suscripcion, email_adicional) es superclave del esquema
- **S10** no está en BCNF ya que existe al menos una DF no trivial, por ej DF1, cuyo determinante (#suscripcion) no es superclave del esquema

- Por lo tanto particiono S10 teniendo en cuenta DF1:
  - **S11)**(**#suscripcion**, email, #plan)
  - **S12)**(**#suscripcion**, **email_adicional**, **#contenido**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que S11 ∩ S12 = {#suscripcion} que es superclave en S11
  - No se pierden DFs ya que, por validación simple, DF1 vale en S11

- **S11** está en BCNF ya que solo vale en ella DF1, cuyo determinante (#suscripcion) es superclave del esquema
- **S12** está en BCNF ya que todos los atributos que lo componen son iguales a la CC, por lo que cualquier DF que pueda hallarse va a ser trivial

### Esquemas en BCNF

- **S1)**(**email_adicional**, nombre_adicional)
- **S3)**(**email**, nombre_usuario)
- **S5)**(**#contenido**, titulo, sinopsis, duracion)
- **S7)**(**#plan**, nombre_plan, texto_condiciones, precio)
- **S9)**(**#suscripcion**, **email_adicional**, fecha_adicional)
- **S11)**(**#suscripcion**, email, #plan)
- **S12)**(**#suscripcion**, **email_adicional**, **#contenido**)

**CP: {#suscripcion, email_adicional, #contenido}**

## Dependencias multivaluadas válidas en S12

DM1: `#suscripcion ->> email_adicional`  
DM2: `#suscripcion ->> #contenido`

## Análisis de 4FN 

S12 no está en 4FN ya que existe al menos una DM no trivial, por ej DM1

- Por lo tanto particiono S12 teniendo en cuenta DM1:
  - **S13)**(**#suscripcion**, **email_adicional**)
  - **S14)**(**#suscripcion**, **#contenido**)

- **S13** está en 4FN ya que solo vale en ella DM1, que es trivial
- **S14** está en 4FN ya que solo vale en ella DM2, que es trivial 

### Esquemas en 4FN

S1, S3, S5, S7, S9, S11, están en 4FN ya que no tienen DMs

- **S1)**(**email_adicional**, nombre_adicional)
- **S3)**(**email**, nombre_usuario)
- **S5)**(**#contenido**, titulo, sinopsis, duracion)
- **S7)**(**#plan**, nombre_plan, texto_condiciones, precio)
- **S9)**(**#suscripcion**, **email_adicional**, fecha_adicional)
- **S11)**(**#suscripcion**, email, #plan)
- **S13)**(**#suscripcion**, **email_adicional**)
- **S14)**(**#suscripcion**, **#contenido**)

### Esquemas en 4FN que no son proyecciones de otros

- **S1)**(**email_adicional**, nombre_adicional)
- **S3)**(**email**, nombre_usuario)
- **S5)**(**#contenido**, titulo, sinopsis, duracion)
- **S7)**(**#plan**, nombre_plan, texto_condiciones, precio)
- **S9)**(**#suscripcion**, **email_adicional**, fecha_adicional)
- **S11)**(**#suscripcion**, email, #plan)
- **S14)**(**#suscripcion**, **#contenido**)

