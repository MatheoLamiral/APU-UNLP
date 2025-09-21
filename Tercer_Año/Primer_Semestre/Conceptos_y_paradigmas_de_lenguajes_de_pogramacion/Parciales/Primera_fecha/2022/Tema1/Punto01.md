# Punto 1

## Gramática para un bloque de manejo de excepciones en Java (EBNF)
```BNF
    G = (N,T,S,P)
    N = {<except><sentencias><sentencia><identificador><cadena><letra><digito><caracter><for><while><if><switch><exp_aritmetica>}
    T = {a, A, ..., z, Z, 0, ..., 9, #, $, ..., @, "try", "catch", "finally", "{", "}", "(", ")", ";", ",", "Exception"}
    S = {<except>}
    P = {<except>::="try {"<sentencias>"}" {"catch""("<identificador><identificador>")" "{"<sentencias>"}"}+    ["finaly {" <sentencias> "}"],
         <sentencias>::= {<sentencia>}+
         <sentencia>::= (<for> | <while> | <if> | <switch> | <exp_aritmetica> | <except>),
         <identificador>::= <letra>{<cadena>}*,
         <cadena>::= (<letra> | <digito> | <caracter>),
         <letra>::= (a | A | ... | z | Z),
         <digito>::= (0 | ... | 9),
         <caracter>::= (# | % | $ | @ | ...)  
        }
```
## Diagrama de Conway
