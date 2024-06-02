Árbol B+, orden 4, max 3, min 1.
Política de resolusión de underflows derecha.

## Inicial

                                2:0(241)1(422)3(540)4
            0:(3)(35)(67)1  1:(241)(300)(329)3  3:(422)(445)4   4:(556)(600)-1

## +100

Intento insertar la clave 100 en el nodo 0, se porduce underflow, entonces creo el nodo 5
y divido las claves en partes iguales entre el nodo 0 y el nodo 5, y promociono una copia 
de la clave del medio (como son pares sube una copia de la del medio a la derecha). Cuando 
intento insertar la copia de la clave 67 en el nodo 2, se produce overflow, entonces creo el
nodo 6 y divido la canitdad de claves entre el nodo 2 y el nodo 6 y promociono la clave del 
medio (como son pares sube la del medio de la derecha), creo el nodo 7 e inserto en él la 
clave que promocionó, 442.

L2,L0,E0,E5,E2,E6,E7.

                                            7:2(442)6
                        2:0(67)5(241)1                          6:3(540)4
            0:(3)(35)5  5:(67)(100)1  1:(241)(300)(329)3  3:(422)(445)4   4:(556)(600)-1


## -540

El árbol se mantiene igual, ya que se busca la calve 540, pero como no está en una hoja, no 
forma realmente parte del árbol, por ende no hay calve que eliminar.

L7,L6,L4.

                                            7:2(442)6
                        2:0(67)5(241)1                          6:3(540)4
            0:(3)(35)5  5:(67)(100)1  1:(241)(300)(329)3  3:(422)(445)4   4:(556)(600)-1


## -67

Elimino la clave 67 del nodo 5 sin problemas.

L7,L2,L5,E5.

                                            7:2(442)6
                        2:0(67)5(241)1                          6:3(540)4
            0:(3)(35)5  5:(100)1  1:(241)(300)(329)3  3:(422)(445)4   4:(556)(600)-1


## -442

Elimino la clave 442 del nodo 3 sin problemas.

L7,L6,L3,E3.

                                            7:2(442)6
                        2:0(67)5(241)1                          6:3(540)4
            0:(3)(35)5  5:(100)1  1:(241)(300)(329)3        3:(445)4   4:(556)(600)-1


## -445

Elimino la clave 445 del nodo 3, se produce underflow, entonces redistribuyo con su hermano 
derecho, el nodo 4.

L7,L6,L3,L4,E3,E4,E6.

                                            7:2(442)6
                        2:0(67)5(241)1                          6:3(556)4
            0:(3)(35)5  5:(100)1  1:(241)(300)(329)3        3:(540)4   4:(556)(600)-1


## -556

Elimino la clave 556 del nodo 4 sin problemas.

L7,L6,L4,E4.

                                            7:2(442)6
                        2:0(67)5(241)1                          6:3(556)4
            0:(3)(35)5  5:(100)1  1:(241)(300)(329)3        3:(540)4   4:(600)-1
