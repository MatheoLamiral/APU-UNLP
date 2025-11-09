# Primera Fecha Redictado 2025

# Ejercicio 1 - PMA

```kotlin
    chan llegada();
    chan recibirExamen(examen text);
    chan entregarExamen(examen text, id int);
    chan nota[E](nota int);

    Process Estudiante [id: 1..E]{
        text examen;
        send llegada();
        receive recibirExamen(examen);
        //realizarExamen(examen);
        send entregarExamen(examen, id);
        receive nota[id](nota int);
    }

    Process Profesor {
        int faltanLlegar = E;
        text examen = //generarExamen();
        text examenRecibido;
        int idEstudiante;
        for i : 1 .. E {
            receive llegada();
        }
        for i : 1 .. E {
            send recibirExamen(examen);
        }
        for i : 1 .. E {
            receive entregarExamen(examenRecibido, idEstudiante);
            int nota = //corregirExamen(examenRecibido);
            send nota[idEstudiante](nota);
        }
    }
```

# Ejercicio 2 - PMS

```kotlin
    Process Paciente[id: 1..15]{
        text tratamiento;
        Administrador!llegada(id);
        Medico?tratamiento(tratamiento);
    }

    Process Administrador{
        cola pacientes;
        int faltan = 15;
        do (faltan > 0)Paciente[*]?llegada(idPaciente)=>
            pacientes.push(idPaciente);
        [] (pacientes.isNotEmpty()); Medico?libre()=>
            Medico[idMedico]!atender(pacientes.pop());
            faltan--;
    }

    Process Medico {
        int idPaciente;
        text tratamiento;
        for i : 1 .. 15 {
            Administrador!libre();
            Administrador?atender(idPaciente);
            tratamiento = //atenderPaciente(idPaciente);
            Paciente[idPaciente]!tratamiento(tratamiento);
        }
    }
```

# Ejercicio 3 - ADA

```ada
    Procedure Examen is
        
        task type Participante;
        arrParticipante: array (1..P) of Participante;

        task Coordinador is
            entry pedirEnunciado(enunciado: out text);
            entry entregarResolucion(resolucion: in text; nota: out real; orden: in  integer)

        task body Participante is
            enunciado: text;
            resolucion: text;
            nota: Integer;
            orden: Integer;
        begin
            Coordinador.pedirEnunciado(enunciado);
            resolucion := --resolverEnunciado(enunciado);
            Coordinador.entregarResolucion(resolucion, nota, orden);
        end Participante;

        task body Coordinador is
            enunciado: text;
            resolucion: text;
            nota: Integer;
            orden: Integer;
        begin 
            enunciado_generado := --generarEnunciado();
            posicion_actual:=1;
            for i:= 1..P*2 loop
                select 
                    accept pedirEnunciado(enunciado: out text)do
                        enunciado:= enunciado_generado;
                    end pedirEnunciado;
                or
                    accept entregarResolucion(resolucion: in text, nota: out real, orden: out integer)do
                        nota:= --asignarNota(resolucion);
                        orden:= posicion_actual;
                    end entregarResolucion;
                    posicion_actual ++;
            end loop;
        end Coordinador;
    Begin
        null;
    End Examen;
```
