# Práctica 3 - Monitores

### Ejercicio 1

- Inciso a 
  - El código funciona correctamente
- Inciso b
  - El código se podría simplificar, ya que no se nos dice en el enunciado que se requiera orden para entrar al puente, por ende podríamos usar un solo Procedure y quitar la cola 
    ```
        Monitor Puente
        {
            Procedure pasarPuente();
        }

        Process Auto [id: 1..N]
        {
            Puente.pasarPuente();
        }

    ```
- Inciso c
  - La solución original no respeta el orden de llegada, ya que cuando un auto sale del puente cualquiera que gane la competencia podría pasar. En el código modificado tampoco se respeta, por la misma razón 

### Ejercicio 2
```
    Monitor Administrador
    {
        cond cola;
        cant = 0;

        Procedure solicitarAcesoBD()
        {
            while (cant = 5){
                wait(cola);
            }
            cant++;
        }

        Procedure salirBD()
        {
            cant --;
            signal(cola);
        }
    }

    Process Lector [id: 1..N]
    {
        Administrador.solicitarAccesoBD();
        //leerBD();
        Administrador.salirBD();
    }
```

### Ejercicio 3

- Inciso a
```
    Monitor Administrador
    {

        Administrador.entrarFotocopiadora(){
            //usarFotocopiadora();
        }
    }

    Process Fotocopiador [id: 1..N]
    {
        Administrador.entrarFotocopiadora();
    }
```

- Inciso b
```
    Monitor Administrador
    {
        cond cola;
        esperando = 0;
        boolean libre = true;

        Procedure solicitarFotocopiadora()
        {
            if (not libre){
                esperando ++;
                wait(cola);
            }
            else{
                libre = false;
            };
        }

        Procedure salirFotocopiadora()
        {
            if(esperando > 0){
                esperando --;
                signal(cola);   
            }
            else{
                libre = true;
            }
        }
    }

    Process Fotocopiador [id: 1..N]
    {
        Administrador.solicitarFotocopiadora();
        //Fotocopiar();
        Administrador.salirFotocopiadora();
    }
```
- Inciso c
```
    Monitor Administrador
    {
        cond espera[N];
        colaOrdenada fila;
        idAux = 0;
        esperando = 0;
        boolean libre = true

        Procedure solicitarFotocopiadora(idF, edad:in int)
        {
            if (not libre){
                fila.insertarOrdenado(idF, edad);
                esperando ++;
                wait(espera[idF]);
            }
            else{
                libre = false;
            };
        }

        Procedure salirFotocopiadora()
        {
            if(esperando > 0){
                esperando --;
                fila.sacar(idAux)
                signal(espera[idAux]);   
            }
            else{
                libre = true;
            }
        }
    }

    Process Fotocopiador [id: 1..N]
    {
        int edad = ...;
        Administrador.solicitarFotocopiadora(id, edad);
        //Fotocopiar();
        Administrador.salirFotocopiadora();
    }
```
- Inciso d

```
    Monitor Administrador
    {
        cond espera[N];
        colaOrdenada fila;
        idAux = 0;
        esperando = 0;
        boolean libre = true
        
        Procedure solicitarFotocopiadora(idF:in int)
        {
            if (!libre){
                fila.insertarOrdenado(idF);
                esperando ++;
                wait(espera[idF]);
            }
            else{
                libre = false;
            };
        }

        Procedure salirFotocopiadora()
        {
            if(esperando > 0){
                esperando --;
                fila.sacar(idAux)
                signal(espera[idAux]);   
            }
            else{
                libre = true;
            }
        }
    }

    Process Fotocopiador [id: 1..N]
    {
        Administrador.solicitarFotocopiadora(id);
        //Fotocopiar();
        Administrador.salirFotocopiadora();
    }
```
```
    Monitor Administrador
    {
        cond espera[N];
        turno = 1;

        Procedure solicitarFotocopiadora(idF:in int)
        {
            if (turno != idF){
                wait(espera[idF]);
            }
        }

        Procedure salirFotocopiadora()
        {
            turno ++;
            signal(espera[turno]);
        }
    }

    Process Fotocopiador [id: 1..N]
    {
        Administrador.solicitarFotocopiadora(id);
        //Fotocopiar();
        Administrador.salirFotocopiadora();
    }
```
- Inciso e
  
