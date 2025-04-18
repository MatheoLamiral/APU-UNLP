# Ejercicio 5
```BNF
    G= ( N, T, S, P)
    N = {<numero_entero>, <digito> }
    T = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
    S = <numero_entero>
    P = {
    <numero_entero>::= <digito><numero_entero> | <numero_entero><digito> | <digito>
    <digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
    }

```
## Inciso a 
- N: conjunto de símbolos no terminales, `<numero_entero>  y `<dígito>` 
- T: conjunto de símbolos terminales, `{0, 1, 2, 3, 4, 5, 6, 7, 8, 0}`
- S: símbolo distinguido de la gramática que pertenece a N, `<numero_entero>`
- P: conjunto de producciones
  - `<numero_entero>::= <digito><numero_entero> | <numero_entero><digito> | <digito>`
  - `<digito>::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9`

## Inciso b
- Es ambigua ya que una sentencia puede derivarse de más de una forma ya que se está utilizando recursión por derecha e izquierda a la vez. 
- La solución correcta es:
``` BNF
    N:<numero_entero> y <dígito> 
    T: {0, 1, 2, 3, 4, 5, 6, 7, 8, 0}
    S: <numero_entero>
    P: {<numero_entero> ::= <digito> | <digito><numero_entero> 
        <digito> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
       }
```