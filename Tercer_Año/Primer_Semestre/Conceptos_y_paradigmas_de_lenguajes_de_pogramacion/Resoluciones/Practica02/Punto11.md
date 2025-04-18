# Ejercicio 11

```BNF
    N= {<sentencia_for>, <bloque>, <variable>, <letra>, <cadena>, <digito>, <otro>, <operacion>,
    <llamada_a_funcion>, <numero>, <sentencia> }
    P= { <sentencia_for>::= for (i= IN 1..10) loop <bloque> end loop;
         <variable>::= <letra> | <cadena>
         <cadena>::= { ( <letra> | <digito> | <otro> ) }+
         <letra>::=( a | .. | z | A | .. | Z )
         <digito>::= ( 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 0 )
         <bloque>::= <sentencia> | <sentencia> <bloque> | <bloque> <sentencia> ;
         <sentencia>::= <sentencia_asignacion> | <llamada_a_funcion> | <sentencia_if> |
         <sentencia_for> | <sentencia_while> | <sentencia_switch> }
```
## Errores en la gramática anterior
- La gramática esta incompleta, falta el conjunto de los símbolos terminales (T) y el símbolo distinguido de la gramática (S)
- Mezcla el uso de BNF y EBNF
- `<sentencia_for>` está mal hecho ya que limita el bucle a 10 repeticiones en lugar de generalizarlo
- La gramática es ambigua ya que `<bloque>` usa tanto recursión por derecha como por izquierda, permitiendo que pueda derivarse de mas de una forma 
- En N no están definidos `<sentencia_asignacion>`, `<sentencia_if>`, `<sentencia_while>` y `<sentencia_switch>`. Y tampoco están desarrollados en P 
- En P no están desarrollados `<otro>`, `<operación>` y `<numero>`