```
    Monitor Administrador
    {
        cond colaE;
        cond colaF;
        cant = 0;
        boolean termino = false;

        Procedure solicitarFotocopiadora()
        {
            cant++;
            signal(colaE);
            wait(colaF);
        }

        Procedure dejarPasar()
        {
            while (cant == 0)||(!termino){
                wait(colaE);
            }
            signal(colaF);
        }

        Procedure salirFotocopiadora()
        {
            cant --;
            termino = true;
            signal(colaE);
        }
    }

    Process Empleado
    {
        Administrador.dejarPasar();
    }

    Process Fotocopiador [id: 1..N]
    {
        Administrador.solicitarFotocopiadora();
        //Fotocopiar();
        Administrador.salirFotocopiadora();
    }
```

- Inciso f
  
```
    Monitor Administrador
    {
        cond colaE;
        cond colaF;
        cantImresoras = 10;
        cola fotocopiadorasLibres; //va a contener los ids de las 10 impresoras
        cola personasEsperando; //personas que estan esperando por una impresoara
        boolean termino = false;
        vec fotocopiadoraAsignada [N];

        Procedure solicitarFotocopiadora(idP: in int, idF: out int)
        {
            personasEsperando.psuh(idP);
            signal(colaE);
            wait(colaF);
            idF = fotocopiadorasAsignadas[idP];
        }

        Procedure dejarPasar()
        {
            //opcion 1 prioridad a atender a los procesos encolados, que no se llene tanto
            while (personasEsperando.isEmpty())||(fotocopiadorasLibres.isEmpty()){
                wait(colaE);
            }
            fotocopiadorasAsigadas[personasEsperando.pop()] = fotocopiadorasLibres.pop();
            signal(colaF);

            //opcion 2 prioridad a ordenar a los procesos que se encolan, que se ordene lo más posible 
            if (personasEsperando.isNotEmpty())&&(fotocopiadorasLibres.isNotEmpty()){
                fotocopiadorasAsigadas[personasEsperando.pop()] = fotocopiadorasLibres.pop();
                signal(colaF);
            }
            wait(colaE);            
        }

        Procedure salirFotocopiadora(idF: in int)
        {
            fotocopiadorasLibres.push(IdF);
            signal(colaE)
        }
    }

    Process Empleado
    {
        while(true){
            Administrador.dejarPasar();
        }
    }

    Process Fotocopiador [id: 1..N]
    {
        int idF;//id de la fotocopiadora a utilizar
        Administrador.solicitarFotocopiadora(id, idF);
        //Fotocopiar(idF);
        Administrador.salirFotocopiadora(idF);
    }
```

### Ejercicio 4

```
    Process Vehiculo[id: 1..N]
    {
        int peso = ..;
        Puente.llegar(peso);
        //crusarPuente();
        Puente.salir(peso);
    }

    Monitor Puente
    {
        cola colaPesoAutos;
        cond colaAutos;
        int pesoActual = 0;

        Procedure llegar(peso: in int){
            if((pesoActual + peso) > 50000)||(colaPesoAutos.isNotEmty()){
                colaPesoAutos.push(peso); // me agrego a la cola de pesos
                wait(colaAutos) 
                colaPesoAutos.pop(); // me saco a mi mismo 
            }
            pesoActual += peso;
        }

        Procedure salir(peso: in int){
            pesoActual -= peso;
            if(colaPesoAutos.isNotEmpty())&&(colaPesoAuto.top() + pesoActual <= 50000){
                esperando --;
                signal(colaAutos);
            }
        }
    }
```

