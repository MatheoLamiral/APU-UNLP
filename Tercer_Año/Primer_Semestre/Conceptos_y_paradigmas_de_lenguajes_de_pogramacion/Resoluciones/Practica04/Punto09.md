# Ejercicio 9
## Incisos a, b y c
```C
    static int aux;
    int v2;
    static int fun2()
    {
        extern int v1;
        aux=aux+1;
    }
    int fun3()
    {
        int aux;
    }
```
| Identificador | L-valor | R-valor | Alcance | T.vida | Inciso |
| --- | --- | --- | --- | --- | --- |
| aux | Estática | 0-1 | 1-8 10→ | 1-10 | B |
| v2 | Automática | 0 | 2-10 | 1-10 | A |
| fun2 |  |  | 3-10 | 3-6 | B |
| v1 | Automática | indefinido | 4-6 | 3-6 | A |
| fun3 |  |  | 7-10 | 7-10 | C |
| aux | Automática | indefinido | 8-10 | 7-10 | A |