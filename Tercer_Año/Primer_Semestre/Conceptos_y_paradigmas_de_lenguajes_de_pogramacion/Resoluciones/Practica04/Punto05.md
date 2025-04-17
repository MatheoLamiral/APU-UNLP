# Ejercicio 5
## Inciso a 
La clasificación se basa en cómo se comportan las constantes respecto al tipo y al momento en que se les liga (asocia) su valor y tipo. Esto afecta la flexibilidad, el tipo de inferencia y el momento en que el compilador resuelve los valores.
- Las **constantes numéricas** son valores literales numéricos sin tipo explícito al momento de su declaración. El compilador difiere el momento de decidir su tipo hasta que sea necesario (cuando se utiliza). Se pueden usar en contextos de distintos tipos numéricos porque se adaptan al tipo requerido. Su valor se asigna en ejecución (ej: t constant := 10)
- Las **constantes comunes** son constantes con tipo explícito declarado. El tipo está ligado en el momento de la declaración, y no puede adaptarse automáticamente como una constante numérica. Más rígidas, pero también más seguras y predecibles. El valor se asigna en compilación (ej: t constant integer := 10)

## Inciso b

- `H: constant Float:= 3,5;`: La ligadura se da en compilación ya que es una constante común
- `I: constant:= 2;`: La ligadura se da en ejecución ya que es una constante numérica
- `K: constant float:= H*I;`: La ligadura se da en compilación ya que es una constante común
