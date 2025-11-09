# Repaso PMS - Simulador de vuelo 

```kotlin
    Process Persona[id: 1..P]{
        Empleado!llegada(id);
        Empleado?turno();
        //usarSimulador();
        Empleado!salir();
    }

    Process Empleado{
        cola personas;
        int idPersona;
        boolean libre = true;
        do (libre); Persona[*]?llegada(idPersona) =>
            libre = false;
            Persona[idPersona]!turno();
        [] (not libre); Persona[*]?llegada(idPersona) =>
            personas.push(idPersona);
        [] (personas.isNotEmpty); Persona[*]?salir() =>
            Persona[personas.pop()]!turno();
        [] (personas.isEmpty()); Personas[*]?salir() =>
            libre = true;
    }
``` 