### Ejercicio 5

- Inciso a
    ```
        Monitor Corralon 
        {   
            cola empleado;
            boolean empleadoLibre = true;
            cond esperaClientes;
            int esperando = 0;
            
            Procedure llegar(idEmp: out int, lista: out list){
                if(not empleadoLibre){
                    esperando++;
                    wait(esperaClientes);
                }
                else{
                    empleadoLibre = false;
                }
            }

            Procedure proximo(){
                if(esperando > 0){
                    esperando--;
                    signal(esperaClientes);
                }
                else{
                    empleadoLibre = true;
                }
            }

        }

        Monitor Escritorio
        {
            cond colaEmpleado, colaCliente;
            text listaProductos, comprobanteRes;
            boolean listo = false;

            Procedure Atencion(lista: in txt, comprobante: out text){
                listo = true; // llega el cliente
                listaProductos = lista; // deja la lista
                signal(colaEmpleado); 
                wait(colaCliente); // se queda esperando el comprobante
                comprobante = comprobanteRes; // toma el comprobante
                signal(colaEmpleado); // avisa que tomo el comprobante
            }

            Procedure esperarLista(lista: out text){
                if(not listo){
                    wait(colaEmpleado);
                }
                lista = listaProductos;
            }

            Procedure enviarComprobante(comprobante: in text){
                comprobanteRes = comprobante; // dejo el comprobante
                signal(colaCliente)
                wait(colaEmpleado) // espero a que el cliente tome el comprobante
                listo = false; // el cliente se fue 
            }

        }

        Process Empleado
        {
            while(true){
                Corralon.proximo(id);
                text lista = new ListaProductos();
                Escritorio.esperarLista(lista);
                comp = //generarComprobante(lista);
                Escritotio.enviarComprobante(comp);
            }

        }

        Process Cliente [id: 1..N]
        {
            int idEmp;
            Corralon.llegar();
            text listaProductos = new ListaProducts();
            text comprobante
            Escritorio.atencion(listaProductos, comprobante);
        }
    ```
- Inciso b
    ```
        Monitor Corralon 
        {   
            cola empleadosLibres;
            cond esperaClientes;
            int esperando = 0;
            int cantEmpleadosLibres = 0;
            
            Procedure llegar(idEmp: out int, lista: out list){
                if(cantEmpleadosLibres = 0){
                    esperando++;
                    wait(esperaClientes);
                }
                else{
                    cantEmpleadosLibres--;
                }
                idEmp = empleadosLibres.pop();
            }

            Procedure proximo(idEmp: in int){
                empleadosLibres.push(idEmp);
                if(esperando > 0){
                    esperando--;
                    signal(esperaClientes);
                }
                else{
                    cantidadEmpleadosLibres++;
                }
            }

        }

        Monitor Escritorio [id:1..E]
        {
            cond colaEmpleado, colaCliente;
            text listaProductos, comprobanteRes;
            boolean listo = false;

            Procedure Atencion(lista: in txt, comprobante: out text){
                listo = true; // llega el cliente
                listaProductos = lista; // deja la lista
                signal(colaEmpleado); 
                wait(colaCliente); // se queda esperando el comprobante
                comprobante = comprobanteRes; // toma el comprobante
                signal(colaEmpleado); // avisa que tomo el comprobante
            }

            Procedure esperarLista(lista: out text){
                if(not listo){
                    wait(colaEmpleado);
                }
                lista = listaProductos;
            }

            Procedure enviarComprobante(comprobante: in text){
                comprobanteRes = comprobante; // dejo el comprobante
                signal(colaCliente)
                wait(colaEmpleado) // espero a que el cliente tome el comprobante
                listo = false; // el cliente se fue 
            }

        }

        Process Empleado [id: 1..E]
        {
            while(true){
                Corralon.proximo(id);
                Escritorio[id].esperarLista();
                comp = //generarComprobante();
                Escritotio[id].enviarComprobante(comp);
            }

        }

        Process Cliente [id: 1..N]
        {
            int idEmp;
            Corralon.llegar(idEmp);
            Escritorio[idEmp].atencion();
        }
    ```
