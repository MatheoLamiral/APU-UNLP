# Punto 1

## Gramática de un switch en Java (BNF)
```BNF
    G = (N,T,S,P)
    N = {<switch>, <expresion>, <casos>, <caso>, <valor>, <bloque>, <variable>, <numero>, <comparacion>, <booleano>, <digito>, <letra>}
    T = {0, ..., 9, a, A, ..., z, Z, "true", "false", "switch", "case", "default", "break", "{", "}", "(", ")", "==", "!=", "<", "<=", ">", ">="}
    S = {<switch>}
    P = {<switch>::= "switch (" <expresion> ")" "{" <casos> "}", 
         <expresion>::= <variable> <comparacion> <numero> | <numero> <comparacion> <variable>...,
         <casos>::= <caso> | <caso><casos>,
         <caso>::= "case " <valor> ":" <bloque> "break;" ; |  "default :" <bloque> ,
         <valor>:= <numero> | <booleano>,
         <bloque>::= <for> | <while> | <if> | <switch> | ...,
         <variable>::= <letra> | <letra> <variable> | <letra> <numero>,
         <numero>::= <digito> | <digito> <numero>,
         <comparacion>::= "==" | "!=" | "<" | "<=" | ">" | ">=",
         <booleano>::= "true" | "false",
         <digito>::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9,
         <letra>::= a | A | ... | z | Z,
        }
```

## Diagrama sintáctico