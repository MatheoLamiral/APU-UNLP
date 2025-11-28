# Ejercicio 9

**TORNEOS** (#torneo, nombre_torneo, año, #equipo, nombre_equipo, estadio_equipo, puesto, #reglamentacion, descripcion, #auspiciante)

## Dependencias funcionales 

DF1: `#torneo -> nombre_torneo`  
DF2: `#equipo -> nombre_equipo, estadio_equipo`  
DF3: `#equipo, #torneo, año -> puesto`  
DF4: `puesto, #torneo, año -> #equipo`  
DF5: `#reglamentacion -> descripcion`  

**CC1: {#torneo, #equipo, año, #reglamentacion, #auspiciante}** la marco con __  
**CC2: {#torneo, puesto, año, #reglamentacion, #auspiciante}**  la marco con ""

## Análisis de BCNF

TORNEOS no está en BCNF ya que existe al menos una DF no trivial, po ej DF1, cuyo determinante (#torneo) no es superclave del esquema

- Por lo tanto, particiono por DF1:
  - **T1)** (**#torneo**, nombre_torneo)
  - **T2)** (<u>**"#torneo"**</u>, <u>**"año"**</u>, <u>**#equipo**</u>, nombre_equipo, estadio_equipo, **"puesto"**, <u>**"#reglamentacion"**</u>, descripcion, <u>**"#auspiciante"**</u>)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que T1 ∩ T2 es {#torneo}, que es clave en T1. No se pierden DFs dado que por validacion simple, DF1 vale en T1 y DF2, DF3, DF4, DF5 valen en T2

- **T1** está en BCNF ya que solo vale la DF1 donde su determinante {#torneo} es superclave del esquema
- **T2** no está en BCNF porque existe al menos una DF no trivial, por ej DF5, cuyo determinante (#reglamentacion) no es superclave del esquema

- Por lo tanto, particiono por DF5:
  - **T3)** (**#reglamentacion**, descripcion)
  - **T4)** (<u>**"#torneo"**</u>, <u>**"año"**</u>, <u>**#equipo**</u>, nombre_equipo, estadio_equipo, **"puesto"**, <u>**"#reglamentacion"**</u>, <u>**"#auspiciante"**</u>)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que T3 ∩ T4 es {#reglamentacion}, que es clave en T3. No se pierden DFs dado que por validacion simple, DF5 vale en T3 y DF2, DF3, DF4, valen en T4

- **T3** está en BCNF ya que solo vale la DF5 donde su determinante {#reglamentacion} es superclave del esquema
- **T4** no está en BCNF porque existe al menos una DF no trivial, por ej DF2, cuyo determinante (#equipo) no es superclave del esquema

- Por lo tanto, particiono por DF2:
  - **T5)** (**#equipo**, nombre_equipo, estadio_equipo)
  - **T6)** (<u>**"#torneo"**</u>, <u>**"año"**</u>, <u>**#equipo**</u>, **"puesto"**, <u>**"#reglamentacion"**</u>, <u>**"#auspiciante"**</u>)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que T5 ∩ T6 es {#equipo}, que es clave en T5. No se pierden DFs dado que por validacion simple, DF2 vale en T5 y  DF3, DF4, valen en T6

- **T5** está en BCNF ya que solo vale la DF2 donde su determinante {#equipo} es superclave del esquema
- **T6** no está en BCNF porque existe al menos una DF no trivial, por ej DF3, cuyo determinante (#equipo) no es superclave del esquema

- Por lo tanto, particiono por DF3:
  - **T7)** (<u>**"#torneo"**</u>, <u>**"año"**</u>, <u>**#equipo**</u>, **"puesto"**)
  - **T8)** (<u>**#torneo**</u>, <u>**año**</u>, <u>**#equipo**</u>, <u>**#reglamentacion**</u>, <u>**#auspiciante**</u>)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que T7 ∩ T8 es {#torneo, año, #equipo}, que es clave en T7. No se pierden DFs dado que por validacion simple, DF3 y DF4 valen en T7, ya que son equivalentes, lo que significa que producen el mismo conjunto de atributos. En este punto se perfila CC1 como clave primaria del esquema. Además, DF3 vale en T8.

- **T7** está en BCNF ya que solo valen la DF3 y DF4 cuyos determinantes {#torneo, año, #equipo} y {#torneo, año, puesto} son superclave del esquema
- **T8** está en BCNF ya que todos los atributos que la componen son iguales a la CC1, por lo tanto cualquier DF que pueda hallarse va a ser trivial.
  

### Esquemas en BCNF

- **T1)** (**#torneo**, nombre_torneo)  
- **T3)** (**#reglamentacion**, descripcion)
- **T5)** (**#equipo**, nombre_equipo, estadio_equipo)
- **T7)** (<u>**"#torneo"**</u>, <u>**"año"**</u>, <u>**#equipo**</u>, **"puesto"**)  
- **T8)** (<u>**#torneo**</u>, <u>**año**</u>, <u>**#equipo**</u>, <u>**#reglamentacion**</u>, <u>**#auspiciante**</u>)

**Clave Primaria: {#torneo, #equipo, año, #reglamentacion, #auspiciante}**

## Dependencias multivaluadas a partir de T8

DM1: `#torneo, año ->> #auspiciante`
DM2: `#torneo, año ->> #equipo`
DM3: `{} ->> #reglamentacion`

## Análisis de 4FN

**T8** no está en 4FN ya que existe al menos una DM no trivial, por ejemplo DM3.

- Por lo tanto, particiono T8 a partir de DM4:
  - **T9)** (**#reglamentacion**)
  - **T10)** (<u>**#torneo**</u>, <u>**"año"**</u>, <u>**#equipo**</u>, <u>**#auspiciante**</u>)

- **T9** está en 4FN ya que solo vale DM3, que es trivial.
- **T10** no está en 4FN ya que existe al menos un DM no trivial, por ejemplo DM2

- Por lo tanto, particiono T10 a partir de DM2:
  - **T11)** (**#equipo**)
  - **T12)** (<u>**#torneo**</u>, <u>**"año"**</u>, <u>**#auspiciante**</u>)

- **T11** está en 4FN ya que solo vale DM2, que es trivial.
- **T12** está en 4FN ya que solo vale DM1, que es trivial.

### Esquemas en 4FN 

- **T1)** (**#torneo**, nombre_torneo)  
- **T3)** (**#reglamentacion**, descripcion)
- **T5)** (**#equipo**, nombre_equipo, estadio_equipo)
- **T7)** (<u>**"#torneo"**</u>, <u>**"año"**</u>, <u>**#equipo**</u>, **"puesto"**)
- **T9)** (**#reglamentacion**)
- **T11)** (**#equipo**)
- **T12)** (<u>**#torneo**</u>, <u>**"año"**</u>, <u>**#auspiciante**</u>)

- Como **T11** es proyección de **T5**, **T9** es proyección de **T3**, se eliminan **T11** y **T9**

### Esquemas en 4FN y que no son proyecciones de otros 

- **T1)** (**#torneo**, nombre_torneo)  
- **T3)** (**#reglamentacion**, descripcion)
- **T5)** (**#equipo**, nombre_equipo, estadio_equipo)
- **T7)** (<u>**"#torneo"**</u>, <u>**"año"**</u>, <u>**#equipo**</u>, **"puesto"**)
- **T12)** (<u>**#torneo**</u>, <u>**"año"**</u>, <u>**#auspiciante**</u>)