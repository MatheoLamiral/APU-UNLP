# Práctica 2

### Ejercicio 1

- Inciso a y b 
```
    sem libre = 1;

    Process chequeo [id = 1 .. N]
    {
        P(libre);
        usarDetector
        V(libre);
    }
```
- Inciso c
```
    sem libre = 3;
    sem mutex = 1;

    Process chequeo [id = 1 .. N]
    {
        P(libre);
        P(mutex);
        detector = buscarLibre;
        V(mutex);
        usarDetector(detector)
        V(libre);   
    }
```
- Inciso d
```
    sem libre = 3;  
    sem mutex = 1;

    Process chequeo [id = 1 .. N]
    {
        int repeticiones = int.Random;
        for i = 1 .. repeticiones
        {
            P(libre);
            P(mutex);
            detector = buscarLibre;
            V(mutex);
            usarDetector(detector)
            V(libre);   
        }
    }
```

### Ejercicio 2

- Inciso a
```
    int vecGravedad ([3]0);
    fallos historial [N]; //asumo N par
    int procesos = 4;

    sem libre ([3] 1);
    
    Process Chequeo[id: 0..procesos-1]
    {
        int vecGravedadPorLote ([3]0);
        const lote = N/procesos
        int inicio := id*lote
        int fin := inicio + lote
        for i in inicio .. fin {
            if(historial[i].gravedad = 3)
                print(historial[i].id)
            vecGravedadPorLote[hitsorial[i].gravedad]++;
        }
        for j : 0..3
        {
            P(libre[j])
                vecGvedad[j] = vecGvedad[j] + vecGravedadPorLote[j];
            V(libre[j])
        }
        
    }
```
- Inciso b
```
    int vecGravedad ([3]0);
    fallos historial [N]; //asumo N par
    int procesos = 4;

    sem libre ([3] 1);
    
    Process Chequeo[id: 0..procesos-1]
    {
        int vecGravedadPorLote ([3]0);
        const lote = N/procesos
        int inicio := id*lote
        int fin := inicio + lote
        for i in inicio .. fin {
            vecGravedadPorLote[hitsorial[i].gravedad]++;
        }
        for j : 0..3
        {
            P(libre[j])
                vecGvedad[j] = vecGvedad[j] + vecGravedadPorLote[j];
            V(libre[j])
        }
        
    }
```

- Inciso c
```
    int vecGravedad ([3]0);
    fallos historial [N]; //asumo N par
    int procesos = 4;

    Process Chequeo[id: 0..procesos-1]
    {
        int cantGravedadLocal = 0;
        for i in 0..N-1 loop
            if(historial[i].gravedad = id)
                cantGravedadLocal++;
        end loop;
        vecGvedad[id] = cantGravedadLocal;
    }
```

### Ejercicio 3
```
    colaRecursos [4];
    procesos = N;
    sem libre = 1;
    sem cant = 5;

    Process tomarRecurso[id: 0..procesos-1]
    {
        P(cant)
        P(libre)
        recurso = colaRecursos.pop();
        V(libre)
        usarRecurso(recurso);
        P(libre)
        recurso = colaRecursos.push(recurso);
        V(libre)
        V(cant)
    }
```

### Ejercicio 4

La solución presentada no es la adecuada, ya que en el caso de que lleguen, por ejemplo, 6 Usuarios de alta seguidos, 2 quedarán sin poder entrar, pero al chequear primero el semáforo de total, lo decrementarán de todas formas, dejando las dos posiciones libres bloqueadas para un usuario de baja, en el caso de que llegase uno, Generando demora innecesaria.  
Esto se soluciona invirtiendo el órden de los semáforos:
```
    total: sem := 6;
    alta: sem := 4;
    baja: sem := 5;

    Process Usuario-Alta [I:1..L]::
    {P (alta);
    P (total);
    //usa la BD
    V(total);
    V(alta);
    }

    Process Usuario-Baja [I:1..K]::
    {P (baja);
    P (total);
    //usa la BD
    V(total);
    V(baja);
    }
```

### Ejercicio 5

- Inciso a
```
    vac contenedores[N];
    int pri_vacia = 0;
    int pri_ocupada = 0;
    sem vacio = N;
    sem lleno = 0;


    Process Preparador []
    {
        while (true){
            paquete = //prepara el paquete
            P(vacio);
            contenedores[pri_vacia] = paquete;
            pri_vacia = (pri_vacia + 1) mod N;
            v(lleno);
        }
    }

    Process Entregador []
    {
        while (true){
            P(lleno);
            paquete = contenedores[pri_ocupada];
            pri_ocupada = (pri_ocupada + 1) mod N;
            v(vacio);
            //realiza entrega(paquete)
        }
    }
```

