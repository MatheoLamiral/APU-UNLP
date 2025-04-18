# Ejercicio 10

## Gramática de una expresión numérica (EBNF)
```BNF
    G = (N, T, S, P)
    N = {<expr><digito><variable><letra>}
    T = {0,1,2,3,4,5,6,7,8,9,”+”,”-”,”*”,”/”,”,”, a, A, ..., z, Z}
    S = {<expr>}
    P = {<expr>::= [-]({<digito>}+|<variable>)(+|-|*|/) [-]({<digito>}+|<variable>),
    	 <variable>::= <letra>{(<digito>|<letra>)}*
      	 <digito>::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7  | 8 | 9,
         <letra>::= a | A | ... | z | Z}
```

## Gramática de una expresión numérica con prioridad de operadores (EBNF)

```BNF
    G = (N, T, S, P)
    N = {<expr><expr2><digito><variable><letra>}
    T = {0,1,2,3,4,5,6,7,8,9,”+”,”-”,”*”,”/”,”,”, a, A, ..., z, Z}
    S = {<expr>}
    P = {<expr>::= <expr2> (+ | -) <expr2>
         <expr2>::= [-]({<digito>}+|<variable>) (*|/) [-]({<digito>}+|<variable>),
    	 <variable>::= <letra>{(<digito>|<letra>)}*
      	 <digito>::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7  | 8 | 9,
         <letra>::= a | A | ... | z | Z}
```

Se divide a la expresion generada anteriormente en 2 niveles, el primero (`expr`) divirá en terminos en base a `+` o `-`, y el segundo nivel `expr2` son las expresiones que contengan `*` o `/`