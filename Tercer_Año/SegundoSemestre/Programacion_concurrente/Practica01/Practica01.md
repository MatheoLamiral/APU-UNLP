# Práctica 1 - Variables compartidas

### Ejercicio 1

- **A)** Verdadero. Puede ocurrir **a**, en el caso de que se ejecute primero P1, dejando a `x` en 10, luego P2 dejando a `x` en 11, y por último P3 dejando a `x` en 56

- **B)** Verdadero. Puede ocurrir **b**, en el caso de que se ejecute `x:= (x*3)` de P3, manteniendo `x` en 0, luego P1, dejando a `x` en 10, luego vuelve a P3 ejecutando `+ (x*2) + 1` dejando a `x` en 21, y por último ejecutando `x:= x + 1` de P2, dejando a `x` en 22

- **C)** Verdadero. Puede ocurrir **c**, en el caso de que se ejecute `x:= (x*3)` de P3, manteniendo `x` en 0, luuego P1, dejando `x` en 10, luego P2, dejando a `x` en 11, y por último `+ (x*2) + 1` dejando a `x` en 23

### Ejercicio 2
Asumo que tengo un vector de rengo 30, y busco repeticiones del número 10
```kotlin
    int rango := 30
    int n := 10
    int procesos := 3

    arreglo vec;
    repeticiones_n := 0;
    
    Process Repeticiones[id: 0..2]
    {
        rep := 0
        int inicio := id*(rango/procesos)
        int fin := inicio + (rango/procesos)
        for i in inicio .. fin
        {
            if(arreglo(i) = n)
                rep := rep + 1;
        }
        <repeticiones_n := rep;>
    }
```

### Ejercicio 3
- **A)** El código no funciona, ya que podría ejecutarse la instrucción `elemento = buffer[pri_ocupada];` antes de ejecutar `buffer[pri_vacia] = elemento;`, generando la posibilidad de que el consumidor consuma antes de que el productor porduzca, o `<await (cant > 0); cant-- >` y luego `<await (cant < N); cant++>; buffer[pri_vacia] = elemento;`, generando la posibilidad de que el productor produzca antes de que el consumidor libere el buffer
  - Código modificado: 
    ```kotlin
        int cant = 0; 
        int pri_ocupada = 0; 
        int pri_vacia = 0; 
        int buffer[N];

        Process Productor::
        {   while (true)
            {   produce elemento
                <await (cant < N); cant++; buffer[pri_vacia] = elemento;>
                pri_vacia = (pri_vacia + 1) mod N;
            }
        }

        Process Consumidor::
        {   while (true)
            {   <await (cant > 0); cant--; elemento = buffer[pri_ocupada];>
                pri_ocupada = (pri_ocupada + 1) mod N;
                consume elemento
            }
        }
    ```
- **B)** Código para que funcione para C consumidores y P productores: 
    ```kotlin
        int cant = 0; 
        int pri_ocupada = 0; 
        int pri_vacia = 0; 
        int buffer[N];
        const int C =;
        const int P =;

        Process Productor [id: 0..P] ::
        {   while (true)
            {   produce elemento
                <await (cant < N); cant++; buffer[pri_vacia] = elemento;
                pri_vacia = (pri_vacia + 1) mod N;>
            }
        }

        Process Consumidor [id: 0..C] ::
        {   while (true)
            {   <await (cant > 0); cant--; elemento = buffer[pri_ocupada];>                
                pri_ocupada = (pri_ocupada + 1) mod N;
                consume elemento
            }
        }
    ```

### Ejercicio 4

```kotlin
    recurso cola [5];
    recurso rec;
    const int N=;
    Process Consumidor [id: 0..N]::
    {   while(true)
        {
            <await (cola is not empty); rec := cola.pop>
            usar recurso
            <cola.push(rec)>
        }
    }
``` 

### Ejercicio 5
- Inciso a 
    ```kotlin
        Process Persona [id: 1..N]
        {
            <imprimir(documento)>
        }
    ```
- Inciso b
    ```kotlin
        cola c;
        int siguiente = -1;

        Process Persona [id: 1..N]
        {
            <if (siguiente = -1)
                sigiente = id
            else
                c.push(id)>
            <await (siguiente == id); imprimir(documento)>
            <if(c.isEmpty()) 
                siguiente == -1
            else
                siguiente == c.pop() >
        }
    ```
- Inciso c 
    ```kotlin
        colaOrdenada c;

        Process Persona [id: 1..N]
        {
            <if (siguiente = -1)
                sigiente = id
            else
                c.push(id, id)>
            <await (siguiente == id); imprimir(documento)>
            <if(c.isEmpty()) 
                siguiente == -1
            else
                siguiente == c.pop() >
        }
    ```
- Inciso d 
    ```kotlin
        cola c;
        int siguiente = -1;
        boolean termino = false;

        Process Persona [id: 1..N]
        {
            <c.push(id)>
            <await (siguiente == id); imprimir(documento)> termino = true;
        }

        Process Coordinador
        {
            <await (cola.isNotEmpty()); siguiente = c.pop()>
            <Await (termino); termino = false>            
        }
    ```
### Ejercicio 6
No cumple con la propiedad de **ausencia de demora innecesaria**, ya que puede pasar que un proceso trate de entrar a su SC, mientras el otro está en sus SNC o no terminó, y el primero esté impedido de entrar a su SC.

### Ejercicio 7

```kotlin
    int arribo([n-1] 0), continuar([n]-1 0);

    process Worker[i=1 to n]
    { while (true)
        {arribo[i] = 1;
        while (continuar[i] == 0) skip; 
        SC
        continuar[i] = 0;
        }
    }

    process Coordinador
    { while (true)
        { for [i = 1 to n]
            { if(arribo[i] == 1) 
                continuar[i] = 1;
              while(continuar[i] == 1) skip;
            }
        }
    }
```