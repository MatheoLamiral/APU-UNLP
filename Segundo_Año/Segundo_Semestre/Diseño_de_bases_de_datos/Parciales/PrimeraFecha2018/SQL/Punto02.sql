SELECT DISTINCT j.dni, j.nombre, j.apellido
FROM Jugador j INNER JOIN ClubJugador cj ON (j.dni = cj.dni)
               INNER JOIN Club c ON (cj.codigoClub = c.codigoClub)
               INNER JOIN Ciudad ci ON (c.codigoCiudad = ci.codigoCiudad)
WHERE (j.edad > 29)AND(ci.nombre = 'Cordoba')