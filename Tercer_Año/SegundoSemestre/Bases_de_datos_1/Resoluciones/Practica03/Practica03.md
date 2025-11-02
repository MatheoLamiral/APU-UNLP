# Práctica 3 - Álgebra relacional

## Primera parte

### Ejercicio 1

- Inciso a
  - Indique cuál/es de las siguientes operaciones son válidas
  - [ ] A(a,b,c) U B(a,b,d)
  - [x] ( A(a,b,c) |X| B(a,b) ) - C(a,b,c)
  - [x] ( A(a,b,c) |X| B(a,d,e) ) ∩ D(a,b,c,d,e)
  - [ ] ( A(a,b,c) X B (a,b,d) ) ∩ D(a,b,c,d)

- Inciso b
  - Para la operación de resta es necesario que los esquemas involucrados sean compatibles, es decir, deben cumplir las siguientes condiciones:
  - [x] Deben tener la misma cantidad de columnas
  - [x] Las columnas deben ser del mismo dominio
  - [x] El orden de los columnas debe ser el mismo
  - [x] Las columnas deben tener igual nombre
  
### Ejercicio 2

¿Para cuáles de las siguientes operaciones es necesario que los operandos sean unión compatibles? Marque todas las opciones correctas:

- [x] resta -
- [ ] división %
- [x] unión U
- [ ] producto cartesiano X
- [ ] producto natural |X|

### Ejercicio 3

Dados los siguientes esquemas:

