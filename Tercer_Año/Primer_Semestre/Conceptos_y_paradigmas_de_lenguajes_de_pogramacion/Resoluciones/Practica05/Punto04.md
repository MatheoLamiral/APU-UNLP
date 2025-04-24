# Ejercicio 4
```Pascal
    Procedure Main;
    var x, y: integer;
    vec: array[1..7] of integer;
        Function B:integer;
            var y:integer;
            begin
                y:=4; x:= y - 2;
                return (x);
            end;
        Procedure D;
        var i, x: integer;
        vec: array[1..7] of integer;
            Procedure A;
                var y:integer;
            begin
            y:=x + 5; vec(i + 2):=
            vec(i + 2) + y;
            x:= x +B; C;
            end;
            Function B:integer;
            begin
                vec(i):= y + 2; i:=i+2;
                vec(i):= vec(1) * i;
                return ( vec(i)-vec(1) );
            end;
        begin
            for x:= 1 to 7 do vec(x):= 1;
            x:=1; i:= 2;
            if y = 7 then A; else C;
            for x:= 1 to 7 do write(vec(x));
        end;
        Procedure C;
        var i, y: integer;
        begin
            i:= 1; y:= 6; x:= x + B;
            vec(2):= vec(2) * x;
            while (i < y) do begin
                vec(i):= vec(i) + B - 1;
                i:= i + 3;
            end;
            y:= y - 4;
        end;
    begin
        for x:= 1 to 7 do vec(x):= x;
        x:= 3; y:= B+5; D;
        if (x = 2) then begin
            vec(x):= vec(x) + 2;
            vec(x + 3):= vec(x) * 3;
        end;
        for x:= 1 to 7 do write(vec(x));
    end.
```

## Cadena estática  
- Main imprime  
  2  
  10  
  3  
  5  
  30  
  6  
  7   
- D imprime  
  1  
  9  
  1    
  4  
  1  
  1  
  1  

|**Main**|
|-------------------------------------------|
|Pto retorno (*1)|
|EE ()|
|ED ()|
|x = ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ ~~7~~ ~~3~~ ~~2~~ ~~2~~ ~~4~~ ~~2~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ 7 |
|y = 7|
|vec(1) = ~~1~~ 2|
|vec(2) = ~~2~~ ~~8~~ 10|
|vec(3) = 3|
|vec(4) = ~~4~~ 5|
|vec(5) = ~~5~~ 30|
|vec(6) = 6|
|vec(7) = 7|
|Function B|
|Procedure D|
|Procedure C|
|Valor retorno = 2|
|-------------------------------------------|
|**Function B**|
|Pto de retrorno (*2)|
|EE(*1)|
|ED(*1)|
|y = 4|
|Valor de retorno|
|-------------------------------------------|
|**D**|
|Pto retorno (*3)|
|EE(*1)|
|ED(*1)|
|i = ~~2~~ 4|
|x = ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ ~~7~~ ~~1~~ ~~40~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ 7|
|vec(1) = 1|
|vec(2) = ~~1~~ 9|
|vec(3) = 1|
|vec(4) = ~~1~~ ~~7~~ 4|
|vec(5) = 1|
|vec(6) = 1|
|vec(7) = 1|
|Procedure A|
|Function B|
|Valor retorno|
|-------------------------------------------|
|**A**|
|Pto de retorno (*4)|
|EE(*3)|
|ED(*3)|
|Y = 6|
|Valor de retorno = 3|
|-------------------------------------------|
|**Function B**|
|Pto de retorno (*5)|
|EE(*3)|
|ED(*4)|
|Valor de retorno|
|-------------------------------------------|
|**C**|
|Pto de retorno (*6)|
|EE(*1)|
|ED(*4)|
|i = ~~1~~ ~~4~~ ~~7~~ 3|
|Y = ~~6~~ 4|
|Valor de retorno = ~~2~~ ~~2~~ 2|
|-------------------------------------------|
|**Function B**|
|Pto de retrorno (*7)|
|EE(*1)|
|ED(*6)|
|y = 4|
|Valor de retorno|
|-------------------------------------------|
|**Function B**|
|Pto de retrorno (*8)|
|EE(*1)|
|ED(*6)|
|y = 4|
|Valor de retorno|
|-------------------------------------------|
|**Function B**|
|Pto de retrorno (*9)|
|EE(*1)|
|ED(*6)|
|y = 4|
|Valor de retorno|

## Cadena dinámica
- Main imprime  
  1  
  4  
  3  
  4  
  12  
  6  
  7  
- D imprime    
  8  
  352  
  39  
  28  
  40  
  1  
  1  

|**Main**|
|-------------------------------------------|
|Pto retorno (*1)|
|EE ()|
|ED ()|
|x = ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ ~~7~~ ~~3~~ ~~2~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ 7 |
|y = 7|
|vec(1) = 1|
|vec(2) = ~~2~~ 4|
|vec(3) = 3|
|vec(4) = 4|
|vec(5) = ~~5~~ 12|
|vec(6) = 6|
|vec(7) = 7|
|Function B|
|Procedure D|
|Procedure C|
|Valor retorno = 2|
|-------------------------------------------|
|**Function B**|
|Pto de retrorno (*2)|
|EE(*1)|
|ED(*1)|
|y = 4|
|Valor de retorno|
|-------------------------------------------|
|**D**|
|Pto retorno (*3)|
|EE(*1)|
|ED(*1)|
|i = ~~2~~ 4|
|x = ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ ~~7~~ ~~1~~ ~~28~~ ~~44~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~6~~ 7|
|vec(1) = ~~1~~ 8|
|vec(2) = ~~1~~ ~~8~~ 352|
|vec(3) = ~~1~~ ~~24~~ ~~8~~ 39|
|vec(4) = ~~1~~ ~~7~~ 28|
|vec(5) = ~~1~~ 40|
|vec(6) = 1|
|vec(7) = 1|
|Procedure A|
|Function B|
|Valor retorno|
|-------------------------------------------|
|**A**|
|Pto de retorno (*4)|
|EE(*3)|
|ED(*3)|
|Y = 6|
|Valor de retorno = 27|
|-------------------------------------------|
|**Function B**|
|Pto de retorno (*5)|
|EE(*3)|
|ED(*4)|
|Valor de retorno|
|-------------------------------------------|
|**C**|
|Pto de retorno (*6)|
|EE(*1)|
|ED(*4)|
|i = ~~1~~ ~~3~~ ~~5~~ 8|
|Y = ~~6~~ 2|
|Valor de retorno = ~~16~~ 32|
|-------------------------------------------|
|**Function B**|
|Pto de retrorno (*7)|
|EE(*3)|
|ED(*6)|
|Valor de retorno|
|-------------------------------------------|
|**Function B**|
|Pto de retrorno (*8)|
|EE(*3)|
|ED(*6)|
|Valor de retorno|
