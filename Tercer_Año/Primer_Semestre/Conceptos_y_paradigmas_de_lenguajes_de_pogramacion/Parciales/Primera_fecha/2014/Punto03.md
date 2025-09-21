# Punto 3

## Gramática para la definición de una función en Python
```BNF
    G = (N, T, P, S)
    N = {<funcion>, <parametros>, <parametro>, <sentencias>, <identificador>, <letra>, <digito>, <caracter>}
    T = { "def", "(", ")", ":", ",", "if", "while", "for", "expr_aritmetica", 0, ..., 9, "a", "A", ..., "z", "Z", "$", "@", ..., "&"}
    S = {<funcion>}
    P = {<funcion>::= "def " <identificador> "(" <parametros> ")" ":" <sentencias>,
         <parametros>::= <parametro> {"," <parametro>}*,
         <parametro>::= <identificador>
         <sentencias>::= {("if" | "while" | "for" | "expr_aritmetica" | ...)}*,
         <identificador>::= <letra> {(<letra> | <digito> | <caracter>)}*,
         <letra>::= a | A | ... | z | Z,
         <digito>::= 0 | 1 | ... | 9,
         <caracter>::= $ | @ | ... | &,
        }
```