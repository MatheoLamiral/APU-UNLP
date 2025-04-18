# Ejercicio 6

## Gramática para la definición de una palabra cualquiera
```BNF
    G = (N, T, S, P)
    N = {<letra><palabra>}
    T = {"a", ..., "z","A", ..., "Z"}
    S = {<palabra>}
    P = {<palabra> ::= <mayuscula> | <minuscula> | <mayuscula><palabra> | <minuscula><palabra>,
         <minuscula> ::= "a" | ... | "z",
         <mayuscula> ::= "A" | ... | "Z",
        }
```
