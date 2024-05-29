Árbol B+, orden 4, máx 3, min 1.
Política de resolución de underflows: izquierda.

            2: 0(238)3(547)1(729)4

0:(145)3    3:(238)(402)(512)1  1:(614)4    4:(729)-1

- *+500*

Intento insertar la clave 500 en el nodo 3, se produce overflow, entonces
creo el nodo 5 y divido la cantidad de claves en partes iguales entre el 
nodo 3 y el nodo 5, y promociono una copia de la clave del medio (como son
pares, sube la copia de la clave del medio a la derecha), 512. Se propaga 
overflow hacia el nodo 2, creo el nodo 6 y divido la cantidad de claves en 
partes iguales entre el nodo 2 y el nodo 6 y promociono la clave del medio
(como son pares, sube la clave del medio a la derecha), 547. Creo el nodo 7 
e inserto en él la clave que promocionó.

                        7:2(547)6
            
        2:0(238)3(500)5          6:1(729)4

0:(145)3  3:(238)(402)5  5:(500)(512)1  1:(614)4  4:(729)-1

L2,L3,E3,E5,E2,E6,E7.


- *-145*

Elimino la clave 145 del nodo 0, se produce underflow, como no tiene hermano
izquierdo, redistribuyo con su hermano adyacente derecho, el nodo 3, entonces
la clave 238 del nodo 3 pasa al nodo 0 y se actualiza el separador en el 
nodo 2 con una copia de la clave 402.

                        7:2(547)6
            
        2:0(402)3(500)5          6:1(729)4

0:(238)3  3:(402)5  5:(500)(512)1  1:(614)4  4:(729)-1

L7,L2,L0,L3,E0,E3,E2.


- *-402*

Elimino la clave 402 del nodo 3, se produce underflow, entonces concateno
con su hermano adyacente izquierdo, el nodo 0, esto produce la liberación
del nodo 3. Por último actualizo los separadores del nodo 2.

                      7:2(547)6
            
            2:0(500)5          6:1(729)4

    0:(238)3  5:(500)(512)1  1:(614)4  4:(729)-1

L7,L2,L3,L0,E0,E2.
