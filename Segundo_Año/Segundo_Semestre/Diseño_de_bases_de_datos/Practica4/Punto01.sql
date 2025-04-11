/*1. Listar datos personales de clientes cuyo apellido comience con el string ‘Pe’. Ordenar por DNI*/
- SELECT nombre, apellido, DNI, telefono, direccion
  FROM cliente
  WHERE apellido LIKE 'Pe%'
  ORDER BY DNI
/*2. Listar nombre, apellido, DNI, teléfono y dirección de clientes que realizaron compras solamente
durante 2017.*/
- SELECT c.nombre, c.apellido, c.DNI, c.telefono, c.direccion
  FROM cliente c 
  WHERE c.idCliente IN (SELECT f.idCliente
                  		FROM factura f
                  		WHERE f.fecha BETWEEN '2017-01-01' AND '2017-12-31')
  AND c.idCliente NOT IN (SELECT f.idCliente
                    	  FROM factura f
                    	  WHERE f.fecha NOT BETWEEN '2017-01-01' AND '2017-12-31')
                          ==

SELECT c.nombre, c.apellido, c.DNI, c.telefono, c.direccion
  FROM cliente c 
  WHERE (YEAR(f.fecha) = 2017) AND (C.idCliente NOT IN(
    SELECT c.idCliente
    FROM cliente
    WHERE (YEAR(f.fecha) <> 2017)
  ))
/*3. Listar nombre, descripción, precio y stock de productos vendidos al cliente con DNI 45789456,
pero que no fueron vendidos a clientes de apellido ‘Garcia’.*/
- SELECT p.nombreP, p.descripcion, p.precio, p.stockt
  FROM producto p
  WHERE p.idProducto IN (SELECT p.idProducto
                             FROM producto p 
                             INNER JOIN detalle d ON (p.idProducto = d.idProducto)
                             INNER JOIN factura f ON (d.nroTicket = f.nroTicket)
                             INNER JOIN cliente c ON (f.idCliente = c.idCliente)AND(c.DNI = '45789456')
                            )
  AND p.idProducto NOT IN (SELECT p.idProducto
                             FROM producto p 
                             INNER JOIN detalle d ON (p.idProducto = d.idProducto)
                             INNER JOIN factura f ON (d.nroTicket = f.nroTicket)
                             INNER JOIN cliente c ON (f.idCliente = c.idCliente)AND(c.apellido = 'Garcia')
                            )
                            ==
SELECT p.nombreP, p.descripcion, p.precio, p.stockt
FROM producto p
INNER JOIN detalle d ON (p.idProducto = d.idProducto)
INNER JOIN factura f ON (d.nroTicket = f.nroTicket)
INNER JOIN cliente c ON (f.idCliente = c.idCliente)
WHERE (c.DNI = '45789456')
EXCEPT(
    SELECT p.nombreP, p.descripcion, p.precio, p.stockt
    FROM producto p
    INNER JOIN detalle d ON (p.idProducto = d.idProducto)
    INNER JOIN factura f ON (d.nroTicket = f.nroTicket)
    INNER JOIN cliente c ON (f.idCliente = c.idCliente)
    WHERE (c.apellido = 'Garcia')
)
/*4. Listar nombre, descripción, precio y stock de productos no vendidos a clientes que tengan
teléfono con característica 221 (la característica está al comienzo del teléfono). Ordenar por
nombre.*/
- SELECT p.nombreP, p.descripcion, p.precio, p.stock
  FROM producto p
  WHERE p.idProducto NOT IN (SELECT p.idProducto
                             FROM producto p 
                             INNER JOIN detalle d ON (p.idProducto = d.idProducto)
                             INNER JOIN factura f ON (d.nroTicket = f.nroTicket)
                             INNER JOIN cliente c ON (f.idCliente = c.idCliente)AND(c.telefono LIKE '221%')
                            )
  ORDER BY p.nombreP
/*5. Listar para cada producto nombre, descripción, precio y cuantas veces fue vendido. Tenga en
cuenta que puede no haberse vendido nunca el producto.*/
- SELECT p.nombreP, p.descripcion, p.precio, SUM(d.cantidad)
  FROM Producto p
  LEFT JOIN Detalle d ON (p.idProducto = d.idProducto)
  GROUP BY p.idProducto, p.nombreP, p.descripcion, p.precio
/*6. Listar nombre, apellido, DNI, teléfono y dirección de clientes que compraron los productos con
nombre ‘prod1’ y ‘prod2’ pero nunca compraron el producto con nombre ‘prod3’.*/
SELECT c.nombre, c.apellido, c.DNI, c.telefono, c.direccion
FROM Cliente c NATURAL JOIN Factura f
               NATURAL JOIN Detalle d
               NATURAL JOIN Producto p
WHERE p.nombreP = 'prod1'
INTERSECT
SELECT c.nombre, c.apellido, c.DNI, c.telefono, c.direccion
FROM Cliente c NATURAL JOIN Factura f
               NATURAL JOIN Detalle d
               NATURAL JOIN Producto p
WHERE p.nombreP = 'prod2'  
EXCEPT
SELECT c.nombre, c.apellido, c.DNI, c.telefono, c.direccion
FROM Cliente c NATURAL JOIN Factura f
               NATURAL JOIN Detalle d
               NATURAL JOIN Producto p
WHERE p.nombreP = 'prod3'  
/*7. Listar nroTicket, total, fecha, hora y DNI del cliente, de aquellas facturas donde se haya
comprado el producto ‘prod38’ o la factura tenga fecha de 2019.*/
SELECT f.nroTicket, f.todas, f.fecha, f.hora, c.DNI
FROM Factura f NATURAL JOIN Detalle d
               NATURAL JOIN Producto p
               NATURAL JOIN Cliente c
WHERE p.nombreP = 'prod38' OR YEAR(f.fecha) = 2019
/*8. Agregar un cliente con los siguientes datos: nombre:’Jorge Luis’, apellido:’Castor’, DNI:
40578999, teléfono: ‘221-4400789’, dirección:’11 entre 500 y 501 nro:2587’ y el id de cliente:
500002. Se supone que el idCliente 500002 no existe.*/
INSERT INTO Cliente (idCliente, nombre, apellido, DNI, telefono, direccion)
VALUES (500002, 'Jorge Luis', 'Castor', 40578999, '221-4400789', '11 entre 500 y 501 nro:2587')	 
/*9. Listar nroTicket, total, fecha, hora para las facturas del cliente  ́Jorge Pérez ́ donde no haya
comprado el producto  ́Z ́.*/
SELECT f.nroTicket, f.todas, f.fecha, f.hora
FROM Factura f NATURAL JOIN Cliente C
               NATURAL JOIN Detalle d
               NATURAL JOIN Producto p
WHERE c.nombre = 'Jorge' AND c.apellido = 'Pérez' AND p.nombreP <> 'Z'
/*10. Listar DNI, apellido y nombre de clientes donde el monto total comprado, teniendo en cuenta
todas sus facturas, supere $10.000.000.*/
SELECT c.DNI, c.apellido, c.nombre
FROM cliente c NATURAL JOIN factura f
GROUP BY C.idCliente, c.DNI, c.apellido, c.nombre
HAVING SUM(f.total) > 10000000

