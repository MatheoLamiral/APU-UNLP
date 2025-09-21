# Ejercicio 4

## Qué modelos existen en cuanto a la continuacion luego de que finaliza el manejo de una excepción?
Existen dos modelos
### Reasunción
- Se refiere a la posibilidad de retomar la ejecución normal del progrma después de manejar una excepción
- El controlador de excepciones realiza las acciones necesarias para manejar la excepción (medidas correctivas) y luego el programa continúa su ejecución a partir del punto donde se produjo la excepción
- Lenguajes que utilizan Reasunción:
  - PL/1
    - se declara: `ON CONDITION(Nombre-excepción) Manejador`
    - se lanza : `SIGNAL CONDITION(Nombre-excepción)`
    - Los manejadores se ligan dinámicamente con las excepciones. Una excepción siempre estará ligada con el último manejador definido. (Manejo de pila de manejadores de excepciones)
    - El alcance de un manejador termina cuando finaliza la ejecución de la unidad donde fue declarado.
### Terminación
- El controlador de excepciones realiza las acciones necesarias para manejar la excepción, pero no se retorna al punto donde se produjo la excepción (invocador), continúa su ejecución a partir de la finalización del manejador.
- Es el más utilizado actualmente
- Lenguajes que utilizan Terminación:
  - ADA
    - se declara: `MiExcepcion: exception;`
    - se lanza: Las excepciones se levantan explícitamente con la palabra clave `raise`
    - Si la unidad que genera la excepción proporciona un manejador para la misma, el control se transfiere inmediatamente a ese manejador:
      - se omiten las acciones que siguen al punto en el que se generó la excepción,
      - se ejecuta el manejador
      - luego el programa continúa su ejecución normalmente, desde la instrucción que sigue al manejador.
    - Si la unidad no proporciona un manejador se busca por propagación
      - Se termina la unidad (bloque, paquete, subprograma o tarea) dónde se produce la excepción.
      - Si el manejador no se encuentra en ese lugar la excepción se propaga dinámicamente (quién lo llamó). Esto significa que se vuelve a levantar en otro ámbito.
      - Siempre tener en cuenta el alcance, puede convertirse en anónima. Al propagarse a otras unidades la variable excepción declarada ya no está en el alcance y quedará sin nombre y entrará por when others
  - C++
    - se declara:
      - `Try` para indicar los bloques donde pueden llegar a levantarse excepciones
      - `Catch` se utilizada para especificar los manejadores
        - `Catch(NombreDeLaExcepción)`
        - las cláusulas catch deben estar después del bloque try y antes de cualquier código que esté fuera del bloque try.
        - Los manejadores van asociados a bloques { }
    - se lanza: 
      - `Throw` Se utiliza para lanzar explícitamente una excepción
      - Permite pasar parámetros al levantar la excepción. `throw MiExcepcion(parametro1, , parametroN);`
    - Si se lanza una excepción el control se transfiere inmediatamente a la cláusula catch.
    - Si la excepción coincide con el tipo especificado en la cláusula catch, se ejecuta el bloque de código de esa cláusula catch.
    - Si la excepción se maneja exitosamente, la ejecución continúa después del bloque try-catch.
    - Si no se encuentra una catch correspondiente o no se maneja la excepción, la excepción puede propagarse hacia bloques try-catch externos.
    - Sino puede resultar en una finalización abrupta del programa
  - CLU
    - se declara: 
      - `<instrucción> except <lista_de_controladores> end`
      - donde `<instrucción>` puede ser cualquier instrucción (compuesta) del lenguaje. Si la ejecución de una invocación de procedimiento dentro de `<instrucción>` genera una excepción, el control se transfiere a `<lista_de_controladores>`
      - Los manejadores se colocan al lado de una sentencia simple o compleja y llevan la palabra clave `when`
      ```CLU
        <sentencia> except
        when Nombre-Excepcion: Manejador1;
        when Nombre-Excepcion: Manejador2;
        ...
        when others: ManejadorN;
        end;
      ```
    - se lanza:
      - Se lanzan explícitamente con la palabra clave `signal`
    - Solamente pueden ser lanzadas por los procedimientos. si una instrucción genera una excepción, el procedimiento que contiene la instrucción retorna anormalmente al generar la excepción. Un procedimiento no puede manejar una excepción generada por su ejecución, quien llama al procedimiento debe encargarse de manejarla.
    - La excepciones que un procedimiento puede lanzar se declaran en su encabezado.
    - Se pueden pasar parámetros a los manejadores.
    - Una excepción se puede volver a levantar una sola vez utilizando `resignal`
    - Una excepción se puede levantar en cualquier lugar del código
    - Si no encuentra el manejador es por Propagación al producirse una excepción:
    - Se termina el procedimiento donde se levantó la excepción y devuelve el control al llamante inmediato donde se debe encontrar el manejador.
    - Si el manejador se encuentra en ese ámbito, se ejecuta y luego se pasa el control a la sentencia siguiente a la que está ligado dicho manejador.
    - Si el manejador no se encuentra en ese lugar la excepción se propaga estáticamente en las sentencias asociadas. Esto significa que el proceso se repite para las sentencias incluidas estáticamente.
    - En caso de no encontrar ningún manejador en el procedimiento que hizo la llamada se levanta una excepción failure y devuelve el control, terminando todo el programa
  - Java
    - Al igual que C++ las excepciones son objetos que pueden ser alcanzados y manejados por manejadores adicionados al bloque donde se produjo la excepción.
    - Cada excepción está representada por una instancia de la clase `Throwable` o de una de sus subclases (`Error` y `Exception`)
    - La gestión de excepciones se lleva a cabo mediante cinco palabras clave: `try`, `catch`, `throw`, `throws`, `finally`
    - Se debe especificar mediante la cláusula `throws` cualquier excepción que se envía desde un método
    - Se debe poner cualquier código que el programador desee que se ejecute siempre, en el método `finally`.
    - Fases del tratamiento de excepciones
      - Detectar e informar del error:
        - Lanzamiento de Excepciones → `throw`
        - Un método detecta una condición anormal que le impide continuar con su ejecución y finaliza “lanzando” un objeto Excepción.
      - Recoger el error y tratarlo:
        - Captura de Excepciones → bloque `try-catch`
        - Un método recibe un objeto Excepción que le indica que otro método no ha terminado correctamente su ejecución y decide actuar en función del tipo de error.
  - Python
    - Se manejan a través de bloques try except
    - La declaración `try` funciona de la siguiente manera:
      - Primero, se ejecuta el bloque `try` (el código entre las declaración `try` y `except`).
      - Si no ocurre ninguna excepción, el bloque `except` se saltea y termina la ejecución de la declaración `try`.
      - Si ocurre una excepción durante la ejecución del bloque `try`, el resto del bloque se saltea. Luego, si su tipo coincide con la excepción nombrada luego de la palabra reservada `except`, se ejecuta el bloque `except`, y la ejecución continúa luego de la declaración `try`.
      - Si ocurre una excepción que no coincide con la excepción nombrada en el `except`, esta se pasa a declaraciones `try` de más afuera; si no se encuentra nada que la maneje, es una excepción no manejada, y la ejecución se frena con un mensaje de error.
    - Qué sucede cuando una excepción no encuentra un manejador en su bloque “`try except`”?
      - Busca estáticamente
          - Analiza si ese try está contenido dentro de otro y si ese otro tiene un manejador para esa excepción. Sino...
      - Busca dinámicamente
          - Analiza quién lo llamó y busca allí
      - Si no se encuentra un manejador, se corta el proceso y larga el mensaje standard de error
      - Levanta excepciones explícitamente con “`raise`”
  - PHP
    - Una excepción puede ser lanzada (`thrown`), y atrapada ("`catched`")
    - El código esta dentro de un bloque `try`,
    - Cada bloque `try` debe tener al menos un bloque `catch` correspondiente.
    - Las excepciones pueden ser lanzadas (o relanzadas) dentro de un bloque `catch`.
    - Se puede utilizar un bloque `finally` después de los bloques `catch`
    - El objeto lanzado debe ser una instancia de la clase `Exception` o de una subclase de `Exception`. Intentar lanzar un objeto que no lo es resultará en un Error Fatal de PHP.
    - Cuando una excepción es lanzada, el código siguiente a la declaración no será ejecutado, y PHP intentará encontrar el primer bloque `catch` coincidente. Si una excepción no es capturada, se emitirá un Error Fatal de PHP con un mensaje "`Uncaught Exception ...`" ("Excepción No Capturada"), a menos que se haya definido un gestor con `set_exception_handler()`.

### Cuál de estos modelos es más inseguro y por qué?
- El modelo de Reasunción es considerado más inseguro en comparación con el modelo de Terminación.
- Retomar desde un estado potencialmente inconsistente, como el control regresa al punto donde se produjo la excepción, existe el riesgo de que el estado del programa no haya sido completamente recuperado o corregido. Esto puede provocar errores difíciles de detectar, como corrupción de datos o ciclos infinitos.
- Mayor complejidad para garantizar la corrección, El programador debe asegurarse de que, luego de manejar la excepción, todas las condiciones necesarias para continuar con la ejecución normal se hayan restablecido correctamente. Esto incrementa la carga cognitiva y la posibilidad de errores sutiles.
- Menor claridad en el flujo del programa, la reasunción puede dificultar la lectura y el razonamiento del flujo del programa, ya que la ejecución se puede retomar en lugares inesperados con un estado modificado.
- En cambio, en el modelo de Terminación, se descarta el contexto donde ocurrió la excepción y la ejecución continúa desde el manejador o después de él, reduciendo el riesgo de continuar con datos corruptos.
- Este enfoque es más predecible y más fácil de razonar, por eso es el que adoptan la mayoría de los lenguajes modernos (como ADA, Java, Python, C++, etc.).