/*1. Listar DNI, nombre, apellido,dirección y email de integrantes nacidos entre 1980 y 1990 y que
hayan realizado algún recital durante 2023.*/
SELECT i.DNI,I.nombre, i.apellido, i.dirección, i.email
FROM Integrates i INNER JOIN Banda b ON(i.codigoB = b.codigoB)
                  INNER JOIN Recital r ON(b.codigoB = r.codigoB)
WHERE (YEAR(r.fecha) = 2023)AND((YEAR(i.fecha_nacimiento)>= 1980)AND(YEAR(i.fecha_nacimiento)<= 1990))
/*2. Reportar nombre, género musical y año de creación de bandas que hayan realizado recitales
durante 2023, pero no hayan tocado durante 2022 .*/
SELECT b.nombre, b.genero_musical, b.año_creacion
FROM Banda b INNER JOIN Recital r ON(b.codigoB = r.codigoB)
WHERE (YEAR(r.fecha) = 2023)
EXCEPT
SELECT b.nombre, b.genero_musical, b.año_creacion
FROM Banda b INNER JOIN Recital r ON(b.codigoB = r.codigoB)
WHERE (YEAR(r.fecha) = 2022)
/*3. Listar el cronograma de recitales del día 04/12/2023. Se deberá listar nombre de la banda que
ejecutará el recital, fecha, hora, y el nombre y ubicación del escenario correspondiente.*/
SELECT b.nombre, r.fecha, r.hora, e.nombre, e.ubicacion
FROM Recital r INNER JOIN Banda b ON(r.codigoB 0 b.codigoB)
               INNER JOIN Escenario e ON (r.nroEscenario = e.nroEscenario)
WHERE r.fecha = 4-12-2023
/*4. Listar DNI, nombre, apellido,email de integrantes que hayan tocado en el escenario con nombre
‘Gustavo Cerati’ y en el escenario con nombre ‘Carlos Gardel’.*/
SELECT i.DNI,I.nombre, i.apellido, i.email
FROM Integrate i INNER JOIN Banda b ON(i.codigoB = b.codigoB)
               INNER JOIN Recital r ON(r.codigoB 0 b.codigoB)
               INNER JOIN Escenario e ON (r.nroEscenario = e.nroEscenario)
WHERE e.nombre_escenario = 'Gustavo Cerati'
INTERSECT
SELECT i.DNI,I.nombre, i.apellido, i.email
FROM Integrate i INNER JOIN Banda b ON(i.codigoB = b.codigoB)
               INNER JOIN Recital r ON(r.codigoB 0 b.codigoB)
               INNER JOIN Escenario e ON (r.nroEscenario = e.nroEscenario)
WHERE e.nombre_escenario = 'Carlos Gardel '
/*5. Reportar nombre, género musical y año de creación de bandas que tengan más de 8 integrantes.*/
/*6. Listar nombre de escenario, ubicación y descripción de escenarios que solo tuvieron recitales
con el género musical rock and roll. Ordenar por nombre de escenario*/
/*7. Listar nombre, género musical y año de creación de bandas que hayan realizado recitales en
escenarios cubiertos durante 2023.// cubierto es true, false según corresponda*/
/*8. Reportar para cada escenario, nombre del escenario y cantidad de recitales durante 2024.*/
/*9. Modificar el nombre de la banda ‘Mempis la Blusera’ a: ‘Memphis la Blusera’.
Página 2 de 8*/
