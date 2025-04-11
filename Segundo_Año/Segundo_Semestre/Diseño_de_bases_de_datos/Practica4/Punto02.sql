/*1. Listar especie, años, calle, nro y localidad de árboles podados por el podador ‘Juan Perez’ y por
el podador ‘Jose Garcia’.*/
SELECT a.especie, a.anios, a.calle, a.nro, l.nombreL
FROM arbol a NATURAL JOIN Poda p 
             NATURAL JOIN Podador po
             NATURAL JOIN Localidad l
WHERE po.nombre = 'Juan Perez'
INTERSECT
SELECT a.especie, a.anios, a.calle, a.nro, l.nombreL
FROM arbol a NATURAL JOIN Poda p 
             NATURAL JOIN Podador po
             NATURAL JOIN Localidad l
WHERE po.nombre = 'Jise Garcia'
/*2. Reportar DNI, nombre, apellido, fecha de nacimiento y localidad donde viven de aquellos
podadores que tengan podas realizadas durante 2023.*/
SELECT po.DNI, po.nombre, po.apellido, po.fechaNac, l.nombreL
FROM Podador po NATURAL JOIN Poda p
                INNER JOIN Localidad l ON (po.codigoPostalVive = l.codigoPostal)
WHERE YEAR(p.fecha) = 2023
/*3. Listar especie, años, calle, nro y localidad de árboles que no fueron podados nunca.*/
SELECT a.especie, a.anios, a.calle, a.nro, l.nombreL
FROM arbol a 
WHERE a.idArbol NOT IN (SELECT a.idArbol
                        FROM arbol a NATURAL JOIN Poda p)
/*4. Reportar especie, años,calle, nro y localidad de árboles que fueron podados durante 2022 y no
fueron podados durante 2023.*/
SELECT a.especie, a.anios, a.calle, a.nro, l.nombreL
FROM arbol a NATURAL JOIN Poda p
             NATURAL JOIN Localidad l
WHERE YEAR(p.fecha) = 2022
EXCEPT
SELECT a.especie, a.anios, a.calle, a.nro, l.nombreL
FROM arbol a NATURAL JOIN Poda p
             NATURAL JOIN Localidad l
WHERE YEAR(p.fecha) = 2023
/*5. Reportar DNI, nombre, apellido, fecha de nacimiento y localidad donde viven de aquellos
podadores con apellido terminado con el string ‘ata’ y que tengan al menos una poda durante
2024. Ordenar por apellido y nombre.*/
SELECT DISTINCT po.DNI, po.nombre, po.apellido, po.fechaNac, l.nombreL
FROM Podador po NATURAL JOIN Poda p
                INNER JOIN Localidad l ON (po.codigoPostalVive = l.codigoPostal)
WHERE po.apellido LIKE '%ata' AND YEAR(p.fecha) = 2024
/*6. Listar DNI, apellido, nombre, teléfono y fecha de nacimiento de podadores que solo podaron
árboles de especie ‘Coníferas’.*/
SELECT po.DNI, po.apellido, po.nombre, po.telefono, po.fnac
FROM Podador po NATURAL JOIN Poda p
                NATURAL JOIN Arbol a
WHERE a.especie = 'Coníferas'
EXCEPT
SELECT po.DNI, po.apellido, po.nombre, po.telefono, po.fnac
FROM Podador po NATURAL JOIN Poda p
                NATURAL JOIN Arbol a
WHERE a.especie <> 'Coníferas'
/*7. Listar especies de árboles que se encuentren en la localidad de ‘La Plata’ y también en la
localidad de ‘Salta’.*/
SELECT a.especie
FROM arbol a NATURAL JOIN Localidad l
WHERE l.nombreL = 'La Plata'
INTERSECT   
SELECT a.especie
FROM arbol a NATURAL JOIN Localidad l
WHERE l.nombreL = 'Salta'
/*8. Eliminar el podador con DNI 22234566.*/
DELETE FROM Podador
WHERE dni = 22234566
/*9. Reportar nombre, descripción y cantidad de habitantes de localidades que tengan menos de 100
árboles.*/
SELECT l.nombreL, l.descripcion, l.#habitantes
FROM Localidad l NATURAL JOIN Arbol a
GROUP BY l.codigoPostal, l.nombreL, l.descripcion, l.#habitantes
HAVING COUNT(*) < 100
