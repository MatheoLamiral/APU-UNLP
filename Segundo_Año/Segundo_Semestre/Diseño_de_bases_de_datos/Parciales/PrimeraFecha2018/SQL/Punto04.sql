SELECT j.nombre, j.apellido, j.edad, COUNT(DISTINCT cj.codigoClub)
FROM Jugador j INNER JOIN ClubJugador cj ON (j.dni = cj.dni)
GROUP BY j.dni, j.nombre, j.apellido, j.edad