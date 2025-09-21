# Punto 1
```ADA
    1. with text_io; use text_io;
    2. Procedure Main is;
    3. type arreglo_int is array(integer range <>);
    4. c, o, r:integer;
    5. vec:arreglo(1..100);
    6. c3: constant integer:=10;
    7. Procedure Proc is;
        7.1. type pointer is access integer;
        7.2. vec2:arreglo(0..c3);
        7.3. r. pointer,
        7.4. begin
            7.4.1. 0:=4;
            7.4.2. vec2(o):= vec2(1) + vec(5);
            7.4.3. r.= new pointer,
            7.4.4. free r;
        7.5. end;
    8. begin
    9. 0:=5;
    10. Proc;
    11. c:= o+ 2;
    12. end.
```
|Identificador | l-value | r-value | Alcance | Tiempo de vida |
|------------- |-------- |---------| --------| ---------------|
|Procedure Main|--|--|3-12|2-12|            
|c|Automática|indefinido|5-12|2-12|
|o|Automática|indefinido|5-12|2-12|
|r|Automática|indefinido|(5-7.3) -> (8-12)|2-12|
|vec|Automática|indefinido|6-12|2-12|
|c3|Automática|10|7-12|2-12|
|Procedure Proc|--|--|7.1-7.5|7-7.5|
|vec2|SemiDinámica|indefinido|7.3 - 7.5|7-7.5|
|r|Automámitca|null|7.4-7.5|7-7.5|
|r^|Dinámica|indefinido|7.4-7.5|7.4.3-7.4.4|


