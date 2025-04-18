# Ejercicio 10 - C

En C, una expresión es cualquier combinación válida de operandos (valores, variables o constantes) y operadores que el lenguaje puede evaluar para producir un resultado.  
El lenguaje C proporciona un manejo flexible y potente de expresiones, permitiendo realizar desde operaciones simples hasta cálculos complejos.

## Tipos de expresiones en C
1. **Expresiones aritméticas**
- Permiten realizar operaciones matemáticas usando operadores como +, -, *, /, %.
- Ejemplo: resultado = a + b * c;
2. **Expresiones relacionales**
- Comparan valores y devuelven un resultado booleano (0 o 1 en C). 
- Operadores: ==, !=, <, >, <=, >=
- Ejemplo: esMayor = (x > y);
3. **Expresiones lógicas**
- Combinan valores booleanos usando operadores lógicos: && (AND), || (OR), ! (NOT).
- Ejemplo: if (edad > 18 && tieneLicencia)
4. **Expresiones de asignacion** 
- Se usan para asignar un valor a una variable. El operador principal es =.
- Ejemplo: x = 10;
5. **Expresiones condicionales (Operador ternario)**
- Proporcionan una forma abreviada de tomar decisiones.
- Ejemplo: resultado = (a > b) ? a : b;
6. **Expresiones con operadores a nivel de bits**
- Permiten manipular los bits individuales de datos. 
- Ejemplo: x = x << 1; // Desplazamiento a la izquierda

## Precedencia y asociatividad
C define un orden de precedencia entre operadores que determina cómo se agrupan las expresiones cuando hay más de un operador. También establece una asociatividad (de izquierda a derecha oviceversa) para resolver empates de precedencia.  
Por ejemplo: En a + b * c, primero se evalúa b * c por tener mayor precedencia que +.