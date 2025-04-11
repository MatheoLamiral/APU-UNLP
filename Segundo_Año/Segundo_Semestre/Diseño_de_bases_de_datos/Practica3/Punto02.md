1. Listar datos personales de integrantes con apellido 'Garcia' o fecha de nacimiento anterior a 2005, que toquen en bandas de Rock and Roll
   - <i>π<sub>DNI, nombre, apellido, dirección, email, fecha_nacimiento</sub></i>(<i>σ<sub>(Banda.genero_musical = 'Rock and Roll')^((Integrante.apellido = 'Garcia')v(Integrante.fecha_nacimiento <= 31/12/2005))</sub></i>(Banda |x| Integrante))
2. Listar nombre de escenario, ubicación y descripción de escenarios que no tuvieron recitales durante 2019
   - <i>π<sub>nombre_escenario, ubicación, descripción</sub></i>(<i>π<sub>nombre_escenario, ubicación, descripción</sub></i>(<i>σ<sub>(Recital.fecha < 01/01/2019)^(Recital.fecha > 31/12/2019)</sub></i>(Escenario |x| Recital)) - (<i>π<sub>nombre_escenario, ubicación, descripción</sub></i>(Escenario |x| Recital))
3. Listar nombre de escenario, ubicación y descripción de escenarios que tuvieron recitales con género musical rock and roll o tuvieron recitales durante 2020.
   - <i>π<sub>nombre_escenario, ubicación, descripción</sub></i>(<i>σ<sub>(Banda.genero_musical = 'rock and roll')v((Recital.fecha < 01/01/2019)^(Recital.fecha > 31/12/2019))</sub>(Escenario |x| Banda |x| Recital |x|)</i>)
4. Listar nombre, género musical y año de creación de bandas que hayan realizado recitales en escenarios cubiertos durante 2019. // cubierto es true, false según corresponda
   - <i>π<sub>nombreBanda, genero_musical, año_creación</sub>(<i>σ<sub>(cubierto = true)^((Recital.fecha >= 01/01/2019)^(Recital.fecha <= 31/12/2019))</sub>(Bandas)</i>)</i>
5. Listar DNI, nombre, apellido, dirección y email de integrantes nacidos entre 2000 y 2005 y que toquen en bandas con género pop que hayan tenido recitales durante 2020.
   - <i>π<sub>DNI, nombre, apellido, dirección, email</sub>(<i>σ<sub>((Integrante.fecha_nacimiento >= 01/01/2000)^(Integrante.fecha_nacimiento <= 31/12/2005))^(Banda.genero_musical = 'pop')^((Recital.fecha >= 01/01/2020)^(Recital.fecha <= 31/12/2020))</sub>(Integrante |x| Banda |x| Recital)</i>)</i>
6. Listar DNI, nombre, apellido,email de integrantes que hayan tocado en el escenario con nombre ‘Gustavo Cerati’ y no hayan tocado en el escenario con nombre ‘Carlos Gardel’.
   - <i>π<sub>DNI, nombre, apellido, dirección, email</sub>((<i>σ<sub>(Escenario.nombre_escenario = 'Gustavo Cerati')</i>(Escenario |x| Integrante |x| Recital)) - (<i>σ<sub>(Escenario.nombre_escenario = 'Carlos Gardel')</i>(Escenario |x| Integrante |x| Recital)))</i>
7. Modificar el año de creación de la banda de nombre ‘Ratones Paranoicos’ a: 1983.
   - δ año_creación ⇐ 1983 (<i>σ<sub>(nombreBanda = 'Ratones Paranoicos')</sub>(Banda)</i>)
8. Reportar nombre, género musical y año de creación de bandas que hayan realizado recitales durante 2019, y además hayan tocado durante 2020.
   - Durante2019 ⇐ <i>π<sub>nombreBanda, genero_musical, año_creación</sub>(<i>σ<sub>(Recital.fecha >= 01/01/2019)^(Recital.fecha <= 31/12/2019)</sub>(Recital |x| Banda)</i>)</i>
   - Durante2020 ⇐ <i>π<sub>nombreBanda, genero_musical, año_creación</sub>(<i>σ<sub>(Recital.fecha >= 01/01/2020)^(Recital.fecha <= 31/12/2020)</sub>(Recital |x| Banda)</i>)</i>
   - DuranteAmbos ⇐ Durante2019 U Durante2020
9.  Listar el cronograma de recitales del día 04/12/2019. Se deberá listar: nombre de la banda que ejecutará el recital, fecha, hora, y el nombre y ubicación del escenario correspondiente
       - <i>π<sub>nombreBanda, fecha, hora, nombre_escenario, ubicación</sub>(<i>σ<sub>(Recital.fecha = '04/12/2019')</sub>(Recital |x| Escenario |x| Banda)</i>)</i>  