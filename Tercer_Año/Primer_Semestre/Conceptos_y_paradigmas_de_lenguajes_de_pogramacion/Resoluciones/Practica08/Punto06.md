# Ejercicio 6

## Selección multiple en C
- Constructor `Switch (expresión)`
- Cada rama `Case` es "etiquetada" por uno o más valores constantes (enteros o char)
- Si coincide con una etiqueta se ejecutan las sentencias asociadas, y continúa con las sentencias de las otras entradas. (chequea todas salvo exista un `break`)
- La sentencia `break` (opcional) provoca la salida de cada rama
- La sentencia `default` (opcional) para los casos en que el valor no coincida con ninguna de las opciones establecidas
- El orden en que aparecen las ramas no tiene importancia

## Es igual a ADA, Pascal o Python?
- En cuanto a pascal, la sentencia break no es obligatoria, entrará en una opcion y luego finalizara el case, y puede ser inseguro ya que no es obligatoria la sentencia else, que toma el caso en que no haya entrado en ninguna de las opciones anteriores. Además, puede definirse un rango de valores en cada caso
    ```Pascal
        case x of
            1: writeln('Uno');
            2..4: writeln('Entre dos y cuatro');
        else // es opcional 
            writeln('Valor no reconocido');
        end;
    ```
- En cuanto a ADA es obligatoria la sentencia when others, que toma los casos en que no haya entrado en ninguna de las opciones anteriores, pero no es obligatoria la sentencia break, entrará en una opcion y luego finalizara el case. Además usar un rango de valores para cada caso, que debe estar definido previamente
    ```ADA
        case D is
        when Lunes =>
            Put_Line("Es lunes");
        when Martes =>
            Put_Line("Es martes");
        when others => -- es obligatorio
            Put_Line("Otro día");
        end case;
    ```
- En cuanto a Python (match), la opcion de case _ no es obligatoria, que es la opcion que se encarga del caso en que no haya entrado en niguna de las opciones anteriores, el programa continuara su ejecución sin ejecutar ninguana de las opciones del match, y en la sentencia case se puede definir varios valores para una misma opción. Antes de que existiese el match, se simulaba el case con la estructura de if, elif, else.
    ```Python
        match x:
            case 1 | 2 | 3:
                print("Uno, dos o tres")
            case 4 | 5 | 6:
                print("Cuatro, cinco o seis")
            case 7 | 8 | 9:
                print("Siete, ocho o nueve")
            case 10:
                print("Diez")
            case _: ## es opcional
                print("no es un numero entre uno y diez")
    ```