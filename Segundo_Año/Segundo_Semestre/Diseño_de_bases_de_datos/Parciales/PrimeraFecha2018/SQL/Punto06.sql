SELECT DISTINCT j.nombre, j.apellido
FROM Jugador j 
WHERE NOT EXISTS(
    SELECT * 
    FROM Club cl
    WHERE NOT EXISTS(
        SELECT * FROM ClubJugador cj
        WHERE (cj.dni = j.dni) AND (cj.codigoClub = cl.codigoClub)
    )
)

-- quiero a los jugadores para los cuales NO EXISTA un club para el cual NO HAYAN jugado