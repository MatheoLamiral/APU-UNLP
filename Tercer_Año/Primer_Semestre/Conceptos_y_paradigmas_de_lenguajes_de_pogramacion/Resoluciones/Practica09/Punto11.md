# Ejericio 11

- El mecanismo de excepciones en JavaScript se basa en el uso de las instrucciones `try`, `catch`, `finally` y `throw` .
  - `try`: Define un bloque de código para probar (donde pueden ocurrir excepciones).
  - `catch`: Define un bloque de código para manejar cualquier excepción lanzada en el bloque try.
  - `finally`: Define un bloque de código que se ejecuta siempre, ocurra o no una excepción.
  - `throw`: Permite lanzar manualmente una excepción.

- Ejemplo:
```JavaScript
try {
    //Código que puede lanzar una excepción
} catch (error) {
    console.error(error.message);
} finally {
    console.log("Esto se ejecuta siempre.");
}
```
- Comportamiento:
- Si ocurre una excepción en el bloque `try`, se transfiere el control al bloque `catch`. El bloque `finally` se ejecuta siempre, haya o no excepción.