```
sem mutexFila = 1;
cola fila;
sem llegada = 0;
boolean hayLugar = true;
sem mutexLugar = 1;
int asientoActual;
int faltanLlegar = 50;
sem respuesta ([C] 0);
sem espera = 0;

Process Cliente[id: 1..C]
{
    P(mutexFila);
    fila.push(id); // llego a la fila para comprar el boleto
    V(mutexFila);
    V(llegada); // aviso que llegue al vendedor
    P(respuesta[id]); // espero a que me diga si hay lugar
    // no hace falta exclusión ni en hayLugar ni asiento
    if(hayLugar){
        asiento = asientoActual;
        V(espera); // avisa que tomo su asiento
        faltanLlegar --;
        if(faltanLlegar = 0){
            V(barrera); // si soy el último, despierto al micro 
        }
            
    }
}

Process Vendedor
{
    for i: 1..50{
        P(llegada) // espera a que llegue alguien
        P(mutexFila);
        idCliente = fila.pop(); // tomo al cliente
        V(mutexFila);
        P(mutexAsientos);
        asientoActual = i; // seteo el asiento actual
        V(mutexAsientos);
        V(respuesta[idCliente]) // avisa al cliente que ya esta su asiento
        P(espera); // espera a que el cliente tome el asiento 
    }
    hayLugar = false;
    for i: 50..C+50{
        P(llegada) // espera a que llegue alguien
        P(mutexFila);
        idCliente = fila.pop(); // tomo al cliente
        V(mutexFila);
        V(respuesta[idCliente]) // avisa al cliente que no hay más lugar
    }
}

Process Micro
{
    if(faltanLlegar > 0){
        P(barrera); // espera a que lleguen todos
    }
}
```