# Ejercicio 8

**FESTIVALES** (#festival, denominacion_festival, localidad, cuil_musico, nombre_musico, fecha_nacimiento, #banda, nombre_banda, estilo_musical, #tema, nombre_tema, duracion, instrumento, cuil_auspiciante, url_plataforma_entradas, #sponsor)

## Dependencias funcionales 

DF1: `#festival -> denominacion_festival, localidad`  
DF2: `#banda -> nombre_banda, estilo_musical`  
DF3: `cuil_musico -> nombre_musico, fecha_nacimiento`  
DF4: `#festival, #banda, #tema -> nombre_tema, duracion`  
DF5: `#festival, #banda, #tema, cuil_musico -> instrumento `  

**CC: {#festival, #banda, cuil_musico, #tema, cuil_auspiciante, url_plataforma_entradas, #sponsor}**

## Análisis de BCNF

FESTIVALES no está en BCNF ya que existe al menos una DF no trivial, po ej DF2, cuyo determinante (#banda) no es superclave del esquema 

- Por lo tanto, particiono por DF2:
  - **F1)** (**#banda**, nombre_banda, estilo_musical)
  - **F2)** (**#festival**, denominacion_festival, localidad, **cuil_musico**, nombre_musico, fecha_nacimiento, **#banda**, **#tema**, nombre_tema, duracion, instrumento, **cuil_auspiciante**, **url_plataforma_entradas**, **#sponsor**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que F1 ∩ F2 es {#banda}, que es clave en F1. No se pierden DFs dado que por validacion simple, DF2 vale en F1 y DF1, DF3, DF4, DF5 valen en F2

- **F1** está en BCNF ya que solo vale la DF2 donde su determinante {#banda} es superclave del esquema
- **F2** no está en BCNF porque existe al menos una DF no trivial, por ej DF3, cuyo determinante (cuil_musico) no es superclave del esquema

- Por lo tanto, particiono por DF3:
  - **F3)** (**cuil_musico**, nombre_musico, fecha_nacimiento)
  - **F4)** (**#festival**, denominacion_festival, localidad, **cuil_musico**, **#banda**, **#tema**, nombre_tema, duracion, instrumento, **cuil_auspiciante**, **url_plataforma_entradas**, **#sponsor**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que F3 ∩ F4 es {cuil_musico}, que es clave en F3. No se pierden DFs dado que por validacion simple, DF3 vale en F3 y DF1, DF4, DF5 valen en F2

- **F3** está en BCNF ya que solo vale la DF3 donde su determinante {cuil_musico} es superclave del esquema
- **F4** no está en BCNF porque existe al menos una DF no trivial, por ej DF1, cuyo determinante (#festival) no es superclave del esquema

- Por lo tanto, particiono por DF1:
  - **F5)** (**#festival**, denominacion_festival, localidad)
  - **F6)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, nombre_tema, duracion, instrumento, **cuil_auspiciante**, **url_plataforma_entradas**, **#sponsor**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que F5 ∩ F6 es {#festival}, que es clave en F5. No se pierden DFs dado que por validacion simple, DF1 vale en F5 y DF4, DF5 valen en F6

- **F5** está en BCNF ya que solo vale la DF1 donde su determinante {#festival} es superclave del esquema
- **F6** no está en BCNF porque existe al menos una DF no trivial, por ej DF4, cuyo determinante (#festival, #banda, #tema) no es superclave del esquema

- Por lo tanto, particiono por DF4:
  - **F7)** (**#festival**, **#banda**, **#tema**, nombre_tema, duracion)
  - **F8)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, instrumento, **cuil_auspiciante**, **url_plataforma_entradas**, **#sponsor**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que F7 ∩ F8 es {#festival, #banda, #tema}, que es clave en F7. No se pierden DFs dado que por validacion simple, DF4 vale en F7 y DF5 vale en F8

- **F7** está en BCNF ya que solo vale la DF4 donde su determinante {#festival, #banda, #tema} es superclave del esquema
- **F8** no está en BCNF porque existe al menos una DF no trivial, por ej DF5, cuyo determinante (#festival, #banda, #tema, cuil_musico) no es superclave del esquema

- Por lo tanto, particiono por DF5:
  - **F9)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, instrumento)
  - **F10)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, **cuil_auspiciante**, **url_plataforma_entradas**, **#sponsor**)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que F9 ∩ F10 es {#festival, #banda, #tema, cuil_musico}, que es clave en F9. No se pierden DFs dado que por validacion simple, DF5 vale en F9 y en F10 solo valen DFs triviales

- **F9** está en BCNF ya que solo vale la DF5 donde su determinante {#festival, #banda, #tema, cuil_musico} es superclave del esquema
- **F10** está en BCNF ya que todos los atributos que la componen son iguales a la CC, por lo tanto cualquier DF que pueda hallarse va a ser trivial.

### Esquemas en BCNF

- **F1)** (**#banda**, nombre_banda, estilo_musical)
- **F3)** (**cuil_musico**, nombre_musico, fecha_nacimiento)
- **F5)** (**#festival**, denominacion_festival, localidad)
- **F7)** (**#festival**, **#banda**, **#tema**, nombre_tema, duracion)
- **F9)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, instrumento)
- **F10)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, **cuil_auspiciante**, **url_plataforma_entradas**, **#sponsor**)