- Inciso C
    ```
        Monitor Corralon 
        {   
            cola empleadosLibres;
            cond esperaClientes;
            int esperando = 0;
            int cantEmpleadosLibres = 0;
            int clientesRestantes = N;
            
            Procedure llegar(idEmp: out int, lista: out list){
                if(cantEmpleadosLibres = 0){
                    esperando++;
                    wait(esperaClientes);
                }
                else{
                    cantEmpleadosLibres--;
                }
                idEmp = empleadosLibres.pop();
            }

            Procedure proximo(idEmp: in int, clientes: out int){
                empleadosLibres.push(idEmp);
                clientes = clientesRestantes;
                if(esperando > 0){
                    esperando--;
                    signal(esperaClientes);
                }
                else{
                    cantidadEmpleadosLibres++;
                }
                ClientesRestantes--;
            }

        }

        Monitor Escritorio [id:1..E]
        {
            cond colaEmpleado, colaCliente;
            text listaProductos, comprobanteRes;
            boolean listo = false;

            Procedure Atencion(lista: in txt, comprobante: out text){
                listo = true; // llega el cliente
                listaProductos = lista; // deja la lista
                signal(colaEmpleado); 
                wait(colaCliente); // se queda esperando el comprobante
                comprobante = comprobanteRes; // toma el comprobante
                signal(colaEmpleado); // avisa que tomo el comprobante
            }

            Procedure esperarLista(lista: out text){
                if(not listo){
                    wait(colaEmpleado);
                }
                lista = listaProductos;
            }

            Procedure enviarComprobante(comprobante: in text){
                comprobanteRes = comprobante; // dejo el comprobante
                signal(colaCliente)
                wait(colaEmpleado) // espero a que el cliente tome el comprobante
                listo = false; // el cliente se fue 
            }

        }

        Monitor ClientesRestantes
        {
            int cantClientes = N;

            Procedure decrementar(){
                cantClientes --;
            }

            Procedure consultar(cant: out int){
                cant = cantClientes;
            }
        }

        Process Empleado [id: 1..E]
        {
            int restantes;
            while(true){
                Corralon.proximo(id, restantes);
                if( crestantes > 0){
                    Escritorio[id].esperarLista();
                    comp = //generarComprobante();
                    Escritotio[id].enviarComprobante(comp);
                    clientesRestantes.consultar(cant);
                }
                esle{
                    break;
                }
            }

        }

        Process Cliente [id: 1..N]
        {
            int idEmp;
            Corralon.llegar(idEmp);
            Escritorio[idEmp].atencion();
            ClientesRestantes.decrementar();
        }
    ```

### Ejercicio 6

