# Práctica de repaso - Memoria compartida

## Semáforos

### Ejercicio 1

- Inciso a
  ```
    sem mutexFila = 1;
    cola fila;
    sem espera([P] 0);
    boolean libre = true;
    Terminal terminalSube;


    Process Persona [id: 1..P]
    {

      P(mutexFila); // bloqueo acá para también proteger la variable condición
      if(not libre){
        fila.push(id);
        V(mutexFila);
        P(espera[id]);
      }else{
        libre = false;
        V(mutexFila);
      }
      //UsarTerminal(terminalSube);
      P(mutexFila);
      if(fila.isNotEmpty){
        V(espera[fila.pop()]);
      }
      else{
        libre = true;
      }
      V(mutexFila);
    }
  ```
- Inciso b
  ```
    sem mutexFila = 1;
    sem mutexTerminales = 1;
    cola fila;
    sem espera([P] 0);
    int libres = t;


    Process Persona [id: 1..P]
    {

      P(mutexFila); // bloqueo acá para también proteger la variable condición
      if(libres = 0){
        fila.push(id); 
        V(mutexFila);
        P(espera[id]); // espero a que sea mi turno 
      }else{
        libre = false;
        V(mutexFila);
      }
      P(mutexTerminales);
      terminal = terminales.pop(); // reservo la terminal
      V(mutexTerminales);
      //UsarTerminal(terminalSube); // la uso
      P(mutexTerminales);
      terminales.push(terminal); // dejo la terminal en la pila
      V(mutexTerminales);
      // si hay gente esperando le paso el baton al primero, sino la dejo libre para el primero que llegue
      P(mutexFila);
      if(fila.isNotEmpty){
        V(espera[fila.pop()]);
      }
      else{
        libres++;
      }
      V(mutexFila);
    }
  ```

### Ejercicio 2

```
  cola transacciones; // asumo que viene cargada con 10000 transacciones
  sem mutexTransaccion;
  int terminaron = 0;
  sem mutexTerminados = 1;
  int resultados([9] 0);
  sem resLibres([9] 1)

  Process Worker [id: 1..7]
  {
    Transaccion t;
    int res;

    P(mutexTransaccion);
    while(transacciones.isNotEmpty()){
      t = transacciones.pop();// tomo la transaccion
      V(mutexTransaccion);
      res = //Validar(t); // valido la transaccion
      P(semLibres[res]);
      resultados[res] ++; // incremento la cantidad de transacciones con ese resultado
      V(semLibres[res]);
      P(mutexTransaccion);
    }
    V(mutexTransaccion);
    P(mutexTerminados);
    terminados ++; 
    if(terminados = 7){ // si soy el ultimo en terminar
      for i: 0..9{
        //Imprimir(resultados[i]);
      }
    }
    V(mutexTerminados);
  }
```

### Ejercicio 3
```
  cola fila;
  sem mutexFila = 1;
  sem espera ([U] 0);
  boolean libre = true;
  sem reponer = 0;
  sem lleno = 0;
  int cantLatas = 20;

  Process Usuario [id: 1..U]
  {
    P(mutexFila);
    if(not libre){
      fila.push(id); // me meto a la fila
      V(mutexFila);
      P(espera[id]) // espero mi turno
    }
    else{
      libre = false;
      V(mutexFila);
    }

    if(cantLatas = 0){ 
      V(reponer); // aviso al repositor que tiene que reponer
      P(lleno); // espero a que termine de reponer
    }

    cantLatas --; // tomo mi lata

    // si hay alguien en la cola le paso el baton, sino libero para que pueda usarlo cualquiera
    P(mutexFila);
    if(fila.isNotEmpty()){
      V(espera[fila.pop()]);
    }
    else{
      libre = true;
    }
    V(mutexFila);
  }

  Process Repositor
  {
    while(true){
      P(reponer); // espero a que me digan que no quedan latas
      cantLatas = 20; // repongo todas las latas
      V(lleno); // aviso que ya se pueden agarrar la tas
    }
  }
```

## Monitores

### Ejercicio 1
```
  Process Persona [id: 1..N]
  {
    boolean anciano = //soyAnciano();
    boolean embarazada = //soyEmbarazada();
    if(anciano){
      AutoridadMesa.llegarAnciano();
    }
    else if(embarazada){
      AutoridadMesa.llegarEmbarazada();
    }
    else{
      AutoridadMesa.llegarSinPrioridad();
    }
    //Votar();
    AutoridadMesa.salir();
  }

  Monitor AutoridadMesa
  {
    int cantAncianos = 0;
    int cantEmbarazadas = 0;
    int cantSinPrioridad = 0;
    cond esperaAncianos;
    cond esperaEmbarazadas;
    cond esperaSinPrioridad;
    boolean libre = true;

    procedure llegarAnciano()
    {
      if(not libre){
        cantAncianos ++;
        wait(esperaAncianos);
      }
      else{
        libre = false;
      }
    }

    procedure llegarEmbarazada()
    {
      if(not libre){
        cantEmbarazadas ++;
        wait(esperaEmbarazadas);
      }
      else{
        libre = false;
      }
    }

    procedure llegarSinPrioridad()
    {
      if(not libre){
        cantSinPrioridad ++;
        wait(esperaSinPrioridad);
      }
      else{
        libre = false;
      }
    }

    procedure salir()
    {
      // aca se implementa la prioridad
      if(cantAncianos > 0){
        cantAncianos --;
        signal(esperaAncianos);
      }
      else if(cantEmbarazadas > 0){
        cantEmbarazadas --;
        signal(esperaEmbarazadas);
      }
      else if(cantSinPrioridad > 0){
        cantSinPrioridad --;
        signal(esperaSinPrioridad);
      }
      else{
        libre = true;
      }
    }
  }
```

### Ejercicio 2
```
  Process Vendedor[id:1..20]
  {
    nroEquipo = //obtenerNroEquipo();
    Equipo[nroEquipo].llegar();
    int propiosVendidos = //venderEjemplares();
    Equipo[nroEquipo].sumarVendidos(propiosVendidos);
    int totalVendidos
    Equipo[nroEquipo].obtenerTotalVendidos(totalVendidos);
  }

  Monitor Equipo
  {
    int faltanLlegar = 20;
    int faltanTerminar = 20;
    int vendidos = 0;
    cond barrera;
    cond terminaron;

    Procedure llegar()
    {
      faltanLlegar --;
      if(faltanLlegar = 0){
        signalall(barrera);
      }
      else{
        wait(barrera);
      }
    }

    Procedure sumarVendidos(cant: int int)
    {
      vendidos += cant;
      faltanTerminar --;
    }

    Procedure obtenerTotalVendidos(cant: out int)
    {
      if(faltanTerminar > 0){
        wait(terminaron);
      }
      else{
        signalall(terminaron);
      }
      cant = vendidos;
    }
  }
```

### Ejercicio 3

```
  Process Escalador[id: 1..30]
  {
    //Escalar();
    Paso.llegar();
    //Pasar();
    Paso.salir();
    //Escalar();
  }

  Monitor Paso
  {
    boolean libre = true;
    int cantEsperando = 0;
    cond espera;

    Procedure llegar()
    {
      if(not libre){
        cantEsperando ++;
        wait(espera);
      }
      else{
        libre = false;
      }
    }

    Procedure salir()
    {
      if(cantEsperando > 0){
        cantEsperando --;
        signal(espera);
      }
      else{
        libre = true;
      }
    }
  }
```