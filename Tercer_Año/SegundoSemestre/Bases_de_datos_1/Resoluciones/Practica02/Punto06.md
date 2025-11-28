# Ejercicio 6

## Dependencias Funcionales:

DF1: `email -> nombre_usuario`  
DF2: `#suscripcion -> #plan, email`  
DF3: `email_adicional -> nombre_adicional`  
DF4: `#suscripcion, email_adicional -> fecha_adicional`  
DF5: `#plan -> nombre_plan,  texto_condiciones, precio`  
DF6: `#contentido -> titulo,  sinopsis,  duracion`  

**CC: {#suscripcion, email_adicional, #contenido}**

## Análisis de BCNF:
SUSCRIPCION no esta en BCNF porque existe al menos una DF no trivial, por ej DF3, cuyo determinante (email_adicional) no es superclave del esquema.

- Por lo tanto, particiono por DF3:
  - **S1)** (**email_adicional**, nombre_adicional)  
  - **S2)** (**#suscripcion**, email, nombre_usuario, #plan, nombre_plan, texto_condiciones, precio, **email_adicional**, **#contenido**, titulo, sinopsis, duracion, fecha_adicional)
- Análisis de perdida de informacion y DFs:
  - No se pierde información ya que S1 ∩ S2 es {email_adicional}, que es clave en S1. No se pierden DFs dado que DF3 vale en S1 y DF1, DF2, DF4, DF5, DF6 valen en S2

- **S1** está en BCNF ya que {email_adicional} es superclave del esquema y solo vale la DF3 del esquema. 
- **S2** no está en BCNF porque existe al menos una DF no trivial, por ej DF1, cuyo determinante (email) no es superclave del esquema.

- Por lo tanto, particiono por DF1:
  - **S3)** (**email**, nombre_usuario)
  - **S4)** (**#suscripcion**, email, #plan, nombre_plan, texto_condiciones, precio, **email_adicional**, **#contenido**, titulo, sinopsis, duracion, fecha_adicional)
- Análisis de perdida de informacion y DFs:
  - No se pierde información ya que S3 ∩ S4 es {email}, que es clave en S3. No se pierden DFs dado que DF1 vale en S3 y DF2, DF4, DF5, DF6 valen en S4

- **S3** está en BCNF ya que {email} es superclave del esquema y solo vale la DF1 del esquema.
- **S4** no está en BCNF porque existe al menos una DF no trivial, por ej DF5, cuyo determinante (#plan) no es superclave del esquema.

- Por lo tanto, particiono por DF5:
  - **S5)** (**#plan**, nombre_plan, texto_condiciones, precio)
  - **S6)** (**#suscripcion**, email, #plan,  **email_adicional**, **#contenido**, titulo, sinopsis, duracion, fecha_adicional)
- Análisis de perdida de informacion y DFs:
  - No se pierde información ya que S5 ∩ S6 es {#plan}, que es clave en S5. No se pierden DFs dado que DF5 vale en S3 y DF2, DF4, DF6 valen en S4
  
- **S5** está en BCNF ya que {#plan} es superclave del esquema y solo vale la DF5 del esquema.
- **S6** no está en BCNF porque existe al menos una DF no trivial, por ej DF2, cuyo determinante (#plan) no es superclave del esquema.

- Por lo tanto, particiono por DF2:
  - **S7)** (**#subscripcion**, #plan, email)
  - **S8)** (**#suscripcion**, **email_adicional**, **#contenido**, titulo, sinopsis, duracion, fecha_adicional)
- Análisis de perdida de informacion y DFs:
  - No se pierde información ya que S7 ∩ S8 es {#subscripcion}, que es clave en S7. No se pierden DFs dado que DF2 vale en S7 y DF4, DF6 valen en S8

- **S7** está en BCNF ya que {#subscripcion} es superclave del esquema y solo vale la DF2 del esquema.
- **S8** no está en BCNF porque existe al menos una DF no trivial, por ej DF6, cuyo determinante (#contenido) no es superclave del esquema.

- Por lo tanto, particiono por DF6:
  - **S9)** (**#contenido**, titulo, sinopsis, duracion)
  - **S10)** (**#suscripcion**, **email_adicional**, **#contenido**, fecha_adicional)
- Análisis de perdida de informacion y DFs:
  - No se pierde información ya que S9 ∩ S10 es {#contenido}, que es clave en S9. No se pierden DFs dado que DF6 vale en S9 y DF4 vale en S10

- **S9** está en BCNF ya que {#contenido} es superclave del esquema y solo vale la DF6 del esquema.
- **S10** no está en BCNF porque existe al menos una DF no trivial, por ej DF4, cuyo determinante (#suscripcion, email_adicional) no es superclave del esquema.

- Por lo tanto, particiono por DF4:
  - **S11)** (**#suscripcion**, **email_adicional**, fecha_adicional)
  - **S12)** (**#suscripcion**, **email_adicional**, **#contenido**)
- Análisis de perdida de informacion y DFs:
  - No se pierde información ya que S11 ∩ S12 es {#suscripcion, email_adicional}, que es clave en S11. No se pierden DFs dado que DF4 vale en S11 y en S12 solo valen DFs triviales.

- **S11** está en BCNF ya que {#suscripcion, email_adicional} es superclave del esquema y solo vale la DF4 del esquema.
- **S12** está en BCNF ya que todos los atributos que la componen son iguales a la CC, por lo tanto cualquier DFs que pueda hallarse va a ser trivial.

### Esquemas en BCNF:
  - **S1)** (**email_adicional**, nombre_adicional)
  - **S3)** (**email**, nombre_usuario)
  - **S5)** (**#plan**, nombre_plan, texto_condiciones, precio)
  - **S7)** (**#subscripcion**, #plan, email)
  - **S9)** (**#contenido**, titulo, sinopsis, duracion)
  - **S11)** (**#suscripcion**, **email_adicional**, fecha_adicional)
  - **S12)** (**#suscripcion**, **email_adicional**, **#contenido**)

- **Clave primaria = {#suscripcion, email_adicional, #contenido}**

## Dependencias Multivaluadas a partir de S12:

DM1: `#suscripcion ->> #contenido`
DM2: `#suscripcion ->> email_adicional`

## Análisis de 4FN:

Dado que DM1 no es trivial, el esquema no está en 4FN. 

- Por lo tanto particiono por DM1:
- **S13)** (**#suscripcion**, **#contenido**)
- **S14)** (**#suscripcion**, **email_adicional**)

- **S13** está en 4FN ya que solo vale DM1, que es trivial.
- **S14** está en 4FN ya que solo vale DM2, que es trivial.

### Esquemas en 4FN:
  - **S1)** (**email_adicional**, nombre_adicional)
  - **S3)** (**email**, nombre_usuario)
  - **S5)** (**#plan**, nombre_plan, texto_condiciones, precio) 
  - **S7)** (**#subscripcion**, #plan, email) 
  - **S9)** (**#contenido**, titulo, sinopsis, duracion) 
  - **S11)** (**#suscripcion**, **email_adicional**, fecha_adicional)
  - **S13)** (**#suscripcion**, **#contenido**) 
  - **S14)** (**#suscripcion**, **email_adicional**) 
  ya que todos los esquemas están BCNF y las multivaluadas que valen son triviales.

Como **S14** es proyección de **S11**, se elimina.

### Esquemas en 4FN y que no son proyecciones de otros:
  - **S1)** (**email_adicional**, nombre_adicional)
  - **S3)** (**email**, nombre_usuario)
  - **S5)** (**#plan**, nombre_plan, texto_condiciones, precio) 
  - **S7)** (**#subscripcion**, #plan, email) 
  - **S9)** (**#contenido**, titulo, sinopsis, duracion) 
  - **S11)** (**#suscripcion**, **email_adicional**, fecha_adicional)
  - **S13)** (**#suscripcion**, **#contenido**) 

- **Clave primaria = {#suscripcion, email_adicional, #contenido}**