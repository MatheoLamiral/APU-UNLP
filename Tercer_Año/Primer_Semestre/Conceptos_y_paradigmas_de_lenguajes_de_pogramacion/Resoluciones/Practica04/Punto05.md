# Ejercicio 5
## Inciso a 
La clasificación se basa en cómo se comportan las constantes respecto al tipo y al momento en que se les liga (asocia) su valor y tipo. Esto afecta la flexibilidad, el tipo de inferencia y el momento en que el compilador resuelve los valores.
- Las **constantes numéricas** son valores literales numéricos sin tipo explícito al momento de su declaración. Su valor se asigna en compilación (ej: t constant := 10)
- Las **constantes comunes** son constantes con tipo explícito declarado. El valor se asigna en ejecución (ej: t constant integer := 10)

## Inciso b

- `H: constant Float:= 3,5;`: La ligadura se da en ejecución ya que es una constante común
- `I: constant:= 2;`: La ligadura se da en compilación ya que es una constante numérica
- `K: constant float:= H*I;`: La ligadura se da en ejecución ya que es una constante común
