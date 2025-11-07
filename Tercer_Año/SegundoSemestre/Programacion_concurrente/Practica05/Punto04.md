# Ejercicio 4

```ada
    Procedure Clinica is
        task medico is
            entry pedidoPaciente();
            entry pedidoEnfermera();
            entry dejarPeticion();
        end medico;

        task type enfermera;
        task type paciente;

        arrEnfermeras: array (1..E) of enfermera;
        arrPacientes: array (1..P) of paciente;

        task body medico is
            peticion: String;
        begin
            loop
                select
                    accept pedidoPaciente();
                or
                    when (pedidoPaciente'count = 0) =>
                        accept pedidoEnfermera();
                else
                    Escritorio.peticionMedico(peticion);
                    if (peticion != "null") then
                        -- procesarPeticion(peticion);
                    end if;
                end select;
            end loop;
        end;

        task body Escritorio is
            peticiones: cola;
        begin
            loop
                select -- si no tengo peticiones de medico atiendo a enfermera
                    when (medico.peticiones'count = 0) =>
                        accept peticionEnfermera(peticion: in String) do
                            peticiones.push(peticion);
                        end peticionEnfermera;
                or  -- le doy al medico una peticion
                    accept peticionMedico(peticion: out String) do
                        if(peticiones.is_empty()) then
                            peticion := "null";
                        else
                            peticion := peticiones.pop();
                        end if;
                end select;
            end loop;
        end Escritorio;

        task body paciente is
            intentos: integer := 3;
            atendido: boolean := false;
        begin
            while not atendido and intentos > 0 loop
                select
                    medico.pedidoPaciente();
                    atendido := true;
                or delay 5 minutos 
                    delay 10 minutos;
                    intentos := intentos - 1;
            end loop;
        end;

        task body enfermera is
            peticion: String;
        begin
            loop
                select
                    medico.pedidoEnfermera();
                else
                    peticion := --generarPeticionEnfermera();
                    Escritorio.peticionEnfermera(peticion);
                end select;
            end loop;
        end;
    Begin
        null;
    End Clinica;
```