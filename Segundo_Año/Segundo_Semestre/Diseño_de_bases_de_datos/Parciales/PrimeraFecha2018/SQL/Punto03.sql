SELECT c.nombre, AVG(j.edad)
FROM Club c INNER JOIN ClubJugador cj ON (c.codigoClub = cj.codigoClub)
            INNER JOIN Jugador j ON (cj.dni = j.dni)
WHERE (cj.hasta IS NULL)
GROUP BY c.codigoClub,c.nombre