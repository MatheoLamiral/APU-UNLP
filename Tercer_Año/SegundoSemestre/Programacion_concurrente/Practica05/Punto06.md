# Ejercicio 6

```ada
    Procedure Playa is
        -- tarea administrador 
        task administrador
            entry informeMonto(montoEquipo: in integer, idEquipo: in integer);
            entry informeEquipoGanadorAdmin(idEquipoGanador: out integer);
        end administrador;

        -- tarea equipo
        task type equipo is
            entry Ident(id: in integer);
            entry llegada();
            entry inicioJuego();
            entry entregarMonto(montoIntegrante: in integer);
            entry informeEquipoGanadorJugador(idEquipoGanador: out integer);
        end equipo;
        arrEquipos: array (1 .. 5) of equipo;

        -- tarea persona
        task type persona;
        arrPersonas: array (1 .. 20) of persona;

        -- ADMINISTRADOR
        task body administrador is
            montoAux: integer;
            montoMax: integer := -1;
            equipoAux: integer;
            equipoGanador: integer;
        begin
            for i := 1 to 5 loop
                -- recibo el monto y el id de cada equipo
                accept informeMonto(montoEquipo: in integer, idEquipo: in integer) do
                    montoAux := montoEquipo;
                    equipoAux := idEquipo;
                end informeMonto;
                -- reviso si es el maximo
                if montoAux > montoMax then
                    montoMax := montoAux;
                    equipoGanador := equipoAux;
                end if;
            end loop;
            -- informo el ganador a cada equipo
            for i := 1 to 5 loop
                accept informeEquipoGanadorAdmin(idEquipoGanador: out integer) do
                    idEquipoGanador := equipoGanador;
                end informeEquipoGanadorAdmin;
            end loop;
        end administrador;

        -- EQUIPO
        task body equipo is
            monto: integer := 0;
            id: integer;
        begin
            accept Ident(ident: in integer) do
                id := ident;
            end Ident;
            -- espero a que lleguen los 4 integrantes
            for i := 1 to 4 loop
                accept llegada();
            --  dejo arrancar a los 4 integrantes
            for i := 1 to 4 loop
                accept inicioJuego();
            end loop;
            -- recibo los montos de cada integrante
            for i := 1 to 4 loop
                accept entregarMonto(montoIntegrante: in integer) do
                    monto := monto + montoIntegrante;
                end entregarMonto;
            end loop;
            -- informo el monto que obtuvo el equipo al administrador
            administrador.informeMonto(monto, id);
            -- le pido el equipo ganador al administrador
            administrador.informeEquipoGanadorAdmin(idGanador);
            -- informo al jugador cual es el equipo ganador
            accept informeEquipoGanadorJugador(idEquipoGanador: out integer)do
                idEquipoGanador := idGanador;
            end informeEquipoGanadorJugador;
        end equipo;

        -- PERSONA
        task body persona is 
            monto: integer := 0;
            nroEquipo: integer;
            idEquipoGanador: integer;
        begin
            nroEquipo := --obtenerNumeroEquipo();
            Equipo(nroEquipo).llegada();
            Equipo(nroEquipo).inicioJuego();
            for i:= 1 to 15 loop
                monto := monto + Moneda();
            end loop;
            Equipo(nroEquipo).entregarMonto(monto);
            Equipo(nroEquipo).informeEquipoGanadorJugador(idEquipoGanador);
        end;
    Begin
        -- identifico a los equipos 
        for i := 1 to 5 loop
            arrEquipos(i).Ident(i);
        end loop;
    End Playa;
```