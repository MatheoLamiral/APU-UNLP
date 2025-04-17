# Ejercicio 15
## `Const` 
Declara una variable cuyo valor no puede ser reasignado después de su inicialización. Tiene un alcance de bloque, es decir, está limitada al bloque donde es definida
## `Let`
Permite reasignar el valor a la variable, pero no puede ser redeclarada en el mismo ámbito. Tiene alcance de bloque 
## `Var`
Permite tanto reasignar como redeclarar el valor de la variable dentro del mismo ámbito. Su alcance está limitado a la función que la define o global si se define fuera de cualquier función
## Sin declaración explícita
Se la considera global, lo cual puede causar problemas si no se tiene cuidado 
## Diferencia entre `Const`, `Let` y `Var`
La diferencia entre “const”, “let” y “var” reside entonces, en la reasignación y redeclaración de las variables, además del alcance de estas. 
## Comparación con Python
No existe un equivalente a const, las variables son mutables por defecto, para trabajar con constantes se utilizan convenciones (como escribir la variable en mayúsculas), pero la variable no es en sí una constante. Todas las variables tienen un alcance local o global dependiendo de dónde se definan, no existen palabras clave para determinar el alcance de una variable. Las variables pueden reasignarse y redeclararse sin ningún problema
