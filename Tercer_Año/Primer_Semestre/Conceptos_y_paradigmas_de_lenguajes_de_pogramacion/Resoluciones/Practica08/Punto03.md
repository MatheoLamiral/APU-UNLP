# Ejercicio 3

Una expresión de asignación puede producir efectos laterales que afecten el resultado final, especialmente si la evaluación de expresiones modifica variables involucradas en la misma expresión. Esto depende del orden en que se evalúan los operandos, lo cual depende del lenguaje
```C
    int i = 1;
    int a = i + (i = 2);
```
- Si el compilador evalúa primero i, obtiene 1, y luego evalúa (i = 2), el resultado será: 3
- Si se evalúa primero (i = 2) y luego i, el resultado sera: 4
```C
    int i = 1;
    int a  = i + fun();

    int fun(){
        i = i*2;
    }
```
- Si el compilador evalúa primero i, obtiene 1, y luego ejecuta fun(), el resultado será: 3
- Si ejecuta primero fun() y luego evalua i, el resultado sera: 4