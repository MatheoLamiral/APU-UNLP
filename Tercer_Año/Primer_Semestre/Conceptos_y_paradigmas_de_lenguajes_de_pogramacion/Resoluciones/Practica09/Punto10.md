# Ejercicio 10

# Manejo de excepciones en Ruby
- Ruby implementa El modelo de excepciones de terminación. Cuando ocurre una excepción, el flujo de ejecución abandona el bloque donde se produjo el error y transfiere el control al bloque `rescue` correspondiente. Es un modelo de excepciones basado en clases, similar al de Java. Las excepciones en Ruby son objetos que heredan de la clase Exception.
- Las instrucciones específicas para el manejo de excepciones en Ruby son:
  - `begin` ... `rescue` ... `end`: Permite capturar excepciones que ocurren dentro del bloque begin. El bloque rescue maneja la excepción.
  - `rescue`: Captura la excepción. Puede especificar el tipo de excepción a capturar.
  - `else`: Se ejecuta si no se produjo ninguna excepción.    
  - `ensure`: Se ejecuta siempre, ocurra o no una excepción (similar a `finally` en otros lenguajes).
  - `raise`: Lanza una excepción manualmente.
- Ejemplo:
```Ruby
    begin
    # Código que puede lanzar una excepción
    rescue ZeroDivisionError
    puts "No se puede dividir por cero"
    rescue => e
    puts "Ocurrió un error: #{e.message}"
    else
    puts "No hubo errores"
    ensure
    puts "Esto se ejecuta siempre"
    end
```
- Comportamiento:
  - Si ocurre una excepción, se ejecuta el bloque `rescue` correspondiente.
  - Si no ocurre ninguna excepción, se ejecuta el bloque `else`.
  - El bloque `ensure` se ejecuta siempre, haya o no excepción.