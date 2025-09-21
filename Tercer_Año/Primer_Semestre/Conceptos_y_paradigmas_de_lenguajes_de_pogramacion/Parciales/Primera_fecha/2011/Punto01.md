# Punto 1

## Gramática del for de Java (EBNF)
```BNF
    G = (N, T, P, S)
    N = {<for>, <valor_inicial>, <condicion>, <factor_incremento_valor_inicial>, <bloque>, <sentencia>, <tipo_dato>, <operador_condicional>, <expresion>,<auto_incremento>, <incremento_con_otro_valor>, <identificador>, <valor>, <numero>, <cadena>, <booleano>, <letra>, <digito>, <caracter>}
    T = {"for", "(", ")", "{", "}", ";", "=", "<", ">", "<=", ">=", "==", "!=", "+", "-", "*", "/", "++", "--", "true", "false", '"', "@", "$", ..., "&", "a", "A", ... , "z", "Z", "0", "1", ... , "9"} 
    S = {<for>}
    P = {<for> ::= "for (" <valor_inicial> ";" <condicion> ";" <factor_incremento_valor_inicial> ")" "{"<bloque>"}"
        <valor_inicial> ::= [<tipo_dato>] <identificador> "=" <expresion>,
        <condicion> ::= <expresion> <operador_condicional> <expresion>,
        <factor_incremento_valor_inicial> ::= <identificador> (<incremento_con_otro_valor> | <auto_incremento>),
        <bloque> ::= {<sentencia>}*,
        <sentencia> ::= (<for> | <if> | <while> | <expr_aritmetica> | <switch> | <asignación> | ...),
        <tipo_dato> ::= ("int" | "float" | "double" | "char" | "boolean"),
        <operador_condicional> ::= ("==" | "!=" | "<" | "<=" | ">" | ">="),
        <expresion> ::= (<identificador> | <valor>)	 
        <valor> ::= (<numero> | <cadena> | <booleano>),
        <auto_incremento> ::= ("++" | "--"),
        <incremento_con_otro_valor> ::= ("+" | "-" | "*" | "/") <expresion>,
        <identificador> ::= <letra> {(<letra> | <digito>)}*,
        <numero> ::= <digito> {<digito>}*,
        <cadena> ::= '"' {(<caracter>|<letra>)}* '"',
        <booleano> ::= ("true" | "false"),
        <letra> ::= (a | A | ... | z | Z),
        <digito> ::= (0 | 1 | ... | 9),
        <caracter> ::= (@ | $ | ... | &),
        }
```