```
    Monitor comision
    {
        cond colaJ;
        int faltanLlegar = 50;
        vec tareas([50] 0);
        cond asignadas;
        cond terminados([25] 0);
        cond notas([25] 0);
        int nota 25;

        Procedure llegar(idA: in int, nroGrupo : out int){
            faltanLlegar --;
            if(faltanLlegar = 0){
                signal(colaJ); // aviso al JTP que llegaron todos
            }
            wait(asignadas); // espero a que me den mi tarea
            nroGrupo = tareas[idA];
        }

        Procedure asignarGrupo(){
            if(faltanLlegar > 0){
                wait(colaJ); // espero a que lleguen todos
            }
            for i: 1..50{
                tareas[i] = AsignarNroGrupo(); // asumo que no se repite más de dos veces el mismo número 
            }
            signalall();            
        }
    }

    Monitor Entrega
    {
        cond colaJ;
        cola examenesTerminados;

        Procedure entregar(nroGrupo: in int, examen: in examen){
            examenesTerminados.push(examen, nroGrupo);
            signal(colaJ);
        }

        Procedure agarrarExamen(examen: out examen, nroGrupo: out int){
            if(cola.isEmpty()){
                wait(colaJ);
            }
            (examen, nroGrupo) = cola.pop();
        }
    }

    Monitor Correccion
    {
        cond alumnosTerminaron[25];
        int vec alumnosCorregidos([25] 0);
        int vec notas([25] 0);
        nota = 25;
        
        Procedure esperarNota(nroGrupo: in int, notaRecibida: out int){
            wait(alumnosTerminaron[nroGrupo]);
            notaRecibida = notas[nroGrupo];
        }

        Procedure entregarNota(nroGrupo: in int){
            alumnosCorregidos[nroGrupo] ++;
            if(alumnosCorregidos[nroGrupo] = 2){
                notas[nroGrupo] = nota;
                nota --;
                signalAll(alumnosTerminaron[nroGrupo])
            }
        }
    }

    Process JTP
    {
        comision.asignarGrupo();
        for i: 1..50{
            Entrega.agarrarExamen(examen, nroGrupo);
            // corregirTarea(examen);
            Coreccion.entregarNota(nroGrupo);
        }
    }

    Process Alumno [id: 1..50]
    {
        int nota;
        int nrorupo;
        Cmision.llegar(id, nroGrupo);
        examen = //realizarTarea(nroGrupo);
        Entrega.entregar(nroGrupo, examen);
        Correccion.esperarNota(nroGrupo, nota);
    }
```

### Ejercicio 7

```
    Process Corredor [id: 1..C]
    {
        LineaSalida.llegar();
        //correrCarrera();
        Fila.llegar();
        botella botella;
        MaquinaExpendedora.tomarAgua(botella);
        Fila.salir();
    }

    Process Repositor
    {
        while(true){
                botellas = [(20), new botella();]
                MaquinaExpendedora.recargarBotellas(botellas);
        }
    }

    Monitor LineaSalida 
    {
        cond colaCorredores;
        int faltanLlegar = C;

        Procedure llegar(){
            faltanLlegar --;
            if (faltanLlegar > 0){
                wait(colaCorredores);
            }else{
                signalall(colaCorredores);
            }
        }
    }

    Monitor Fila
    {
        cond colaCorredores;
        boolean libre = true;
        int esperando = 0;
        
        Procedure llegar(){
            if (libre = false){
                esperando ++;
                wait(colaCorredores);
            }else{
                libre = false;
            }
        }

        Procedure salir(){
            if(esperando > 0){
                esperando --;
                signal(colaCorredores);
            }
            esle{
                libre = true;
            }
        }
    }

    Monitor MaquinaExpendedora
    {
        cond colaCorredores;
        cond repositor;
        int cantBotellas = 20;
        cola botellas;
        
        Procedure tomarAgua(botella: out botella){
            if(cantBotellas = 0){
                signal(repositor);
                wait(colaCorredores);
            }
            botella = botellas.pop();
            cantBotellas --;
        }

        Procedure recargarBotellas(arregloBotellas: in botellas){
            if(cantBotellas > 0){
                wait(repositor); // si todavía quedan botellas se duerme
            }
            for botella in arregloBotellas{
                botellas.push(botella); // recarga la máquina expendedora completa
            }
        }
    }
```

### Ejercicio 8

