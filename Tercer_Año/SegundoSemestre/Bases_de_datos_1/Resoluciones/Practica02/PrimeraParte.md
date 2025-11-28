# Práctica 2 - Normalización
## Parte 1
### Ejercicio 1

- El esquema tiene más de una calve candidata 

DF1) idMapa -> proyeccion, escalaMapa  
DF2) idSitioWeb -> dominioSitioWeb, especialidadSitioWeb  
DF3) dominioSitioWeb -> idSitioWeb, especialidadSitioWeb  
DF4) idMapa, idSitioWeb, fechPublicacion -> valorPublicacion  
DF5) idMapa, dominioSitioWeb, fechPublicacion -> valorPublicacion  

CC1: {idMapa, idSitioWeb, fechPublicacion, dueñosSitioWeb}  
CC2: {idMapa, dominioSitioWeb, fechPublicacion, dueñosSitioWe} 
 
### Ejercicio 2

La CC correcta es {a,f} ya que `a -> b,c`, y `c -> d,e`, por ende, por transitividad, `a -> b,c,d,e`, y como `f` no es determinado por nadie, debe formar parte de la clave

### Ejercicio 3

La opción correcta es la B
- La relación ALUMNO tiene dos CC
  - {DNI, #libroUsadoEnCarrera}
  - {nroLegajo, #libroUsadoEnCarrera}
- Pero solo pordrá ser identificada por una única clave primaria por restricciones de diseño de BD

### Ejercicio 4

`#aplicacion -> nombre_aplicacion, descripcion, #categoria ` 
`#actualizacion, #aplicacion -> descripcion_cambios `  
`#desarrollador -> nombre_apellido_desarrollador ` 

1. No es válida, ya que la clave {#aplicacion, #actualizacion} no es minimal. Puedo determinar nombre_aplicacion y descripcion solo con #aplicacion
2. Válida y mínima
3. No es válida, ya que el nombre_apellido_desarrollador puede repetirse para diferentes #desarrollador, por ende, no lo determina
4. Válida y minima
5. ...

### Ejercicio 5

- `#curso ->> año_edicion` : no es válida a la vez, no cumple con independencia, ya que luego aparece como determinante en la DM #curso,año_edicion ->> calificacion
- `#curso ->> referencia`: Es válida a la vez con #curso,año_edicion ->> calificacion
- `referencia ->> #curso`: No es válida ya que no cumple independencia
- `año_edicion ->> #curso`: No es válida, ya que no cumple con independencia
  
- no se mensiona `#curso, año_edicion ->> #nro_modulo` 

- **Inciso a**
  -  Son multivaluadas válidas y triviales: 
     -  DM2:  `#curso, año ->> #profesor`
        -  Es trivial ya que la union de los atributos (#curso, año) y #profesor, son todos los atributos del esquema R1
- **Inciso b**
  - Son un conjunto de multivaluadas válidas:
    - DM3: `#Linea, #Ramal ->> #Colectivo `
    - DM6: `{ } ->> dniEmpleado`
- **Inciso c**
  - DM1, `#pelicula ->> #autor`: Es válida, ya que una película es realizada por varios autores
  - DM2, `#pelicula ->> #actor`: Es válida, ya que en una película participan varios actores
  - DM3, `#pelicula ->> #actor, #autor`: No es válida, ya que se multideterminan por separado, no son un par que tengan que estar juntos 
  - DM4, `#pelicula, #autor ->> #actor`: No es válida, ya que #autor no multidetermina a #actor
  - DM5, `#auspiciante ->> #pelicula`: No es válida ya que el auspiciante no multidetermina a la película
  - DM6, `#pelicula ->> #auspiciante`: Es válida ya que el auspiciante es multideterminado por un rodaje de una película
  - DM7, `#pelicula ->> #equipo_rodaje`: Es válida ya que el equipo de rodaje es multideterminado por un rodaje de una película
  - DM8, `{ } ->> #equipo_rodaje`: No es válida ya que el equipo de rodaje es multideterminado por un rodaje de una película
  
  El esquema no está en 4FN ya que ninguna de las DM son triviales

- **Inciso d**
- **Inciso e**


 