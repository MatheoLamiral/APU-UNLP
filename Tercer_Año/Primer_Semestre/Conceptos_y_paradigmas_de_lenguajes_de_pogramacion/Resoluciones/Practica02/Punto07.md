# Ejercicio 7

## EBNF
```BNF
    G = (N, T, S, P)
    N = {<numero_real><digito>}
    T = {0,1,2,3,4,5,6,7,8,9,"-",","}
    S = {<numero_real>}
    P = {<numero_real> ::= ["-"]{<numero_entero>} + [","{<numero_entero>}+],
         <numero_entero> ::= {<digito>}+   
      	 <digito>::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
        }
```

## BNF
```BNF
    G = (N, T, S, P)
    N = {<numero_real><numero_entero><digito>}
    T = {0,1,2,3,4,5,6,7,8,9, "-", ","}
    S = {<numero_real>}
    P = {<numero_real> ::= <numero_entero> | "-" <numero_entero> | <numero_entero> "," <numero_entero> | "-" <numero_entero> "," <numero_entero>,
         <numero_entero> ::= <digito> | <digito><numero_entero>,
      	 <digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
        }

```
## Diferencias en la utilización de EBNF y BNF
- En BNF no se permiten repeticiones (`{}`), implementamos las mismas con recursión
- En BNF no se permiten opcionales (`[]`)
- La expresividad en BNF es limitada, mientras que en EBNF es mas concisa y legible
- BNF en general requiere más reglas auxiliares de las que requiere EBNF