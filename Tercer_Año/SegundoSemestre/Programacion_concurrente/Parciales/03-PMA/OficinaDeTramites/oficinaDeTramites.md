# Repaso PMA - Oficina de Trámites

```kotlin
    chan llegada(text tramite, int idPersona);
    chan resultado[P](text resultado);
    chan libre(int idEmpleado);
    chan asignar[3](text tramite, int idPersona);

    Process Persona [id: 1..P]{
        text tramite = //generarTramite();
        text resultado;
        send llegada(tramite, id);
        receive resultado[id](text resultado);
    }

    Process Administrador{
        text tramite;
        int idPersona;
        while true{
            receive libre(idEmpleado); 
            if(empty(llegada)){
                send asignar[idEmpleado]("", -1);
            }
            else{
                receive llegada(tramite, idPersona);
                send asignar[idEmpleado](tramite, idPersona);
            }
        }
    }

    Process Empleado[id: 1..3]{
        text tramite;
        text resultado;
        int idPersona;
        while true{
            send libre(id);
            receive asignar[id](tramite, idPersona);
            if(idPersona = -1){
                delay(5); //procesa tramites pendientes por 5 minutos
            }
            else{
                resultado = //procesarTramite(tramite);
                send resultado[idPersona](resultado);
            }
        }
    }
```