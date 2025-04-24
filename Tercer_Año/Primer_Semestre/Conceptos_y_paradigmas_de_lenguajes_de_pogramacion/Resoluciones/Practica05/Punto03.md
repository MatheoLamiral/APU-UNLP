# Ejercicio 3
```Pascal
    PROGRAM P1;
    var
        a:integer;
        b:char;
        c: array[1..10] of integer
    Procedure PP1 ();
    var
    a:char;
    p:integer;
        Function x: integer;
        var
        z:integer;
        begin
        a:="j";
        z=-1;
        return z;
        end;
    Begin
        p:=x;
        write(a);
        p:=x+3;
        c[p]=8;
        p:=x+2;
        c[p]=x;
    end;
    Procedure x;
    var
        b:char;
        Procedure PP2;
        Begin
        write("para qué estoy aquí?");
        end;
    Begin
        a:=1;
        c[a]:=4;
        b:="a";
        write(concat(c[1],b)); /*concat convierte a string los
        parámetros, concatena y retorna un string;*/
        PP1();
        b:="b";
        write(concat(c[5],b)); /*concat convierte a string los
        parámetros, concatena y retorna un string;*/
    End;
    BEGIN
        a:=3;
        b:="c";
        for a:=3 to 10 do
        begin
            c[a]:=2*a;
        end;
        x;
        write(b);
        write(a);
        for a:=1 to 10 do
            write(c[a]-3);
    END.
```

## Cadena estática

- P1 imprime
  1  
  -4  
  5  
  3  
  5  
  7  
  9  
  11  
  13  
  15  
  17  
- Procedure x imprime  
  4 a  
  10 b  
- PP1 imprime  
  j

|**P1**|
|-------------------------------------------------|
|Pto retorno (*1)|
|EE ()|
|ED ()|
|a = ~~3~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ ~~7~~ ~~8~~ ~~9~~ ~~10~~ ~~1~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ ~~7~~ ~~8~~ ~~9~~ 10|
|b = "c"|
|c(1) = ~~4~~ -1|
|c(2) = 8|
|c(3) = 6|
|c(4) = 8|
|c(5) = 10|
|c(6) = 12|
|c(7) = 14|
|c(8) = 16|
|c(9) = 18|
|c(10) = 20|
|Procedure PP1|
|Procedure x|
|Valor retorno|
|-------------------------------------------------|
|**Procedure x**|
|Pto retorno (*2)|
|EE(*1)|
|ED(*1)|
|b = ~~"a"~~ "b"|
|Procedure PP2|
|Valor retorno|
|-------------------------------------------------|
|**PP1**|
|Pto retorno (*3)|
|EE(*1)|
|ED(*2)|""
|a = ~~"j"~~ ~~"j"~~ ~~"j"~~ "j"|
|p = ~~-1~~ ~~2~~ 1|
|Function x|
|Valor retorno = ~~-1~~ ~~-1~~ ~~-1~~ -1|
|-------------------------------------------------|
|**Function x**|
|Pto retorno (*4)|
|EE(*3)|
|ED(*3)|
|z = -1|
|Valor retorno|
|-------------------------------------------------|
|**Function x**|
|Pto retorno (*5)|
|EE(*3)|
|ED(*3)|
|z = -1|
|Valor retorno|
|-------------------------------------------------|
|**Function x**|
|Pto retorno (*6)|
|EE(*3)|
|ED(*3)|
|z = -1|
|Valor retorno|
|-------------------------------------------------|
|**Function x**|
|Pto retorno (*7)|
|EE(*3)|
|ED(*3)|
|z = -1|
|Valor retorno|

## Cadena dinámica

En este caso la seguir la cadena dinámica es igual a seguir la cadena estática