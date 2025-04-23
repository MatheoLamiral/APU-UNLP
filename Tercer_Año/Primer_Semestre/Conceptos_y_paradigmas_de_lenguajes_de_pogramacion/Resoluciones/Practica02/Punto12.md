# Ejercicio 12

## Gramática para la definición de un tag div en HTML 5 (EBNF)
```BNF
    G = (N, T, S, P) 
    N = { <div>,<atributos>,<atributo>,<nombre>,<valor>,<contenido>,<elemento_html>,<letra>,<digito>,<caracter>}
    T = {a, A, …, z, Z, 0, ..., 9, "&", "@", ..., "$"}
    S = {<div>}
    P = { <div>::= "<div" [<atributos>] ">" {<contenido>}* "</div>",
          <atributos>::= <atributo> {" " <atributo>}*,
          <artibuto>::= <nombre> "=" '"'<valor>'"',
          <nombre>::= {(letra | caracter)}+ {(<letra> | <digito> | <caracter>)}*
          <valor>::= {(<letra> | <digito> | <caracter>)}+,
          <contenido>::= (<elemento_html> | <cadena>),
          <elemento_html>::= "<"<cadena> ">" {<contenido>}* "</" <cadena> ">",
          <letra>::= "a" | "A" | ... | "z" | "Z", 
          <digito>::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9,
          <caracter>::= "&" | "@" | ... | "$",
        }
```
