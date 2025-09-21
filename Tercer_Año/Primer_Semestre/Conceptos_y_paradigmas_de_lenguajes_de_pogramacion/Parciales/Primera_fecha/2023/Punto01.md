# Punto 1

## Gramática de una URL (EBNF)
```BNF
    G = (N,T,S,P)
    N = {<url><protocolo><servicio><nombre_de_dominio><ruta_en_el_servidor><pagina><palabra><cadena><letra><digito><caracter>} 
    T = {a,A,...,z,Z,0,1,...,9,"-","_","~", ..., "=", "://", ".", "/", "www.", "http", "https"}
    S = {<url>}
    P = {<url>::= <protocolo> "://" <servicio> "." <nombre_de_dominio> {"/" <ruta_en_el_servidor> "/"}+ <pagina> "/",
         <protocolo>::= ("http" | "https"),
         <servicio>::= [("www" | "ftp" | "mail")],
         <nombre_de_dominio>::= <cadena>,
         <ruta_en_el_servidor>::= <palabra>,
         <pagina>::= <cadena>,
         <palabra> ::= {letra}+,
         <cadena> ::= {(letra | digito | caracter)}+,
         <letra>::= a | A | ... | z | Z,
         <digito>::= 0 | 1 | ... | 9,
         <caracter>::= "-" | "_" | "." | "~" | ... | "=" ,
        }
```

## Árbol de derivación de https://www.lanacion.com.ar/cultura/feria-del-libro-nid26042023/