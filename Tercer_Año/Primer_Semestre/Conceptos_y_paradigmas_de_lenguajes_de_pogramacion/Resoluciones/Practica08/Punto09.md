# Ejercicio 9

## Diferencias entre `return` y `shield`
La sentencia `return` finaliza la ejecución de una función y devuelve un valor, en cambio, la sentencia `yield` pausa la ejecución de la función y devuelve un valor temporal, pero puede continuar desde ese punto. El `return` se usa en funciones y el `yield` en generadores, útiles para manejar secuencias grandes o infinitas.

## Cuándo es útil utilizar `yield`
- Cuando se trabaja con:
- Grandes volúmenes de datos (no cargar toda la lista en memoria).
- Streams de datos infinitos.
- Procesamiento por partes (como leer archivos línea a línea).