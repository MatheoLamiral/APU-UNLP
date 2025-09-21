# Ejercicio 3

## Inciso a 
|Tipo de pasaje de parámetros|Lenguaje|
|----------------------------|--------|
|Por copia modo IN (por defecto). Por resultado modo OUT. IN - OUT. Para los tipos de datos primitivos indica que es por valor - resultado. Para los tipos no primitivos, y datos compuestos (arreglos, registros) se hace por referencia.|ADA|
|Por valor (si se necesita por referencia se usan punteros). Permite pasaje por valor constante, agregándole const.|C|
|Por valor. Pero al igual que Python si se pasa es un objeto “mutable” (objeto que puede ser modificado), no se hace una copia, sino que se trabaja sobre él.|Ruby|
|Solo copia de valor. Pero como las variables de tipos no primitivos son todas referencias a variables anónimas en el HEAP, el paso por valor de una de estas variables constituye en realidad un paso por referencia de la variable. La estructura de datos primitiva es un tipo de estructura de datos que almacena datos de un solo tipo. Ejemplos son los enteros, los caracteres y los flotantes, etc. La estructura de datos no primitiva es un tipo de estructura de datos que puede almacenar datos de más de un tipo. Ejemplos son los arrays, TAD, listas, etc.|JAVA|
|Envía objetos que pueden ser “inmutables” o “mutables” (objeto que pueden ser o no modificados). Si es inmutable actuará como por valor y, si es mutable, ejemplo: listas, no se hace una copia, sino que se trabaja sobre él.|Python|

## Inciso b
Ada es más seguro que Pascal respecto al pasaje de parámetros porque su sistema de tipos y reglas obligatorias permite detectar y evitar errores en tiempo de compilación, mientras que Pascal deja más libertad (y, por tanto, más margen para errores).

## Inciso c
En cuanto a IN - OUT, para los tipos de datos primitivos indica que es por valor - resultado, y para los tipos no primitivos, y datos compuestos (arreglos, registros) se hace por referencia.