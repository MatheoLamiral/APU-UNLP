# Inciso c

Árbol B, orden 10, max 9, min 4.
Política de resolución de underflows izquierda 

## Inicial

                        2:0(33)1
            0:(5)(10)(25)(30)   1:(40)(45)(56)(61)(66)(72)(77)(80)(96)

## -10

Elimino la clave 10 del nodo 0, se porduce undeflow, como no tiene hermano
izquierdo redistribuyo con su hermano derecho.

L2,L0,L1,E0,E1,E2.

                                 2:0(56)1
           0:(5)(25)(30)(33)(40)(45)    1:(61)(66)(72)(77)(80)(96)


# Inciso d

Teniendo en cuenta que la fórmula para averiguar la cantidad mínima de elementos es
[m/2] - 1, para averiguar los posibles ordenes de un árbol a partir de la 
cantidad mínima de elementos = 3, tenemos que ver cual es el orden más grande 
que nos da como mínimo de elementos 2, y el mas chico que nos da como mínimo 
de elementos 4.
El orden 7, nos da como mínimo de elementos 2, y el 8 nos da como mínimo 3, por 
ende sabemos que a partir de 8 la cantidad mínima será 3, ahora debemos saber 
a partir de que orden deja de dar como mínimo 3. 
El odren 9, nos da como mínimo de elementos 3, y el 10, nos da como mínimo 4,
por ende sabemos que hasta 9 la cantidad mínima será 3.
Entonces, los órdenes posibles son 8 o 9.

# Inciso e 

Teniendo en cuenta que la cantidad máxima de elementos del nodo de un árbol
es el orden - 1 , si queremos saber el orden a partir de la cantidad máxima
de elementos, debemos sumarle 1 a la cantidad máxima y asi sabremos el orden.
Entonces si max = 1289, el orden = 1290.



