Árbol B, orden 4, max 3, min 1.
Política de resolución de underflows derecha.

## Inicial 

                    2:0(100)1(310)4(600)3
        0:(60) 1:(160)(210)(240) 4:(420)(490) 3:(800) 


## +260

Intento insertar la calve 260 en el nodo 1, se produce overflow, entonces creo el nodo 5 
y divido la canitdad de calves en partes iguales etre el nodo 1 y el nodo 5, y promociono 
la clave del medio (como son pares sube la del medio a la dercha), 240. Cuando intento 
insertar la clave 240 en el nodo 2, se produce overflow, entonces creo el nodo 6 y divido
las claves en partes iguales entre el nodo 2 y el nodo 6 y promociono la clave del medio
(como son pares sube la del medio a la derecha), 310. Creo el nodo 7 e inserto en él la
clave que promocionó.

L2,L1,E1,E5,E2,E6,E7.

                                    7:2(310)6
                2:0(100)1(240)5                  6:4(600)3
            0:(60) 1:(160)(210) 5:(260)     4:(420)(490) 3:(800) 


## -310

Cambio de posición la clave 310, con la calve mas chica de su subárbol derecho 420 en el nodo 
4, y elimino la clave 310 del nodo 4.

L7,L6,L4,E4,E7.

                                    7:2(420)6
                2:0(100)1(240)5                  6:4(600)3
            0:(60) 1:(160)(210) 5:(260)        4:(490) 3:(800)


## -490

Elimino la calve 490 del nodo 4, se produce underflow, como no puedo redistribuir con su hermano 
derecho, concateno. Se libera el nodo 3, y se propaga el underflow al nodo 6, como no tiene hermano 
derecho, redistribuyo con el nodo 2. Por último redistribuyo punteros.

L7,L6,L4,L3,E4,E3,L2,E2,E6,E7.

                                    7:2(240)6
                         2:0(100)1              6:5(420)4
                    0:(60) 1:(160)(210)    5:(260) 4:(600)(800)

## -60

Elimino la clave 60 del nodo 0, se produce underflow, entonces redistribuyo con su hermano derecho.

L7,L2,L0,L1,E0,E1,E2.

                                    7:2(240)6
                         2:0(160)1              6:5(420)4
                      0:(100) 1:(210)       5:(260) 4:(600)(800)