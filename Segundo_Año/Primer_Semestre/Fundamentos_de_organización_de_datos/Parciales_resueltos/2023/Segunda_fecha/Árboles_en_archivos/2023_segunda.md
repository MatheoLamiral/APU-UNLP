Árbol B+, orden 4, max 3, min 1.
Política de resolución de underflows derecha.

## Inicial

                            4:0(240)1(403)2(500)3
        0:(3)(45)(60)1  1:(240)(255)(360)2  2:(409)(420)3   3:(550)-1


## +58

Intento insertar la clave 58 en el nodo 0, se produce overflow, entonces creo el nodo 5,
y divido las claves en partes iguales entre el nodo 0 y el nodo 1 y promociono una copia
de la clave del medio (como son pares sube la copia de la del medio a la derecha), 58. 
Cuando intento insertar la copia del 58 en el nodo 4, se produce overflow, entonces creo
el nodo 6 y repito el mismo proceso que cuando se produjo overflow en el nodo 0, solo que 
ahora no promociono una copia, porque no estoy trabajando con una hoja, creo el nodo 7, e 
inserto en él la clave qu promocionó, 403.

L4,L0,E0,E5,E4,E6,E7.

                                            7:4(403)6
                    4:0(58)5(240)1                            6:2(500)3
        0:(3)(45)5   5:(58)(60)1  1:(240)(255)(360)2  2:(409)(420)3   3:(550)-1


## -430

Se mantiene igual el árbol, ya que se buscara la clave 403, pero como no esta en una 
hoja del árbol, no perteneve realmente al archivo, entonces no hay clave que eliminar.

L7,L6,L2.

                                            7:4(403)6
                    4:0(58)5(240)1                            6:2(500)3
        0:(3)(45)5   5:(58)(60)1  1:(240)(255)(360)2  2:(409)(420)3   3:(550)-1


## +260

Intento insertar la clave 260 en el nodo 1, se produce overflow, entonces creo el nodo 8,
y divido las claves en partes iguales entre el nodo 1 y el nodo 8 y promociono una copia 
de la clave del medio (como son pares sube la copia de la del medio a la derecha), 260, 
que se inserta sin problemas en el nodo 4.

L7,L4,L1,E1,E8,E4.

                                                    7:4(403)6
                        4:0(58)5(240)1(260)8                            6:2(500)3
        0:(3)(45)5   5:(58)(60)1  1:(240)(255)8  8:(260)(360)2  2:(409)(420)3   3:(550)-1


## -550

Elimino la clave 550 del nodo 3, se produce underflow, como no tiene hermano derecho
reditribuyo con su hermano izquierdo.

L7,L6,L3,L2,E2,E3,E6.

                                                    7:4(403)6
                        4:0(58)5(240)1(260)8                            6:2(420)3
        0:(3)(45)5   5:(58)(60)1  1:(240)(255)8  8:(260)(360)2      2:(409)3   3:(420)(500)-1