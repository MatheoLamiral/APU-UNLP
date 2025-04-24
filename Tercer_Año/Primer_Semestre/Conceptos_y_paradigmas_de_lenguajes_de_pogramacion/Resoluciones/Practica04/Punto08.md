# Ejercicio 8
```Pascal
    1-  Program Uno;
    2-      type tpuntero= ^integer;
    3-      var mipuntero: tpuntero;
    4-      var i:integer;
    5-      var h:integer;
    6-  Begin
    7-      i:=3;
    8-      mipuntero:=nil;
    9-      new(mipuntero);
    10-     mipunterno^:=i;
    11-     h:= mipuntero^+i;
    12-     dispose(mipuntero);
    13-     write(h);
    14-     i:= h- mipuntero;
    15- End.

```
## Inciso a
- rango de instrucciones que representa el tiempo de vida de las variables i, h y mipuntero
  - `i`: 1 – 15 
  - `h`: 1 – 15 
  - `mipuntero`: 1 – 15 
  - `mipuntero^`: 9  - 12

## Inciso b
- rango de instrucciones que representa el alcance de las variables i, h y mipuntero.
  - `i`: 5 - 15                   
  - `h`: 6 - 15
  - `mipuntero`: 4 – 15
  - `mipuntero^`: 4  - 15

## Inciso c
No ocurre ningún error de tipo sintáctico o semántico, ya que `mipuntero` es un puntero a un entero, por ende, `mipuntero^` trae un entero, se le suma `i`, y eso se le asigna a `h` siendo ambos de tipo entero. 
## Inciso d
Ocurre error de semántico, ya que `mipuntero` es de tipo `tpuntero`, por ende, al restarle `mipuntero` a `h` (integer) se produce un error de tipos
## Inciso e
Los programas, procedimientos o funciones, tambien son entidades a las cuales se les puede asignar un tiempo de vida y un alcance. En este caso, `Program Uno` tiene un tiempo de vida de 1 a 15, y un alcance de 1 a 15.
## Inciso f
- `mipuntero`: Automática
- `mipuntero^`: Dinámica
- `i`: Automática
- `h`: Automática
