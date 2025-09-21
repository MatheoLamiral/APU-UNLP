# Punto 2
```C
    PRIMER_ARCHIVO.C 
    1. int x; 
    2. char *r;
    3.
    4. main()
    5. {
    6.      static int variable3;
    7.      extern int a;
    8.      int m, n;
    9.      for(n=0; n<10; n++)
    10.      {   char var1='C';
    11.         r=&var1;}
    12.}
    SEGUNDO_ARCHIVO.C
    13. static int auxiliar; 
    14. int a; 
    15. static int funcion2()
    16. {   extern int x;
    17.     auxiliar=auxiliar-2;
    18.     ...
    19. }
    20. int funcion3()
    21. {   int a;
    22.     a=a+4;
    23.     ...
    24. }
```

|Identificador | l-value | r-value | Alcance | Tiempo de vida |
|------------- |-------- |---------| --------| ---------------|
|x|Automática|0|(2-12)->(17-19)|1-24|
|r|Automática|null|3-12|1-24|
|r^|Dinámica|indefinido|3-12|11-12|
|main|--|--|5-12|4-11|
|variable3|Estática|0|7-12|<1-24>|
|m|Automática|indefinido|9-12|4-12|
|n|Automática|indefinido|9-12|4-12|
|var1|Automática|indefinido|11|4-12|
|auxiliar|Estática|0|14-24|<1-24>|
|a(14)|Automática|0|(15-21)->(8-12)|1-24|
|funcion2|--|--|16-24|<1-24>|
|funcion3|--|--|21-24|20-24|
|a(21)|Automática|indefinido|22-24|20-24|

