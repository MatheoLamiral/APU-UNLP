# Punto 2
```ADA
    1. with text_io; use text_io;
    2. Procedure Main is;
    3. type arreglo_int is array(integer range <>);
    4. a, n, p:integer;
    5. vec1:arreglo(1..100);
    6. c: constant integer:=10;
    7. Procedure Uno is;
        7.1. type puntero is access integer;
        7.2. vec2:arreglo(0..c);
        7.3. p: puntero,
        7.4. begin
            7.4.1. n:=4;
            7.4.2. vec2(n):= vec2(1) + vec1(5);
            7.4.3. p:= new puntero,
            7.4.4. free p;
        7.5. end;
    8. begin
    9. n:=5;
    10. Uno;
    11. a:= n + 2;
    12. end.
```
|Identificador | l-value | r-value | Alcance | Tiempo de vida |
|------------- |-------- |---------| --------| ---------------|
|Procedure Main|--|--|3-12|2-12|            
|a|Automática|indefinido|5-12|2-12|
|n|Automática|indefinido|5-12|2-12|
|p|Automática|indefinido|(5-7.3) -> (8-12)|2-12|
|vec1|Automática|indefinido|6-12|2-12|
|c|Automática|indefinido|7-12|2-12|
|Procedure Uno|--|--|7.1-7.5|7-7.5|
|vec2|SemiDinámica|indefinido|7.3 - 7.5|7-7.5|
|p|Automámitca|null|7.4-7.5|7-7.5|
|p^|Dinámica|indefinido|7.4-7.5|7.4.3-7.4.4|


