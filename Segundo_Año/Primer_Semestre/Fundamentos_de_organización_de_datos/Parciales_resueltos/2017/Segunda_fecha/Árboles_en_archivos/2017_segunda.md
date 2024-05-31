# Inciso A
```Pascal
        const 
            M = 8; {orden del árbol}
        type
            //datos 
            log=record
                user_num:integer;
                user:String;
                nom:String;
                ape:String;
                cant_mails:integer;
            end;

            //nodo del arbol 
            nodoArbol = record
                cant_datos:	integer;
                datos: array[1..M-1] of log;
                hijos: array[1..M] of integer;
            end;

            arbolB = file of nodoArbol;	

            var	
                archivoSistemas: arbolB;
```

# Inciso B
Árbol B+, orden 4, max 3, min 1.
Política de resolución de underflows derecha.

## Inicial

                            2: 0 (340) 1 (400) 1 (500) 3
        0: (11)(50)(77) 1 1: (340)(350)(360) 4 4: (400)(410)(420) 3 3: (520)(530)-1

## +150

Intento insertar la clave 150 en el nodo 0, se produce overflow, entonces creo el nodo 5
y divido la cantidad de claves en partes iguales entre el nodo 0 y el nodo 5, y promociono
una copia de la clave del medio (como son pares sube una copia de la del medio a la derecha) 77. 
Cuando intento insertar la clave 77 en el nodo 2 se produce overflow, entonces creo el nodo 6
y divido la cantidad de claves en partes iguales entre el nodo 2 y el nodo 6, y promociono
la clave del medio (como son pares sube la del medio a la derecha) 400. Creo el nodo 7
e inserto en él la clave que promocionó.

L2,L0,E0,E5,E2,E6,E7.

                                                7:(400)
                    2: 0 (77)5(340)1                                6: 4 (500) 3
        0: (11)(50) 5 5:(77)(150) 1 1: (340)(350)(360) 4   4: (400)(410)(420) 3 3: (520)(530)-1

## -500

Se busca la clave 500, pero como no se encuentra en una hoja, no se elimina. El árbol se mantiene igual
L7,L6,L3.

                                                7:(400)
                    2: 0 (77)5(340)1                                6: 4 (500) 3
        0: (11)(50) 5 5:(77)(150) 1 1: (340)(350)(360) 4   4: (400)(410)(420) 3 3: (520)(530)-1


