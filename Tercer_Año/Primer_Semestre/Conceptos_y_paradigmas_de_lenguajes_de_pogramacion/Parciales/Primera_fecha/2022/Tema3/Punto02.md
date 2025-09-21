# Punto 2
```ADA

1. with text_io; use text_io;
2. Procedure Principal is; 
3. type puntero is access integer;
4. e, k, l:integer; 
5. m: constant integer:=8;
6. Procedure Uno is;
    6.1. k:char;
    6.2. l: puntero; 
    6.3. begin 
        6.3.1. k:="Z";
        6.3.2. k:="X";
        6.3.3. l:= new puntero; 
        6.3.4. ...
        6.3.5. free l; 
    6.4  end;
7. Procedure Dos is;
7.1. k:boolean; 
7.2. e:char;
7.3. begin
    7.3.1. k:=T;
    7.3.2. e:= "X";
    7.3.3. l:=m;
    7.3.4. l:=7;
7.4. end;
8. begin
9.  k:=5;
10. Uno;
11. Dos;
12. e:= k +2,
13. end.
```
|Identificador | l-value | r-value | Alcance | Tiempo de vida |
|------------- |-------- |---------| --------| ---------------|
|Principal|--|--|3-13|2-13|
|e(4)|Automática|indefinido|(5-7.2)(8-13)|2-13|
|k(4)|Automática|indefinido|(5-6.1)(7-7.1)(8-12)|2-13|
|l(4)|Automática|indefinido|(5-6.2)(7-13)|2-13|
|m|Automática|indefinido|6-13|<2-13>|
|Uno|--|--|6-13|6-6.4|
|k(6.1)|Automática|indefinido|6.2-6.4|6-6.4|
|l(6.2)|Automática|null|6.3-6.4|6-6.4|
|l^(6.2)|Dinámica|indefinido|6.3-6.4|6.3.3-6.3.5|
|Dos|--|--|7.1-13|7-7.4|
|k(7.1)|Automática|indefinido|7.2-7.4|7-7.4|
|e(7.2)|Automática|indefinido|7.3-7.4|7-7.4|