**Clave primaria: {#festival, cuil_musico, #banda, #tema, cuil_auspiciante, url_plataforma_entradas, #sponsor}**

## Dependencias multivaluadas a partir de F10

DM1: `#festival, #banda, #tema ->> cuil_musico` X= #festival, #banda, #tema  Y= cuil -> el cuil tiene 
que ser independiente de todo lo que no sea la X. por ej no podria haber un festival ->> cuil_musico, porque ahi estas diciendo que es independiente de banda y tema, y eso no es cierto, porque hay una DF5 que las relaciona
DM2: `#festival ->> #cuil_auspiciante`
DM3: `#festival ->> url_plataforma_entradas`
DM4: `{} ->> #sponsor`

## Análisis de 4FN

**F10** no está en 4FN ya que existe al menos una DM no trivial, por ej DM4

- Por lo tanto, particion a partir de DM4:
  - **F11)** (**#sponsor**)
  - **F12)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, **cuil_auspiciante**, **url_plataforma_entradas**)
  
- **F11** está en 4FN ya que solo vale DM4, que es trivial.
- **F12** no está en 4FN ya que existe al menos un DM no trivial, por ej DM2

- Por lo tanto, particion a partir de DM2:
  - **F13)** (**#festival**, **cuil_auspiciante**)
  - **F14)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, **url_plataforma_entradas**)
  
- **F13** está en 4FN ya que solo vale DM2, que es trivial.
- **F14** no está en 4FN ya que existe al menos un DM no trivial, por ej DM3

- Por lo tanto, particion a partir de DM3:
  - **F15)** (**#festival**, **url_plataforma_entradas**)
  - **F16)** (**#festival**, **cuil_musico**, **#banda**, **#tema**)
  
- **F15** está en 4FN ya que solo vale DM3, que es trivial.
- **F16** está en 4FN ya que solo vale DM1, que es trivial.

### Esquemas en 4FN 

- **F1)** (**#banda**, nombre_banda, estilo_musical)
- **F3)** (**cuil_musico**, nombre_musico, fecha_nacimiento)
- **F5)** (**#festival**, denominacion_festival, localidad)
- **F7)** (**#festival**, **#banda**, **#tema**, nombre_tema, duracion)
- **F9)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, instrumento)
- **F11)** (**#sponsor**)
- **F13)** (**#festival**, **cuil_auspiciante**)
- **F15)** (**#festival**, **url_plataforma_entradas**)
- **F16)** (**#festival**, **cuil_musico**, **#banda**, **#tema**)

Como **F16** es proyección de **F9** se elimina

### Esquemas en 4FN y que no son proyecciones de otros  

- **F1)** (**#banda**, nombre_banda, estilo_musical)
- **F3)** (**cuil_musico**, nombre_musico, fecha_nacimiento)
- **F5)** (**#festival**, denominacion_festival, localidad)
- **F7)** (**#festival**, **#banda**, **#tema**, nombre_tema, duracion)
- **F9)** (**#festival**, **cuil_musico**, **#banda**, **#tema**, instrumento)
- **F11)** (**#sponsor**)
- **F13)** (**#festival**, **cuil_auspiciante**)
- **F15)** (**#festival**, **url_plataforma_entradas**)
