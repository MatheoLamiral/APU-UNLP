# Ejercicio 6
```C
    Archivo.c
    {
        int x = 1;
        int func1()
        {
            int i;
            for (i=0; i < 4; i++)x=x+1;
        }
        int func2()
        {
            int i,j;
            /*sentencias que contienen declaraciones y sentencias que no contienen declaraciones*/
            ...
            for (i=0; i < 3; i++)j=func1+1;
        }
    }
```
En cuanto a alocación de memoria, en ambos casos, x se asigna en el segmento de datos del programa y su tiempo de vida abarca toda la ejecución del programa (es estático). Por ende, en cuanto a alocación de memoria, el comportamiento es el mismo  