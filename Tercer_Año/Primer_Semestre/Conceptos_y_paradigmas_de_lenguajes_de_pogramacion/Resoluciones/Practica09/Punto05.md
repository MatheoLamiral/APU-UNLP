# Ejercicio 5

## Comparación de propagación de errores en Java, Python y PL/1

Java posee propagación dinámica, es decir, que una vez lanzadas las excepciones, en caso de no ser tratadas por la unidad que las generó, se propagan dinámicamente. En Python, en primer lugar, cuándo la excepcion ocurre dentro de un bloque `try`, busca inmediatamente el manejador en los excepts siguientes, en caso de no encontrar el manejador, lo propaga dinámicamente. En PL/1, en cambio, a medida que se va ejecutando el proceso y se encuentran `ON CONDITION` se apila el manejador en una pila de manejadores, cuando se levanta una excepción se busca el nombre de la misma desde el tope de la pila hacia abajo, el primer manejador que se encuentra es el que se ejecuta

## Propagación de errores en JavaScript
- Similar a la propagación dinámica de Java
- Se lanza una excepción con throw.
- Se busca un bloque `try` y `catch` que pueda manejarla
- Si no hay un `try` y `catch` en ese contexto, se propaga al contexto superior (la función que llamó a la actual).
- Este proceso continúa hasta llegar al contexto global.
- Si no se captura en ningún punto, la excepción se considera no manejada y genera un error en tiempo de ejecución.