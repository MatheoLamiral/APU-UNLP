Árbol B+, orden 6, max 5, min 2.
Política de resolusión de underflows derecha.

## Inicial

                                      2:0(10)1(60)3(115)4(145)5(179)6
      0:(1)(5)1 1(34)(44)3 3:(60)(113)4 4:(120)(125)(131)(139)5 5:(145)(153)(158)(160)(177)6 6:(179)(190)-1


## +159

Intento insertar la clave 159 en el nodo 5, se produce overflow, entonces creo el nodo 7 y divido las claves en partes iguales entre el nodo 5 y el nodo 7 y promociono una copia de la clave del medio (como son pares sube una copia de la del medio a la derecha), cuando intento insertar la clave que promocionó, 159 en el nodo 2, se produce overflow, entonces creo el nodo 8 y divido las calves en partes iguales entre el nodo 2 y el nodo 8, y promociono la clave del medio (como son pares sube la del medio a la derecha), creo el nodo 9 e inserto en él la clave que promocionó, 145.

L2,L5,E5,E7,E2,E8,E9.

                                                9:2(145)8
                    2:0(10)1(60)3(115)4                                 8:5(159)7(179)6
     0:(1)(5)1 1(34)(44)3 3:(60)(113)4 4:(120)(125)(131)(139)5 5:(145)(153)(158)7 7:(159)(160)(177)6 6:(179)(190)-1

## -5

Elimino la clave 5 del nodo 0, se produce underflow, como su hermano derecho no esta en condiciones de redistribuir, concateno. Se libera el nodo 1.

L9,L2,L0,L1,E1,E2.

                                                9:2(145)8
                    2:0(60)3(115)4                                 8:5(159)7(179)6
     0:(1)(10)(34)(44)3 3:(60)(113)4 4:(120)(125)(131)(139)5 5:(145)(153)(158)7 7:(159)(160)(177)6 6:(179)(190)-1


## -190

Elimino la clave 190 del nodo 6, se produce underflow, como no tiene hermano derecho redistribuyo con su hermano izquierdo. Por último actualizo el separador del nodo 8.

L9,L8,L6,L7,E7,E6,E8.

                                                9:2(145)8
                    2:0(60)3(115)4                                 8:5(159)7(177)6
     0:(1)(10)(34)(44)3 3:(60)(113)4 4:(120)(125)(131)(139)5 5:(145)(153)(158)7 7:(159)(160)6 6:(177)(179)-1

