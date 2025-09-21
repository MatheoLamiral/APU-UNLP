# Punto 3
```Pascal
1.  Program primer_parcial;
2.  const DLY = 10;
3.  var Maxp, Ptos: integer;
4.  mejor, defi: boolean;
5.  marca: array[1..6] of integer;
6.  Ptr: ^Real;
7.  procedure Inicia;
8.  var ver, ptos integer;
9.  begin 
10.     ver:1;..
11. end; 
12. procedure Abre;
13. const text ='hola';
14. var contaX, defi: integer;
15. marca: array[1..12] of integer;
16. Ptr: ^Real;
17. begin 
18.     new (Ptr);
19.     ...
20.     Dispose (Ptr);
21. end;
22. begin
23.     Maxp=0;
24.     Ptos=0;
25.     Inicia;
26.     new (Ptr);
27.     repeat abre;
28.     until defi;
29.     Dispose (Ptr)
30. end.
```
|Identificador|L-valor|Alcance|Tiempo de vida|
|-------------|-------|-------|-------------|
|primer_parcial|--|2-30|1-30|
|DLY | Automática|3-30|1-30|
|Maxp| Automática|4-30|1-30|
|Ptos| Automática|(4-8)->(12-30)|1-30|
|mejor| Automática|5-30|1-30|
|defi| Automática|(5-14)->(22-30)|1-30|
|marca| Automática|(6-15)->(22-30)|1-30|
|Ptr| Automática|(7-16)->(22-30)|1-30|
|Ptr^|Dinámica|(7-16)->(22-30)|26-29|
|Inicia|--|8-30|7-11|
|ver|Automática|9-11|7-11|
|ptos|Automática|9-11|7-11|
|Abre|--|13-30|12-21|
|text|Automática|14-21|12-21|
|contaX|Automática|15-21|12-21|
|defi|Automática|15-21|12-21|
|marca|Automática|16-21|12-21|
|Ptr|Automática|17-21|12-21|
|Ptr^|Dinámica|17-21|18-20|




