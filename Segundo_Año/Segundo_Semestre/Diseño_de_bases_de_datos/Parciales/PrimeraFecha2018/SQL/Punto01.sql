SELECT c.nombre, c.anioFundacion
FROM club c INNER JOIN Ciudad ci ON (c.codigoCiudad = ci.codigoCiudad)
WHERE (ci.nombre = 'La Plata') AND c.codigoClub NOT IN(
                                                        SELECT c.codigoClub
                                                        FROM Club c INNER JOIN Estadio e ON (c.codigoClub = e.codigoClub)
                                                        )