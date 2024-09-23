Árbol B, orden 5, max 4, min 1.
Política de resolución de underflows izquierda.

## Inicial

                           2: 0(10)3(30)1(45)5(60)4
            0:(1) 3:(20)(25) 1:(35)(40) 5:(50)(55) 4:(70)(80)(90)(100)

## -1

Elimino la clave 1 del nodo 0, se produce underflow, como no tiene hermano izquierdo 
redistribuyo con su hermano derecho.

L2,L0,L3,E0,E3,E2.

                           2: 0(20)3(30)1(45)5(60)4
            0:(10) 3:(25) 1:(35)(40) 5:(50)(55) 4:(70)(80)(90)(100)

## +120

Intento insertar la clave 120 en el nodo 4, se produce overflow, entonces creo el nodo
6 y divido las claves en partes iguales entre el nodo 4 y el nodo 6, y promociono la clave 
del medio 90. Cuando intento insertar la calve 90 en el nodo 2, se produce overflow, entonces
creo el nodo 7 y divido las calves en partes iguales entre le nodo 2 y el nodo 7, y promociono 
la clave del medio, 45. Creo el nodo 8 e inserto en el la calve que promocionó.

L2,L4,E4,E6,E2,E7,E8.

                                      8:2(45)7
                    2: 0(20)3(30)1                   7:5(60)4(90)6
                0:(10) 3:(25) 1:(35)(40)    5:(50)(55) 4:(70)(80) 6:(100)(120)

## -10

Elimino la clave 10 del nodo 0, se produce underflow, como no tiene hermano izquierdo y su hermano
derecho no está en condiciones de redistribuir, concateno. Se libera el nodo 3.

L8,L2,L0,L3,E0,E2.

                                      8:2(45)7
                    2: 0(30)1                   7:5(60)4(90)6
                0:(20)(25) 1:(35)(40)    5:(50)(55) 4:(70)(80) 6:(100)(120)


## -70

Elimino la clave 70 del nodo 4 sin problemas.

L8,L7,L4,E4.

                                      8:2(45)7
                    2: 0(30)1                   7:5(60)4(90)6
                0:(20)(25) 1:(35)(40)    5:(50)(55) 4:(80) 6:(100)(120)


## -80

Elimino la clave 80 del nodo 4, se produce underflow, entonces redistribuyo con su hermano izquierdo.

L8,L7,L4,L5,E5,E4,E7.

                                      8:2(45)7
                    2: 0(30)1                   7:5(55)4(90)6
                0:(20)(25) 1:(35)(40)      5:(50) 4:(60) 6:(100)(120)
