# Ejercicio 9

```ada
    Procedure EmpresaLimpiesa is

        task Administrador is
            entry reclamo(idPersona: in integer);
            entry camionDisponible(idPersona: out integer);
        end Administrador;

        task type Camion;
        arrCamiones: array (1 .. 3) of Camion;

        task type Persona is 
            entry Ident(ident: in integer);
            entry camionEnCamino();
        end Persona;
        arrPersonas: array (1 .. P) of Persona;

        task body Administrador is
            reclamos: array (1 .. P) of integer; -- inicializados en 0
            totalReclamos: integer := 0;
            idMasReclamos: integer := -1;
        begin
            loop
                select 
                    accept reclamo(idPersona: in integer) do
                        -- si la cantidad de reclamos es -1 significa que ya fue atendida
                        if(reclamos(idPersona) != -1) then
                            -- incremento la cantidad de reclamos de la persona y el total
                            reclamos(idPersona) = reclamos(idPersona) + 1;
                            totalReclamos := totalReclamos + 1;
                        end if;
                    end reclamo;
                    -- actualizo el maximo 
                    for i := 1 to P loop
                        if reclamos(i) > reclamos(idMasReclamos) then
                            idMasReclamos := i;
                        end if;
                    end loop;
                -- si me llego un pedido del camion y hay reclamos pendientes
                or when totalReclamos > 0
                    accept camionDisponible(idPersona: out integer) do
                        -- le digo al camion a donde ir 
                        idPersona := idMasReclamos;
                        -- decremento los reclams de la persona atendida del total
                        totalReclamos := totalReclamos - reclamos(idMasReclamos);
                        -- marco que la persona ya fue atendida
                        reclamos(idMasReclamos) := -1;
                        -- reseteo el maximo para que arranque de nuevo 
                        idMasReclamos := -1;
                    end camionDisponible;
                end select;
            end loop;
        end Administrador;

        task body Camion is
            idPersona: integer;
        begin
            loop
                Administrador.camionDisponible(idPersona);
                -- se dirige a la casa correspondiente
                Persona(idPersona).camionEnCamino();
            end loop;
        end Camion;

        task body Persona is
            atendido: boolean := false;
        begin
            accept Ident(ident: in integer) do
                id := ident;
            end Ident;
            while (not atendido) loop
                Administrador.reclamo();
                select
                    accept camionEnCamino();
                    atendido := true;
                or delay 15 minutos
                    null;
                end select;
            end loop;
        end Persona;

    Begin
        for i in 1 .. P loop
            arrPersonas(i).Ident(i);
        end loop;
    End;
```