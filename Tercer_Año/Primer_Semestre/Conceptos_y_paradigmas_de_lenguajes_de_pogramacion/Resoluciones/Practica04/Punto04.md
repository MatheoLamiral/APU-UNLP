# Ejercicio 4
## Inciso a
- Se denomina variable local a todas las referencias a variables creadas dentro de un bloque o método. Solo es accesible dentro de ese bloque y su vida útil es limitada al tiempo de ejecución de ese bloque.
- Se denomina variable global a todas las referencias creadas en el programa principal
## Inciso b
Una variable local puede declararse con un modificador como static (en C, C++, o similares), o de forma equivalente en otros lenguajes, para que su dirección de memoria (l-valor) y contenido se mantengan constantes entre ejecuciones del mismo bloque. 
Normalmente, las variables locales son automáticas: se crean en la pila al entrar a la función y se destruyen al salir. Sin embargo, si se marca como estática, ya no se crea ni destruye con cada ejecución, sino que su espacio se reserva en un segmento fijo del programa (como el segmento de datos), como una variable global, pero con ámbito restringido a la función donde se declaró.
- Ejemplo en C
```C
    void contar()
    {
        int x = 0;
        x++;
        printf("%d\n", x);
    }
```
## Inciso c
Una variable global no siempre es estática, su alcance no determina el momento en el que se aloca en memoria. Por ejemplo, en C se pueden declarar variables globales estáticas mediante el uso de “static” y variables globales no estáticas mediante el uso de “extern”.
## Inciso d
El l-valor de una variable estática es la dirección de memoria donde se almacena su valor. En cambio, las constantes no tienen un l-valor en el sentido tradicional. No ocupan una posición en la memoria de la misma manera que las variables, no tienen una dirección de memoria explícita, ya que su valor es tratado como un valor literal durante la compilación.