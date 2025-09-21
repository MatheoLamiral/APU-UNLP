# Ejercicio 4

La terminología "circuito corto" y "circuito largo" se refiere a cómo se evalúan las expresiones lógicas en ciertos contextos. Son técnicas utilizadas en la evaluación de expresiones booleanas que involucran operadores lógicos tipo AND y OR (no aplica a XOR).

## Circuito largo
Significa que todos los operandos se evalúan, sin importar si el primero ya determina el resultado final

## Circuito corto
Significa que los operandos de una expresión lógica se evalúan de izquierda a derecha, pero solo hasta encontrar el primero que determina el resultado final
- La conjunción da como resultado verdadero únicamente cuando ambos términos son verdaderos. Si el primer término es falso, no es necesario evaluar el segundo
- La disyunsión da como resultado falso únicamente cuando ambos términos son falsos. Si el primer término es verdadero, no es necesario evaluar el segundo 
- Permite evitar errores y optimizar el rendimiento

## Ejemplo de error en circuito largo que se soluciona en circuito corto

```C
    int x = 0;
    int division_segura(){
        return (x != 0) && (10/x);
    }
```
- Si se utilizase circuito largo, se evaluaría la condición de que x sea distinto de 0, resultaría falsa, y de todas formas evaluaría la segunda condición lo cual generaría un error al intentar realizar una division por cero
- Si se utilizase circuito corto, se evaluaría la condición de que x sea distinto de 0, resultaria falsa, y como se trata de una conjunción, al ser falso el primer término, no es necerario evaluar el segundo, por lo que evitamos el error de intentar realizar una division por cero