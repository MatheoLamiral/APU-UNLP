# Punto 2
```Pascal
    Program Main;
    var
        a:Integer;
        b:integer;
        v: array[1..5] of integer;
        Procedure B;
        var
            f:integer;
        begin
            f:=1;
            a:=f-3;
        end;//Procedure B
        Procedure A;
        var a:integer;
            Function f; ist t inteyer
            var
                t:integer;
            begin 
                t:=2;
                a:=b-4;
                if( b >0) then begin
                    b=a;
                    v(b)=t*4;
                end;
            return a;
            end; //Funcion f
        begin
            b:=f-a;
            B;
        end; //Procedure A
    begin//Programa principal
        a:=0;
        b:=-1;
        for b=1 to 5 do begin
            v[b]=b;
        end;
        A;
        write(a); 
        write(b);
        for b=1 to 5 do begin
            write (v[b]);
        end;
    end.
```

## Cadena estática 
- Main imprime  
  -2  
   0  
   8  
   2  
   3  
   4  
   5  

|**Main**|
|------------------------------------|
|EE()|
|ED()|
|Pto retorno (*1)|
|a = ~~0~~ -2 |
|b = ~~-1~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~1~~ ~~0~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ 5|
|v(1)= ~~1~~ 8 |
|v(2)= 2 |
|v(3)= 3 |
|v(4)= 4 |
|v(5)= 5 |
|Procedure B|
|Procedure A|
|Valor retorno|
|------------------------------------|
|**A**|
|Pto retorno (*2)|
|EE(*1)|
|ED(*1)|
|a = 1|
|Function f|
|Valor retorno 1 |
|------------------------------------|
|**f**|
|Pto retorno (*3)|
|EE(*2)|
|ED(*2)|
|t = 2|
|Valor retorno|
|------------------------------------|
|**B**|
|Pto retorno (*4)|
|EE(*1)|
|ED(*2)|
|f = 1|
|Valor retorno|

## Cadena dinámica
- Main imprime  
   0    
   0  
   8  
   2  
   3  
   4  
   5  

|**Main**|
|------------------------------------|
|EE()|
|ED()|
|Pto retorno (*1)|
|a = 0 |
|b = ~~-1~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~5~~ ~~1~~ ~~0~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ 5|
|v(1)= ~~1~~ 8 |
|v(2)= 2 |
|v(3)= 3 |
|v(4)= 4 |
|v(5)= 5 |
|Procedure B|
|Procedure A|
|Valor retorno|
|------------------------------------|
|**A**|
|Pto retorno (*2)|
|EE(*1)|
|ED(*1)|
|a = ~~1~~ -2|
|Function f|
|Valor retorno 1 |
|------------------------------------|
|**f**|
|Pto retorno (*3)|
|EE(*2)|
|ED(*2)|
|t = 2|
|Valor retorno|
|------------------------------------|
|**B**|
|Pto retorno (*4)|
|EE(*1)|
|ED(*2)|
|f = 1|
|Valor retorno|