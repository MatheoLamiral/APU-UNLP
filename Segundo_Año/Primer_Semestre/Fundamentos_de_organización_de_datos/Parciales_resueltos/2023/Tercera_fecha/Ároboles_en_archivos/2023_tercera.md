Árbol B, orden 4, max 3, min 1.
Política de resolución de underflows derecha o izquierda.

## Inicial

                      2:0(100)4(300)1(600)3
            0:(10)  4:(200)  1:(400)(450)(500)   3:(700)

## +410

Intento insertar la clave 410 en el nodo 1, se produce overflow, entonces
creo el nodo 5, y divido la cantidad de claves en partres iguales entre 
el nodo 1 y el nodo 5, y promociono la clave del medio (como son pares sube
la del medio a la derecha), 450. Cuando intento insertar la clave 450 en el 
nodo 2 se produce overflow, entonces creo el nodo 6 y repito el mismo proceso. 
Creo el nodo 7 e inserto en él la clave que promocionó, 450.

L2,L1,E1,E5,E2,E6,E7.

                                    7:2(450)6
                   2:0(100)4(300)1             6:5(600)3
            0:(10)  4:(200)  1:(400)(410)   5:(500)   3:(700)


## -200

Elimino la clave 200 del nodo 4, se produce overflow, entonces reditribuyo
con su hermano derecho.

L7,L2,L4,L1,E4,E1,E2.

                                    7:2(450)6
                   2:0(100)4(400)1             6:5(600)3
                0:(10)  4:(300)  1:(410)   5:(500)   3:(700)

## -500

Elimino la calve 500 del nodo 5, se procude underflow, como su hermano derecho
no está en condiciones de redistribuir, y no tiene hermano izquierdo, concateno
con el hermano derecho y su padre la clave 600 en el nodo 6, esto produce la 
liberación del nodo 3. Se propaga el underflow hacia el nodo 6, entonces 
redistribuyo con su hermano izquierdo. Por último reacomodo punteros

L7,L6,L5,L3,E5,L2,E2,E6,E7.

                                    7:2(400)6
                         2:0(100)4             6:1(450)5
                      0:(10)  4:(300)       1:(410)  5:(600)(700)

## -100

Cambio de posición la clave 100, con la calve más chica de su subárbol derecho,
la clave 300 en el nodo 4, y elimino la clave 100, se produce underflow en el nodo 4,
como no tiene hermano derecho y su hermano izquierdo no esta en condiciones de 
redistribuir, concateno con el hermano izquierdo y su padre la clave 300 en el nodo 2,
se produce la liberación del nodo 4. Se propaga el underflow hacia el nodo 2, como su 
hermano derecho no está en condiciones de redistribuír y no tiene hermano izquierdo,
concateno con el hermano derecho y su padre la clave 400 en el nodo 7, se produce la 
liberación del nodo 6 y el nodo 7, y el árbol disminuye en 1 su nivel.

L7,L2,L4,L0,E0,L6,E2.

                                2:0(400)1(450)5
                       0:(10)(300)   1:(410)  5:(600)(700)
