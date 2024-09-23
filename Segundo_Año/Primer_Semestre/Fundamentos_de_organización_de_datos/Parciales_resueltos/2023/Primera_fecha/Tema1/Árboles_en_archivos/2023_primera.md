Árbol B, orden 4, max 3, min 1.
Política de resolución de underflows izquierda.

## Inicial

                    2:0(10)1(30)4(60)3
        0:(5)   1:(15)(20)(23)  4:(42)(48)  3:(70)

## +25

Intento insertar la clave 25 en el nodo 1, se produe overflow, entonces creo el nodo 5, y divio en partes iguales la cantidad de claves entre el nodo 1 y el nodo 5, y promociono clave del medio (como son pares sube la del medio a la derecha), 23. Cuando intento insertar la clave 23 en el nodo 2, se produe overflow, entonces creo el nodo 6, y divio en partes iguales la cantidad de claves entre el nodo 2 y el nodo 6, y promociono clave del medio (como son pares sube la del medio a la derecha), 30. Creo el nodo 7 en inserto en él la clave que promocionó.

L2,L1,E1,E5,E2,E6,E7.

                            7:2(30)6
              2:0(10)1(23)5             6:4(60)3
        0:(5)   1:(15)(20)  5:(25)  4:(42)(48)  3:(70)


## -30

Cambio de posición la clave 30, con la clave mas chica de su subárbol derecho, 42 en el nodo 4, y elimino la clave 30 del nodo 4.

L7,L6,L4,E4,E7.

                            7:2(42)6
              2:0(10)1(23)5             6:4(60)3
        0:(5)   1:(15)(20)  5:(25)  4:(48)  3:(70)


## -5

Elimino la clave 5 del nodo 0, se produce underflow, como no tiene hermano izquierdo redistribuyo con su hermano derecho.

L7,L2,L0,L1,E0,E1,E2.

                            7:2(42)6
              2:0(15)1(23)5             6:4(60)3
        0:(10)   1:(20)  5:(25)  4:(48)  3:(70)

## -48

Elimino la clave 48 del nodo 4, se produce underflow, como no tiene hermano izquierdo y su hermano derecho no esta en condiciones de redistribuir, concateno, esto produce la liberación del nodo 3, y underflow en el nodo 6, redistribuyo con el hermano izquierdo del nodo 6 y reacomodo los punteros.

L7,L6,L4,L3,E4,L2,E2,E6,E7.

                        7:2(23)6
              2:0(15)1             6:5(42)4
           0:(10)   1:(20)     5:(25)  4:(60)(70)  