- Inciso b
```
    vac contenedores[N];
    int pri_vacia = 0;
    int pri_ocupada = 0;
    sem vacio = N;
    sem lleno = 0;
    sem mutex = 1;


    Process Preparador [id: 1..P]
    {
        while (true){
            paquete = //prepara el paquete
            P(vacio);
            P(mutex);
            contenedores[pri_vacia] = paquete;
            pri_vacia = (pri_vacia + 1) mod N;
            V(mutex);
            v(lleno);
        }
    }

    Process Entregador []
    {
        while (true){
            P(lleno);
            paquete = contenedores[pri_ocupada];
            pri_ocupada = (pri_ocupada + 1) mod N;
            v(vacio);
            //realiza entrega(paquete)
        }
    }
```

- Inciso c
```
    vac contenedores[N];
    int pri_vacia = 0;
    int pri_ocupada = 0;
    sem vacio = N;
    sem lleno = 0;
    sem mutex = 1;


    Process Preparador []
    {
        while (true){
            paquete = //prepara el paquete
            P(vacio);
            contenedores[pri_vacia] = paquete;
            pri_vacia = (pri_vacia + 1) mod N;
            v(lleno);
        }
    }

    Process Entregador [id: 1..E]
    {
        while (true){
            P(lleno);
            P(mutex);
            paquete = contenedores[pri_ocupada];
            pri_ocupada = (pri_ocupada + 1) mod N;
            V(mutex);
            v(vacio);
            //realiza entrega(paquete)
        }
    }
```

- Inciso d
```
    vac contenedores[N];
    int pri_vacia = 0;
    int pri_ocupada = 0;
    sem vacio = N;
    sem lleno = 0;
    sem mutex = 1;


    Process Preparador [id: 1..P]
    {
        while (true){
            paquete = //prepara el paquete
            P(vacio);
            P(mutex);
            contenedores[pri_vacia] = paquete;
            pri_vacia = (pri_vacia + 1) mod N;
            V(mutex);
            v(lleno);
        }
    }

    Process Entregador [id: 1..E]
    {
        while (true){
            P(lleno);
            P(mutex);
            paquete = contenedores[pri_ocupada];
            pri_ocupada = (pri_ocupada + 1) mod N;
            V(mutex);
            v(vacio);
            //realiza entrega(paquete)
        }
    }
```

### Ejercicio 6

- Inciso a
```
sem libre = 1;

Process usarImpresora [id : 1..N]
{
   P(libre)
   imprimir(documento)
   V(libre) 
}
```

- Inciso b
```
cola c;
boolean Libre = true;
sem mutex = 1;
sem espera([N]0);

Process usarImpresoraEnOrden [id: 1..N]
{
    int aux;
    P(mutex)
    if(libe){
        libre = false;
        V(mutex)
    }else{
        c.push(id);
        V(mutex);
        P(espera[id]);
    }
    imprimir(documento);
    P(mutex);
    if(c.isEmpty()){
        libre = true;
    }else{
        aux = c.pop();
        V(espera[aux]);
    }
    V(mutex);
}
```
- Inciso c
```
boolean Libre = true;
sem mutex = 1;
sem espera([N]0);

Process usarImpresoraEnOrden [id: 1..N]
{
    if(id =! 1){
        P(espera[id]);
    }
    imprimir(documento)
    V(espera[id++]);
}
```
- Inciso d
```
cola c;
sem mutex = 1;
sem espera([N]0);
sem fin = 0;
sem solicitud = 0;

Process usarImpresoraEnOrden [id: 1..N]
{
    P(mutex)
    c.push(id);
    V(mutex)
    V(solicitud)
    P(espera[id])
    imprimir(documento)
    v(fin);
}

Process Coordinador
{
    int aux;
    while(true){
        P(solicitud)
        aux = c.pop();
        v(espera[aux])
        P(fin);
    }
}
```
- Inciso e
```
cola c;
sem mutex = 1;
sem espera([N]0);
sem solicitud = 0;
sem libres = 5;
sem mutex2 = 1;
cola recursos[5];

Process usarImpresoraEnOrden [id: 1..N]
{
    P(mutex)
    c.push(id);
    V(mutex)
    V(solicitud)
    P(espera[id])
    P(mutex2);
    recurso = recursos.pop();
    V(mutex2);
    imprimir(recurso, documento);
    recursos.push(recurso)
    v(libre)
}

Process Coordinador
{
    int aux;
    while(true){
        P(solicitud)
        P(libres);
        P(mutex);
        aux = c.pop();
        V(mutex);
        v(espera[aux])
    }
}
```

