# Ejercicio 2

```kotlin
    Process Persona [id: 1..P]{
        Fila!llegada(id);
        Fila?turno();
        //usarTerminal();
        Fila!salida();
    }

    Process Fila{
        cola personas;
        int idPersona;
        boolean libre = true;
        int faltan = P; 
        while(faltan > 0){
            if (not libre); Persona[*]?llegada(idPersona)=>
                personas.push(idPersona);
            [] (libre); Persona[*]?llegada(idPersona)=>
                libre = false;
                Persona[idPersona]!turno();
                faltan--;
            [] Persona?salida()=>
                if (personas.isNotEmpty()){
                    Persona[personas.pop()]!turno();
                    faltan--;
                }else{
                    libre = true;
                }
        }
    }
```