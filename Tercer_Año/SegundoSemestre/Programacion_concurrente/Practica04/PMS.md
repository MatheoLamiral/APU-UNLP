# Práctica 4 - PMS (Pasaje de Mensajes Sincrónicos)

### Ejercicio 1

- Inciso a y b
```
    Process Examinador [id: 1..R]{
        text dirSitio;
        while (true){
            dirSitio=//buscarSitioInfectado();
            Admin!sitio(dirSitio);
        }
    }

    Process Admin {
        cola sitiosSospechosos;
        text dirSitio;
        while (true){
            do Examinador[*]?(dirSitio) -> 
                sitiosSospechosos.push(dirSitio);
            [] sitiosSospechosos.notEmpty(), Analizador!pedido() ->
                Analizador!sitio(sitiosSospechosos.pop());
        }
    }

    Process Analizador {
        text dirSitio;
        while(){
            Admin?pedido();
            Admin?sitio(dirSitio);
            //analizarSitio(dirSitio);
        }
    }
```
- Inciso c
  - El orden de llegada quedó resuelto en el inciso b

### Ejercicio 2

```
    Process EmpleadoMuestras{
        Muestra muestra;
        while (true){
            muestra =//prepararMuestra;
            ManejoMuestras!(muestra);
        }
    }

    Process ManejoMuestras{
        cola muestrasParaAnalisis;
        Muestra muestra;
        while (true){
            do EmpleadoMuestras?(muestra) ->
                muestrasParaAnalisis.push(muestra);
            [] muestrasParaAnalisis.notEmpty(), EmpleadoSetAnalisis?pedido() ->
                EmpleadoSetAnalisis!muestra(muestrasParaAnalisis.pop());
        }
    }

    Process EmpleadoSetAnalisis{
        Muestra muestra;
        text res;
        while (true){
            ManejoMuestras!pedido();
            ManejoMuestras?muestra(muestra);
            //prepararParaAnalisis(muestra);
            EmpleadoAnalisis!setAnalisis(muestra);
            EmpleadoAnalisis?informeResultados(res);
            //archivarResultados(res);
        }
    }

    Process EmpleadoAnalisis{
        Muestra muestra;
        text res;
        while (true){
            EmpleadoSetAnalisis?setAnalisis(muestra);
            res=//analizarMuestra(muestra);
            EmpleadoSetAnalisis!informeResultados(res);
        }
    }
```

### Ejercicio 3

- Inciso a
```
    Process Alumno [id: 1..N]{
        examen examen;
        int nota;
        //resolverExamen(examen);
        pilaExamenes!(examen, id);
        Profesor!nota(nota);
    }

    Process pilaExamenes{
        cola examenesPendientes;
        examen examen;
        int idAlumno;
        int faltanCorregir = N;
        while (while faltanCorregir > 0){
            do Alumno[*]?(examen, idAlumno) ->
                examenesPendientes.push(examen);
            [] examenesPendientes.isNotEmpty(), Profesor?pedido() ->
                Profesor!examen(examenesPendientes.pop(), idAlumno);
                faltanCorregir--;
        }
    }

    Process Profesor{
        examen examen;
        int idAlumno;
        int nota;
        for i: 1..N{
            pilaExamenes!pedido();
            pilaExamenes?examen(examen, idAlumno);
            //corregirExamen(examen, nota);
            Alumno[idAlumno]!nota(nota);
        }
    }
```
- Inciso b 
```
    Process Alumno [id: 1..N]{
        examen examen;
        int nota;
        //resolverExamen(examen);
        pilaExamenes!(examen, id);
        Profesor[*]!nota(nota);
    }

    Process pilaExamenes{
        cola examenesPendientes;
        examen examen;
        int idAlumno;
        int idProfesor;
        int faltanCorregir = N;
        while (while faltanCorregir > 0){
            do Alumno[*]?(examen, idAlumno) ->
                examenesPendientes.push(examen);
            [] examenesPendientes.isNotEmpty(), Profesor[*]?pedido(idProfesor) ->
                Profesor[idProfesor]!examen(examenesPendientes.pop(), idAlumno);
                faltanCorregir--;
        }
        for i: 1..P {
            Profesor[i]!examen(examen, -1);
        }
    }

    Process Profesor [id: 1..P]{
        examen examen;
        int idAlumno;
        int nota;
        while (true){
            pilaExamenes!pedido(id);
            pilaExamenes?examen(examen, idAlumno);
            if(idAlumno == -1){
                break;
            }
            //corregirExamen(examen, nota);
            Alumno[idAlumno]!nota(nota);
        }
    }
```
- Inciso c
```
    Process Alumno [id: 1..N]{
        examen examen;
        int nota;
        Controlador!llegada();
        Controlador?arrancar();
        //resolverExamen(examen);
        Controlador!(examen, id);
        Profesor[*]!nota(nota);
    }

    Process Controlador{
        cola examenesPendientes;
        examen examen;
        int idAlumno;
        int idProfesor;
        int faltanCorregir = N;
        for i: 1..N {
            Alumno[*]?llegada() ->
        }
        for i: 1..N {
            Alumno[i]?arrancar() ->
        }
        while (while faltanCorregir > 0){
            do Alumno[*]?(examen, idAlumno) ->
                examenesPendientes.push(examen);
            [] examenesPendientes.isNotEmpty(), Profesor[*]?pedido(idProfesor) ->
                Profesor[idProfesor]!examen(examenesPendientes.pop(), idAlumno);
                faltanCorregir--;
        }
        for i: 1..P {
            Profesor[i]!examen(examen, -1);
        }
    }

    Process Profesor [id: 1..P]{
        examen examen;
        int idAlumno;
        int nota;
        while (true){
            Controlador!pedido(id);
            Controlador?examen(examen, idAlumno);
            if(idAlumno == -1){
                break;
            }
            //corregirExamen(examen, nota);
            Alumno[idAlumno]!nota(nota);
        }
    }
```

### Ejercicio 4

### Ejercicio 5