### Ejercicio 7

```
    sem terminados ([10] 0);
    vec grupos ([10] 0);
    vec res ([10] 0);
    int puntaje = 10;
    cola comunicacion;
    sem mutex = 1;
    sem mutex1 = 1;
    sem vacio = 0;
    int contador = 0;
    sem barrera = 0;

    Process Alumno [id: 1..50]
    {
        int grupo = elegir();
        P(mutex);
        contador ++;
        if(contador == 50){
            for i: 1..50{
                V(barrera);
            }
        }
        V(mutex);
        P(barrera);
        //realizar tarea
        P(mutex1);
        comunicacion.push(grupo);
        V(mutex1);
        V(vacio);
        P(terminados[grupo])
    }

    Process Profesor []
    {  
        for i: 1..50 {
            P(vacio);
            P(mutex1);
            alumno = comunicacion.pop();
            V(mutex1);
            grupo[alumno]++
            if(grupo[alumno] == 5){
                res[alumno] = puntaje;
                puntaje --;
                for i: 1..5{
                    V(terminados[alumno]);
                }
            }
        } 
    }
```

### Ejercicio 8

- Inciso a
```
    sem mutex = 1;
    sem mutex2 = 1;
    sem mutex = 3;
    sem barrera = 0;
    int contador = 0;
    cola piezas;
    vec piezas[T];
    int max = -1;
    int idEmpleadoMax = -1;

    Process Empleado [id: 1..E]
    {
        int maxLocal = 0;
        P(mutex);
        contador ++;
        if(contador == E){
            for i: 1..E{
                V(barrera);
            }
        }
        V(mutex);
        P(barrera);

        P(mutex2);
        while (piezas.isNotEmpty()){
            pieza = piezas.pop();
            V(mutex2);
            //prodcuirPieza(pieza);
            maxLocal++;
            P(mutex2);
        }
        V(mutex2);

        P(mutex3);
        if(maxLocal > max){
            max = maxLocal;
            idEmpleadoMax = id;
        }
        v(mutex3);
    }
```

- Inciso b 

```
    Idem Inciso a
```

### Ejercicio 9

```
    vec depositoMarco [30];
    int priVaciaMarco = 0;
    int priOcupadaMarco = 0;
    vec depositoVidrio [50];
    int priVaciaVidrio = 0;
    int priOcupadaVidrio = 0;
    sem vacioMarco = 0;
    sem vacioVidrio = 0;
    sem llenoMarco = 30;
    sem llenoVidrio = 50;
    sem mutex = 1;
    sem mutex1 = 1;


    Process Carpintero [id:1..4]
    {
        marco = //hacerMarco()
        P(llenoMarco);
        P(mutex);
        depositoMarco[priVaciaMarco] = marco;
        priVaciaMarco = (PriVaciaMarco + 1) mod 30;
        V(mutex);
        V(vacioMarco);
    }

    Process Vidriero
    {
        vidrio = //hacerVidrio()
        P(llenoVidrio);
        P(mutex1);
        depositoVidrio[priVaciaVidrio] = vidrio;
        priVaciaVidrio = (PriVaciaVidrio + 1) mod 50;
        V(mutex1);
        V(vacioVidrio);
    }

    Process Armador [id: 1..2]
    {
        P(vacioMarco);
        P(mutex);
        marco = depositoMarco[priOcupadaMarco];
        priOcupadaMarco = (PriOcupadaMarco + 1) mod 30;
        V(mutex);
        V(llenoMarco);
        P(vacioVidrio);
        P(mutex1);
        vidrio = depositoVidrio[priOcupadaVidrio];
        priOcupadaVidrio = (PriOcupadaVidrio + 1) mod 50;
        V(mutex1);
        V(llenoVidrio);
        //fabricarVentana(marco, vidrio);
    }
```

### Ejercicio 10

