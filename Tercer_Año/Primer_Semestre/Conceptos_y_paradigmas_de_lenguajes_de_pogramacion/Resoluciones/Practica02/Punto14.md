# Ejercicio 14

## Gramática para la definición de métodos en Java (EBNF)
```BNF
    G = (N, T, S, P)
    N = {<metodo>,<visibilidad>,<tipo>,<primitivo>,<identificador>,<nombre>,<parametros>,<bloque>,<instrucciones>,<mayuscula>,<minuscula>,<digito>,<for>,<while>,<if>,<asignacion>}
    T = {"a", ...,"z", "A", ..., "Z", "public", "private", "protected", "static", "int", "double", "boolean", "void", "char", "(", ")", ",", "{", "}", 1, 2, 3, 4, 5, 6, 7, 8, 9}
    S = {<metodo>}
    p = {<metodo>::= <visibilidad> <tipo> <nombre> "(" [<parametros>] ")" <bloque>,
         <visibilidad>::= ("public" | "private" | "protected" | "static"),
         <tipo>::= (<primitivo> | <identificador>),
         <primitivo>::= ("int"| "double" | "boolean" | "void" | "char"),
         <identificador>::= <mayuscula> {(<minuscula> | <mayuscula> | <digito>)}*,
         <nombre>::= <minuscula> {(<minuscula> | <mayuscula> | <digito>)}*,
         <parametros>::= <tipo><nombre> {"," <tipo><nombre>}*,
         <bloque>::= "{" <instrucciones> "}",
         <instrucciones>::= {(<for> | <while> | <if> | <asignacion> | ...)}*,
         <mayuscula>::= "a" | ... | "z",
         <minuscula>::= "A" | ... | "Z",
         <digito>::= 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9,
        } 

```