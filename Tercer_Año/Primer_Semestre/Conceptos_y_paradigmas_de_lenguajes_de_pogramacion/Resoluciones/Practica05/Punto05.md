# Ejercicio 5
```Pascal
    Program Main;
    Var x, y, z:integer;
    a, b: array[1..6] of integer;
        Procedure B;
        var y,x: integer;
            Procedure C;
            var c:integer;
            begin
                y:= y + 2; c:=2;
                a(x):=a(x)*y;
                if (y >7) then
                b(y-6)=b(4)*2+b(y
                -6);
                D;
            end;
        begin
            x:=2; y:= x + 3;
            C; x:= x + 1; write (x,y);
        End;
        Procedure D;
        begin
            x:= c + 5 + x;
            y:= y + 2;
        end;
        Function C: integer;
        begin
            b(x):= b(x) + 1;
            x:= x + 1;
            a(y):=a(y)+b(x)+3;
            a(x+2)=a(x) + 2;
            return b(x);
        end
    begin
        x:= 1; Y:= 2;
        for z:=1 to 6 do begin
            a(z):= z;
            b(z):= z + 2;
        end;
        B;
        for z:= to 6 do write (a(z), b(z));
    end.
```

## Cadena estática 
- Main imprime 
  1 16  
  21 4  
  3  5  
  23 6  
  5  7  
  6  8  
- B imprime  
  3 7

|**Main**|
|------------------------------------|
|Pto retorno (*1)|
|EE ()|
|ED ()|
|x = ~~1~~ ~~2~~ 11|
|y = ~~2~~ 4|
|z = ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ 6|
|a(1) = 1 |
|a(2) = ~~2~~ ~~14~~ 21|
|a(3) = 3 |
|a(4) = ~~4~~ 23|
|a(5) = 5 |
|a(6) = 6 |
|b(1) = ~~3~~ ~~15~~ 16|
|b(2) = 4 |
|b(3) = 5 |
|b(4) = 6 |
|b(5) = 7 |
|b(6) = 8 |
|Procedure B|
|Procedure D|
|Function C|
|Valor retorno|
|------------------------------------|
|**B**|
|Pto retorno (*2)|
|EE(*1)|
|ED(*1)|
|y = ~~5~~ 7|
|x = ~~2~~ 3|
|Procedure C|
|Valor retorno|
|------------------------------------|
|**procedure C**|
|Pto retorno (*3)|
|EE(*2)|
|ED(*2)|
|c = 2|
|Valor retorno|
|------------------------------------|
|**D**|
|Pto retorno (*4)|
|EE(*1)|
|ED(*3)|
|Valor retorno = 4|
|------------------------------------|
|**function C**|
|Pto retorno (*5)|
|EE(*1)|
|ED(*4)|
|Valor retorno|



## Cadena dinámica
- Main imprime 
  1  3    
  14 4   
  3  5   
  4  6   
  5  7   
  6  8   
- B imprime  
  10 9

|**Main**|
|------------------------------------|
|Pto retorno (*1)|
|EE ()|
|ED ()|
|x = 1 |
|y = 2 |
|z = ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ 6|
|a(1) = 1 |
|a(2) = ~~2~~ 14 |
|a(3) = 3 |
|a(4) = 4 |
|a(5) = 5 |
|a(6) = 6 |
|b(1) = 3|
|b(2) = 4 |
|b(3) = 5 |
|b(4) = 6 |
|b(5) = 7 |
|b(6) = 8 |
|Procedure B|
|Procedure D|
|Function C|
|Valor retorno|
|------------------------------------|
|**B**|
|Pto retorno (*2)|
|EE(*1)|
|ED(*1)|
|y = ~~5~~ ~~7~~ 9|
|x = ~~2~~ ~~9~~ 10|
|Procedure C|
|Valor retorno|
|------------------------------------|
|**procedure C**|
|Pto retorno (*3)|
|EE(*2)|
|ED(*2)|
|c = 2|
|Valor retorno|
|------------------------------------|
|**D**|
|Pto retorno (*4)|
|EE(*1)|
|ED(*3)|
|Valor retorno|
