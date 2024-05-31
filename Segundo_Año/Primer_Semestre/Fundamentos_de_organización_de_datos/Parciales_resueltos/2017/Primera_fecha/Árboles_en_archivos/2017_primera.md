
Árbol B, orden 5, max 4, min 1.
Política de resolución de underflows izquierda.              


## Inicial                   
                        2: 0 (320) 1 (490) 4(555) 5(641) 3
    0: (13)(153) 1: (325)(341)(431)(460) 4: (500)(507) 5: (608)(633) 3: (790)(923)  


## +445

Intento insertar la calve 455 en el nodo 1, se produce overflow, entonces creo el
nodo 6 y divido la cantidad de calves en partes iguales entre el nodo 1 y el nodo 6,
y promociono la clave del medio, 431. Cuando intento insertar la clave 431 en el 
nodo 2, se produce overflow, entonces creo el nodo 7 y divido las calves en partes
iguales entre el nodo 2 y el nodo 7 y promociono la clave del medio, 490. Creo
el nodo 8 e inserto en él la clave que promocionó.

L2,L1,E1,E6,E2,E7,E8.

                                    8: (490)
                  2: 0 (320) 1 (431) 6             7: 4(555) 5(641) 3 
     0: (13)(153)  1: (325)(341)  6: (445)(460)  4: (500)(507)  5: (608)(633)  3: (790)(923)


## -490

Cambio de posición la clave 490 con la clave mas chica del nodo mas a la izquierda
de su subárbol derecho, 500, y elimino la clave 490 del nodo 4.

L8,L7,L4,E4,E8.


                                    8: (500)

                2: 0 (320) 1 (431) 6             7: 4(555) 5(641) 3 

     0: (13)(153)  1: (325)(341)  6: (445)(460)  4: (507)  5: (608)(633)  3: (790)(923)


## -507

Elimino la calve 507 del nodo 4, se produce underflow, como no tiene hermano izquierdo
redistribuyo con su hermano adyacente derecho, entonces la clave 555 pasa del nodo 7
al nodo 4, y la clave 608 pasa del nodo 5 al nodo 7.

L8,L7,L4,L5,E4,E5,E7.


                                    8: (500)

                2: 0 (320) 1 (431) 6             7: 4(608) 5(641) 3 

     0: (13)(153)  1: (325)(341)  6: (445)(460)  4: (555)  5: (633)  3: (790)(923)


## -608

Cambio de posición la clave 608 con la clave mas chica del nodo mas a la izquierda
de su subárbol derecho, 633, y elimino la clave 608 del nodo 5. Se produce underflow, 
como su hermano adyacente izquierdo no está en condiciones de redistribuír, concateno,
el nodo 4, con el nodo 5 y su padre, la clave 633 en el nodo 7, esto produce la liberación
del nodo 5.

L8,L7,L5,L4,E4,E7.


                                    8: (500)

                2: 0 (320) 1 (431) 6             7: 4(641) 3 

     0: (13)(153)  1: (325)(341)  6: (445)(460)  4: (555)(633)  3: (790)(923)