COMPRA(#compra, fecha, monto_total)
COMPRA_PRODUCTO(#compra, cantidad, #producto)
PRODUCTO(#producto, nombre, precio)

Indique qué formato (conjunto de atributos) tiene el resultado de aplicar la siguiente operación.

`COMPRA_PRODUCTO % ∏#productoPRODUCTO`

- [x] (#compra, cantidad)
- [ ] (#compra, cantidad, #producto)
- [ ] (#compra)

### Ejercicio 4

Dado el siguiente esquema:

PASAJERO (#pasajero, nombre, dni, puntaje)
PASAJERO_RESERVA (#pasajero, #reserva)
RESERVA (#reserva, #vuelo, fecha_reserva, monto, #asiento)
VUELO (#vuelo, aeropuerto_salida, aeropuerto_destino, fecha_vuelo)

Indicar si las siguientes consultas obtienen el resultado correcto (sin tener en cuenta la optimización).

- Inciso a
  - Obtener los pasajeros que tengan reservas sobre vuelos del próximo año, listando #pasajero, #vuelo y #asiento.
  - `VUELOS_PROX_AÑO <— σ fecha_vuelo >= 1/1/2026 AND fecha_vuelo <= 31/12/2026 VUELO`
  - `RES <— Π #pasajero,#vuelo,#asiento ( VUELOS_PROX_AÑO |X| RESERVA |X| PASAJERO_RESERVA)`
  - La consulta es **correcta**, en VUELOS_PROX_AÑO se filtran los vuelos del próximo año y luego se hace el producto cartesiano con RESERVA y PASAJERO_RESERVA para obtener los datos solicitados.
- Inciso b
  - Obtener el listado de montos de reservas realizadas para vuelos efectuados el pasado Agosto desde Buenos Aires a Córdoba.
  - `VUELOS_BUE_CBA <— σ ciudad_salida=“Buenos Aires” AND ciudad_destino=“Córdoba” VUELO`
  - `RESERV_AGO <— ( σ fecha_reserva >= 1/8/2025 AND fecha_reserva <= 31/8/2025 RESERVA) |X| VUELOS_BUE_CBA`
  - `RES <— Π monto RESERV_AGO`
  - La consulta es **correcta**, en VUELOS_BUE_CBA se filtran los vuelos desde Buenos Aires a Córdoba y luego se hace el producto cartesiano con las reservas realizadas en Agosto para obtener los montos solicitados.
- Inciso c
  - Obtener el/los pasajeros que solo hayan reservado vuelos cuyo aeropuerto de salida sea el aeropuerto “Ministro Pistarini”. Listar el nombre y dni de los pasajeros.
  - `VUELOS_PISTARINI <- Π #vuelo (σ aeropuerto_salida=“Ministro Pistarini” VUELO )`
  - `RESERVA_PISTARINI <- Π #pasajero (VUELOS_PISTARINI |X| RESERVA)`
  - `PASAJEROS_PISTARINI <- Π nombre,dni (RESERVA_PISTARINI |X| PASAJERO)`
  - La consulta es **incorrecta**, ya que no se está considerando a los pasajeros que hayan reservado vuelos con aeropuerto de salida a "Ministro Pistarini" y otros más. La consulta debería incluir una operación de resta para excluir esos pasajeros.
- Inciso d
  - Obtener el/los id/s de los pasajeros que hayan realizado reservas por un monto superior a $99000
  - `RESERVAS_MAS_99000 <- Π #pasajero (σmonto < 99000 RESERVA )`
  - La consulta es **incorrecta**, ya que el #pasajero no se encuentra en el esquema RESERVA. La consulta debería incluir una operación de producto cartesiano con PASAJERO_RESERVA para obtener el #pasajero. Además se esta seleccionando por monto menor a 99000 en lugar de mayor.

## Segunda parte

### Ejercicio 5

**DUEÑO** ( id_dueño, nombre, teléfono, dirección, dni )  
**CHOFER** ( id_chofer, nombre, teléfono, dirección, fecha_licencia_desde, fecha_licencia_hasta, dni )  
**AUTO** ( patente, id_dueño, id_chofer, marca, modelo, año )  
**VIAJE** ( patente, hora_desde, hora_hasta, origen, destino, tarifa, metraje )  

- Inciso a: Listar el dni, nombre y teléfono de todos los dueños que NO son choferes  
  - <i>Dueños_no_choferes ⇐ (π<sub>dni</sub>(DUEÑO) - π<sub>dni</sub>(CHOFER))</i>  
  - <i>π<sub>dni, nombre, teléfono</sub> (Dueños_no_choferes |X| DUEÑO)</i>  
- Inciso b: Listar la patente y el id_chofer de todos los autos a cuyos choferes les caduca la licencia el 01/01/2026  
  - <i>Choferes_caducan_licencia ⇐ π<sub>id_chofer</sub>(σ<sub>fecha_licencia_hasta=01/01/2026</sub>(CHOFER))</i>  
  - <i>π<sub>patente, id_chofer</sub>((AUTO) |X| Choferes_caducan_licencia)</i>

### Ejercicio 6

**ESTUDIANTE** ( #legajo, nombreCompleto, nacionalidad, añoDeIngreso, códigoDeCarrera )  
**CARRERA** ( códigoDeCarrera, nombre )  
**INSCRIPCIONAMATERIA** ( #legajo, códigoDeMateria)  
**MATERIA** (códigoDeMateria, nombre)  

- Inciso a: Obtener el nombre de los estudiantes que ingresaron en 2023  
  - <i> π<sub>nombreCompleto</sub>(σ<sub>añoDeIngreso=2023</sub>(ESTUDIANTE))</i>
- Inciso b: Obtener el nombre de los estudiantes con nacionalidad “Argentina” que NO estén en la carrera con código “LI07”    
  - <i> Estudiantes_argentinos ⇐ π<sub>legajo</sub>(σ<sub>nacionalidad=“Argentina”</sub>(ESTUDIANTE))</i>  
  - <i> Estudiantes_LI07 ⇐ π<sub>legajo</sub>(σ<sub>códigoDeCarrera=“LI07”</sub>(ESTUDIANTE))</i>  
  - <i> π<sub>nombreCompleto</sub>(Estudiantes_argentinos - Estudiantes_LI07)</i>  
- Inciso c: Obtener el legajo de los estudiantes que se hayan anotado en TODAS las materias.
  - <i> INSCRIPCIONMATERIA % π<sub>códigoDeMateria</sub>(MATERIA)</i>
  - **NOTA**: Como inscripción materia ya tiene el atributo legajo, no hace falta relacionarlo nuevamente con estudiante. Además al realizar la división, el resultado ya va a ser el legajo de los estudiantes que se hayan anotado en todas las materias, por lo que no hace falta realizar ninguna proyección adicional.

### Ejercicio 7

**LUGAR_TRABAJO** ( #empleado, #departamento )  
**CURSO_EXIGIDO** ( #departamento, #curso )  
**CURSO_REALIZADO** ( #empleado, #curso )  

- Inciso a: ¿Quiénes son los empleados que han hecho todos los cursos, independientemente de qué departamento los exija?
  - <i> TODOS_LOS_CURSOS ⇐ π<sub>#curso</sub>(CURSO_EXIGIDO)</i>
  - <i> π<sub>#empleado</sub>(CURSO_REALIZADO % TODOS_LOS_CURSOS)</i>
- Inciso b: ¿Quiénes son los empleados que ya han realizado todos los cursos exigidos por sus departamentos?
  - <i> NO_HICIERON_TODOS_LOS_CURSOS ⇐ π<sub>#empleado, #curso</sub>(LUGAR_TRABAJO |X| CURSO_EXIGIDO) - CURSO_REALIZADO</i>
  - <i> π<sub>#empleado</sub>(LUGAR_TRABAJO) - π<sub>#empleado</sub>(NO_HICIERON_TODOS_LOS_CURSOS)</i>
  
### Ejercicio 8

**TIPOMUEBLE** ( id_tipomueble, descripción )  
**FABRICANTE** ( id_fabricante,nombrefabricante, cuit )  
**TIPOMADERA** ( id_tipomadera, nombremadera )  
**AMBIENTE** ( id_ambiente, descripcionambiente )  
**MUEBLE** ( id_mueble, id_tipomueble, id_fabricante, id_tipomadera, precio, dimensiones, descripcion )  
**MUEBLEAMBIENTE** ( id_mueble, id_ambiente )  

- **Inciso a: Obtener los nombres de los fabricantes que fabrican muebles en todos los tipos de madera.**
  - <i>FABRICAN_TODO_TIPO_MADERA ⇐ π<sub>id_fabricante, id_tipomadera</sub>(MUEBLE |X| FABRICANTE) % π<sub>id_tipomadera</sub>(TIPOMADERA)</i>
  - <i>π<sub>nombrefabricante</sub>(FABRICAN_TODO_TIPO_MADERA |X| FABRICANTE)</i>
- **Inciso b: Obtener los nombres de los fabricantes que sólo fabrican muebles en Pino.**
  - FABRICAN_EN_PINO ⇐ π<sub>id_fabricante</sub>(σ<sub>nombremadera=“Pino”</sub>(MUEBLE |X| TIPOMADERA))
  - NO_FABRICAN_EN_PINO ⇐ π<sub>id_fabricante</sub>(σ<sub>nombremadera<>“Pino”</sub>(MUEBLE |X| TIPOMADERA))
  - π<sub>nombrefabricante</sub>((FABRICAN_EN_PINO - NO_FABRICAN_EN_PINO) |X| FABRICANTE)
- **Inciso c: Obtener los nombres de los fabricantes que fabrican muebles para todos los ambientes.**
  - FABRICAN_TODO_AMBIENTE ⇐ π<sub>id_fabricante, id_ambiente</sub>(MUEBLE |X| MUEBLEAMBIENTE) % π<sub>id_ambiente</sub>(AMBIENTE)
  - π<sub>nombrefabricante</sub>(FABRICAN_TODO_AMBIENTE |X| FABRICANTE)
- **Inciso d: Obtener los nombres de los fabricantes que sólo fabrican muebles para oficina.**
  - FABRICAN_PARA_OFICINA ⇐ π<sub>id_fabricante</sub>(σ<sub>descripcionambiente=“oficina”</sub>(MUEBLE |X| MUEBLEAMBIENTE |X| AMBIENTE))
  - NO_FABRICAN_PARA_OFICINA ⇐ π<sub>id_fabricante</sub>(σ<sub>descripcionambiente<>“oficina”</sub>(MUEBLE |X| MUEBLEAMBIENTE |X| AMBIENTE))
  - π<sub>nombrefabricante</sub>((FABRICAN_PARA_OFICINA - NO_FABRICAN_PARA_OFICINA) |X| FABRICANTE)
- **Inciso e: Obtener los nombres de los fabricantes que sólo fabrican muebles para baño y cocina.**
  - FABRICAN_PARA_BAÑO ⇐ π<sub>id_fabricante</sub>(σ<sub>descripcionambiente=“baño”</sub>(MUEBLE |X| MUEBLEAMBIENTE |X| AMBIENTE))
  - FABRICAN_PARA_COCINA ⇐ π<sub>id_fabricante</sub>(σ<sub>descripcionambiente=“cocina”</sub>(MUEBLE |X| MUEBLEAMBIENTE |X| AMBIENTE))
  - FABRICAN_PARA_BAÑO_Y_COCINA ⇐ FABRICAN_PARA_BAÑO ∩ FABRICAN_PARA_COCINA
  - NO_FABRICAN_NI_BAÑO_NI_COCINA ⇐ π<sub>id_fabricante</sub>(σ<sub>descripcionambiente<>“baño” AND descripcionambiente<>“cocina”</sub>(MUEBLE |X| MUEBLEAMBIENTE |X| AMBIENTE))
  - π<sub>nombrefabricante</sub>((FABRICAN_PARA_BAÑO_Y_COCINA - NO_FABRICAN_NI_BAÑO_NI_COCINA) |X| FABRICANTE)
- **Inciso f: Obtener los nombres de los fabricantes que producen muebles de cedro y roble.**
  - FABRICAN_CEDRO ⇐ π<sub>id_fabricante</sub>(σ<sub>nombremadera=“cedro”</sub>(MUEBLE |X| TIPOMADERA))
  - FABRICAN_ROBLE ⇐ π<sub>id_fabricante</sub>(σ<sub>nombremadera=“roble”</sub>(MUEBLE |X| TIPOMADERA))
  - π<sub>nombrefabricante</sub>( (FABRICAN_CEDRO ∩ FABRICAN_ROBLE) |X| FABRICANTE )
- **Inciso g: Obtener los nombres de los fabricantes que producen muebles de melamina o MDF.**
  - FABRICAN_MELAMINA ⇐ π<sub>id_fabricante</sub>(σ<sub>nombremadera=“melamina”</sub>(MUEBLE |X| TIPOMADERA))
  - FABRICAN_MDF ⇐ π<sub>id_fabricante</sub>(σ<sub>nombremadera=“MDF”</sub>(MUEBLE |X| TIPOMADERA))
  - π<sub>nombrefabricante</sub>( (FABRICAN_MELAMINA ∪ FABRICAN_MDF) |X| FABRICANTE )