- Inciso a 
```
    cola Camion llegada;
    sem vacio = 0;
    sem disponibleDescarga = 7;
    sem trigo = 5;
    sem maiz = 5;
    sem mutex = 1;
    sem permisoTrigo([T] 0);
    sem permisoMaiz([M] 0);
    sem termino = 0;

    Process CamionTrigo [id: 1..T]
    {
        Camion camionTrigo = new Camion();
        camionTrigo.id = id;
        camionTrigo.tipo = "trigo"
        P(mutex);
        llegada.push(camion);
        V(mutex);
        V(vacio)
        P(permisoTrigo[id]);
        //descarga;
        V(trigo);
    }

    Process CamionMaiz [id: 1..M]
    {
        Camion camionMaiz = new Camion();
        camionMaiz.id = id;
        camionMaiz.tipo = "Maiz"
        P(mutex);
        llegada.push(camion);
        V(mutex);
        V(vacio)
        P(permisoMaiz[id]);
        //descarga;
        V(maiz);
    }

    Process Coordinador
    {
        P(vacio);
        P(mutex);
        camion = llegada.pop();
        V(mutex);
        P(disponibleDescarga);
        if(camion.tipo = "trigo"){
            P(trigo);
            V(permisoTrigo[camion.id]);
        }else{
            P(maiz);
            V(permisoMaiz[camion.id]);
        }
    }
```
- Inciso b
```
    sem disponibleDescarga = 7;
    sem trigo = 5;
    sem maiz = 5;

    Process CamionTrigo [id: 1..T]
    {
        P(trigo);
        P(disponibleDescarga);
        //descarga;
        V(diponibleDescarga);
        V(trigo);
    }

    Process CamionMaiz [id: 1..M]
    {
        P(maiz);
        P(disponibleDescarga);
        //descarga;
        V(diponibleDescarga);
        V(maiz);
    }

```

Este caso es un claro ejemplo de que en ocasiones el uso de coordinadores, disminuye la eficiencia y aumenta la complejidad. Podemos observar que la solución del inciso a es mucho mas ineficiente y compleja que la del inciso b 

### Ejercicio 11

```
    sem mutex = 1;
    sem mutex2 = 1;
    cola c;
    sem llegada = 0;
    int contador = 0;
    sem libre = 1;
    sem espera ([50] 0);

    Process Empleado 
    {
        cola personas;
        for i: 1..10{
            for i:1..5{
                P(llegada);
            }
            for i: 1..5{
                P(mutex)
                int persona = c.pop();
                personas.push(persona);
                V(mutex)
                VacunarPersona(persona);
            }
            for i:1..5{
                V(espera[personas.pop()]);
            }
        }
    }

    Process Persona [id: 1..50]
    {
        P(mutex);
        c.push(id);
        V(mutex);
        V(llegada);
        P(espera[id]);
    }
```

### Ejercicio 12

- Inciso a
```
    cola colas[3];
    sem esperaPasajeros([150] 0);
    sem vacioLlegada = 0;
    sem vacioEnfermeras([3] 0);
    int cantidades([3] 0);
    cola llegada;
    sem mutex = 1;
    sem mutex2 = 1;


    Process Pasajero [id: 1..150]
    {
        P(mutex);
        llegada.push(id);
        V(mutex);
        V(vacioLlegada);
        P(esperaPasajeros[id]); //espero a que me terminen de hisopar
    }

    Process Enfermera [id: 1..3]
    {
         while (true){
            P(vacioEnfermeras[id]);
            P(mutex)
            pasajero = colas[id].pop();
            V(mutex)
            //Hisopar(pasajero);
            V(esperaPasajeros[pasajero]);
            P(mutex2);
            cantidades[id]--;
            V(mutex2);
        }
    }

    Process Recepcionista
    {
        for i: 1..150{
            P(vacioLegada);
            P(mutex2);
            int minEspera = //calcularMinimo();
            cantidades[minEspera]++;
            V(mutex2);
            P(mutex);
            colas[minEspera].push(llegada.pop());
            V(mutex);
            V(vacioEnfermeras[minEspera]);
        }
    }
```

- Inciso b 
```
    cola colas[3];
    sem esperaPasajeros([150] 0);
    sem vacioEnfermeras([3] 0);
    int cantidades([3] 0);
    sem mutex = 1;
    sem mutex2 ();


    Process Pasajero [id: 1..150]
    {
        P(mutex);
        minEspera = //calcularMinimo();
        cantidades[minEspera]++;
        V(mutex);
        P(mutex2);
        colas[minEspera].push(id);
        V(mutex2);
        V(vacioEnfermeras[minEspera]);
        P(esperaPasajeros[id]); //espero a que me terminen de hisopar
    }

    Process Enfermera [id: 1..3]
    {
         while (true){
            P(vacioEnfermeras[id]);
            P(mutex2)
            pasajero = colas[id].pop();
            V(mutex2)
            //Hisopar(pasajero);
            V(esperaPasajeros[pasajero]);
            P(mutex);
            cantidades[id]--;
            V(mutex);
        }
    }
```