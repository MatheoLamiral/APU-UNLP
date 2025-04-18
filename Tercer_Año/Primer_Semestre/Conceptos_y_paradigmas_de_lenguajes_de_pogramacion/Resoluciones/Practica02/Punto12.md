# Ejercicio 12

## Gramática para la definición de un tag div en HTML 5 (EBNF)
```BNF
    G = (N, T, S, P) 
    N = { <div>, <inner_html>, <etiqueta>, <etiqueta_cierre>, <palabra>}
    T = {a, A, …, z, Z, <, >, /}
    S = {<div>}
    P = { <div>::= "<div" [<atributos>] ">" {<contenido>}* "</div>",
          <atributos>::= <atributo> {" " <atributo>}*,
          <artibuto>::= <cadena> "=" '"'<cadena>'"',
          <cadena>::= {(<letra> | <digito> | <caracter>)}+,
          <contenido>::= (<elemento_html> | <cadena>),
          <elemento_html>::= "<"<cadena> ">" {<contenido>}* "</" <cadena> ">",
          <letra>::= "a" | "A" | ... | "z" | "Z", 
          <digito>::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9,
          <caracter>::= "&" | "@" | ... | "$",
        }
```
