# Punto 1

## Gramática para un bloque de manejo de excepciones en Python (EBNF)
```BNF
    G = (N,T,S,P)
    N = {<except><sentencias><sentencia><identificador><cadena><letra><digito><caracter>}
    T = {a, A, ..., z, Z, 0, ..., 9, #, $, ..., @}
    S = {<except>}
    P = {<except>::="try :"<sentencias> {"except"<identificador>":"<sentencias>}+ "else:" <sentencias>["finaly:" <sentencias>],
         <sentencias>::= {<sentencia>}+
         <sentencia>::= (for | while | if | exp_aritmetica | except),
         <identificador>::= <letra>{<cadena>}*,
         <cadena>::= (<letra> | <digito> | <caracter>),
         <letra>::= (a | A | ... | z | Z),
         <digito>::= (0 | ... | 9),
         <caracter>::= (# | % | $ | @ | ...)  
        }
```

## Diagrama de Conway