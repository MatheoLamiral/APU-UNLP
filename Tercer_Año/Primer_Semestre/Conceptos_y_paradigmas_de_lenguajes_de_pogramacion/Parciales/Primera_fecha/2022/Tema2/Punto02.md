# Punto 2
```C
    PRIMER_ARCHIVO.C 
    1. int y; 
    2. char *s;
    3.
    4. main()
    5. {
    6.      static int var3;
    7.      extern int b;
    8.      int m, n;
    9.      for(n=0; n<10; n++)
    10.      {   char var2='C';
    11.         r=&var2;}
    12.}
    SEGUNDO_ARCHIVO.C
    13. static int auxi; 
    14. int b; 
    15. static int funcion2()
    16. {   extern int y;
    17.     auxi=auxi-2;
    18.     ...
    19. }
    20. int funcion3()
    21. {   int b;
    22.     b=b+4;
    23.     ...
    24. }
```

|Identificador | l-value | r-value | Alcance | Tiempo de vida |
|------------- |-------- |---------| --------| ---------------|
|y|Automática|0|(2-12)->(17-19)|1-24|
|s|Automática|null|3-12|1-24|
|s^|Dinámica|indefinido|3-12|11-12|
|main|--|--|5-11|4-11|
|var3|Estática|0|7-12|<1-24>|
|m|Automática|indefinido|9-12|4-12|
|n|Automática|indefinido|9-12|4-12|
|var2|Automática|indefinido|11|4-12|
|auxi|Estática|0|14-24|<1-24>|
|b(14)|Automática|0|(15-19)->(8-12)|1-24|
|funcion2|--|--|16-19|<1-24>|
|funcion3|--|--|21-24|20-24|
|b(21)|Automática|indefinido|22-24|20-24|