```
    Process Jugador [id: 1..20]
    {
        nroEquipo = //darEquipo();
        Equipo[nroEquipo].llegar(nroCancha);
        Cancha[nroCancha].jugar();
    }
    
    Monitor Equipo [id: 1..4]
    {
        cond colaJugadores;
        int faltanLlegar = 5;
        int nroCancha

        Procedure llegar(canchaAsignada: out int){
            faltanLlegar--;
            if (faltanLlegar > 0){
                wait(colaJugadores);
            }
            else{
                Asignaciones.asignarCancha(nroCancha)
                signalall(colaJugadores);
            }
            canchaAsignada = nroCancha
        }
    }

    Monitor Asignaciones
    {
        int cantCancha1 = 0;
        int cantCancha2 = 0;

        Procedure asignarCancha(canchaAsignada: out int){
            if(cantCancha1 < 2){
                cantCancha1 ++;
                canchaAsignada = 1;
            }else{
                cantCancha1 ++;
                canchaAsignada = 2;
            }
        }
    }

    Monitor Cancha [id: 1..2]
    {
        cond colaJugadores;
        int faltanLlegar = 10;

        Procedure jugar(){
            faltanLlegar--;
            if (faltanLlegar > 0){
                wait(colaJugadores);
            }
            else{
                delay(50minutos);
                signalall(colaJugadores);
            }
        }
    }
```

### Ejercicio 9

```
    Process Alumno [id: 1..45]
    {
        Examen examen;
        Aula.llegar(id, examen);
        //realizarExamen(examen);
        Entrega.entregar(id, examen);
        int nota;
        Correccion.esperarNota(nota);
    }

    Process Preceptor 
    {
        Aula.esperarAlumnos();
    }

    Process Profesora
    {
        for i: 1..45{
            Examen examen;
            int idA;
            Entrega.agarrarExamen(idA, examen);
            int nota;
            //corregirExamen(examen, nota);
            Correccion.entregarNota(idA, nota);
        }
    }

    Monitor Aula
    {
        cond colaAlumnos;
        cond preceptor;
        int faltanLlegar = 45;
        vec Examenes examenes [45];

        Procedure llegar(id: in int, examen: out Examen){
            faltanLlegar --;
            if(faltanLlegar = 0){
                signal(preceptor);// si soy el último despierto al preceptor 
            }
            wait(colaAlumnos);//espero a que me entreguen el examen
            examen = examenes[id];
        }

        Procedure esperarAlumnos(){
            if(faltanLlegar > 0){
                wait(preceptor);
            }
            examen = new Examen();
            for i: 1..45{
                examenes[i] = examen;
            }
            signalAll(colaAlumnos);
        }
    }

    Monitor Entrega
    {
        cond alumnos;
        cond profesora;
        cola examenes;
        int notas [45];

        Procedure entregarExamen(idA: in int, examen: in Examen){
            examenes.push(idA, examen);
            signal(profesora);
            wait(alumnos);
            notaRecibida = notas[id];
        }

        Procedure agarrarExamen(idA: out int, examen: out Examen){
            if(examenes.isEmpty()){
                wait(profesora);
            }
            (idA, examen) = examenes.pop();
        }

        Procedure entregarNota(idA: in int, nota: in int){
            notas[idA] = nota;
            signal(alumnos);
        }
    } 
```

### Ejercicio 10

```
    Process Persona [id: 1..N]
    {
        Fila.llegar();
        Juego.jugar();
        Fila.liberar();
    }

    Process Empleado
    {
        for i: 1..N{
            Juego.desinfectar();
        }
    }

    Monitor Fila
    {   
        cond colaP;
        int esperando = 0;
        boolean libre = true;

        Procedure llegar(){
            if(not libre){
                esperando ++;
                wait(colaP);
            }else{
                libre = false;
            }
        }

        Procedure dejarPasar(){
            if(esperando > 0){
                esperando --;
                signal(colaP);
            }
        }
    }

    Monitor Juego
    {
        cond persona;
        cond empleado;
        boolean desinfectado = false;

        Procedure jugar(){
            if(not desinfectado){
                wait(persona);
            }
            Usar_Juego();
            desinfectado = false;
            signal(empleado);|
        }

        Procedure desinfectar(){
            Desinfectar_Juego();
            desinfectado = true;
            signal(persona);
            wait(empleado)
        }
    }
```