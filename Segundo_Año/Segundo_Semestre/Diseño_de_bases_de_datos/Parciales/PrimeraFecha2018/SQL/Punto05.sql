SELECT c.nombre
FROM Club c
WHERE c.codigoClub NOT IN(SELECT DISTINCT cj.codigoClub
                            FROM ClubJugador cj INNER JOIN Jugador j ON (cj.dni = j.dni)
                            INNER JOIN Ciudad ci ON (j.codigoCiudad = ci.codigoCiudad)
                            WHERE ci.nombre = 'Mar Del Plata')