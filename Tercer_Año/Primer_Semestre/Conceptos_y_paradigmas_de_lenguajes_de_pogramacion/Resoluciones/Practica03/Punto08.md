# Ejercicio 8

## Null
Representa intencionalmente la ausencia de valor. Es un valor asignado manualmente para indicar “ningún valor” o “vacío”.  

## Undefined
Significa que una variable fue declarada, pero todavía no tiene un valor asignado. Variables no inicializadas. Parámetros no pasados a una función. El valor que retorna una función que no tiene return.  

## Direfencia entre Null y Undefined
| Característica | undefined | null |
| --- | --- | --- |
| ¿Quién lo asigna? | Lo asigna el motor de  JavaScript automáticamente | Lo asigna el programador manualmente |
| ¿Qué representa? | Una variable declarada pero sin valor | Una variable con valor vacío intencional |
| Tipo (typeof) | "undefined" | "object" (aunque no es realmente un objeto) |
| Comparación ==  → null == undefined → true |  |
| Comparación ===  → null === undefined → false |  |