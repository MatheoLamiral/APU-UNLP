# Ejercicio 1
## Inciso a
```Pascal
    1. Procedure Practica4();
    2. var
    3.      a,i:integer
    4.      p:puntero
    5. Begin
    6.      a:=0;
    7.      new(p);
    8.      p:= ^i
    9.      for i:=1 to 9 do
    10.         a:=a+i;
    11.     end;
    12.     ...
    13.     p:= ^a;
    14.     ...
    15.     dispose(p);
    16. end;
```
| Identificador | Tipo | r-valor | Alcance | Tiempo de vida |
| --- | --- | --- | --- | --- |
| a | Automática | indefinido | 4-16 | 1-16 |

## Inciso b
| Identificador | Tipo |  | r-valor |  | Alcance |  | Tiempo de vida |  |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| p | p: automática | p^: Dinámica | p: nill | p^: indefinido | p: 5-16 | p^:5-16 | p:1-16 | p^:7-15 |
