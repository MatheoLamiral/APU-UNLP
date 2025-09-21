# Punto 1

## Gramática para la definición de un registro en Pascal (EBNF)
```BNF
    G = (N,T,S,P)
    N = {<record>, <campo>, <tipo>, <identificador>, <primitivo>, <letra>, <digito>, <caracter>}
    T = {a, A, ..., z, Z, 0, ..., 9, #, $, ..., @}
    S = {<record>}
    P = {<record>::= "record " <identificador> "of " {<campo>}+ "end",
         <campo>::= <identificador> ":" <tipo>,
         <tipo>::= (<identificador> | <primitivo>)
         <identificador>::= <letra> {<letra>|<digito>|<caracter>}*
         <primitivo>::= ("Integer" | "Boolean" | "Real" | "Char"),
         <letra>::= (a | A | ... | z | Z),
         <digito>::= (0 | ... | 9),
         <caracter>::= (# | % | $ | @ | ...)
        }
```