# Ejercicio 1

## Inciso 1
- Es un conjunto de reglas usadas por un lenguaje para estructurar y organizar sus tipos.
- El objetivo de un sistema de tipos es escribir programas seguros.
- Conocer el sistema de tipos de un lenguaje nos permite conocer de una mejor forma los aspectos semánticos del lenguaje.
- Provee mecanismos de expresión:
    - Expresar tipos intrínsecos o definir tipos nuevos.
    - Asociar los tipos definidos con construcciones del lenguaje.
- Define reglas de resolución:
    - Equivalencia de tipos
    - Compatibilidad de tipos 
    - Inferencia de tipos

## Inciso 2
- **Sistema de tipos fuerte**:  
  Un lenguaje con un sistema de tipos fuerte no permite conversiones automáticas entre tipos de datos distintos. Las operaciones entre tipos incompatibles generan un error, y el programador debe realizar conversiones explícitas si desea operar con diferentes tipos. Un lenguaje con un sistema de tipos fuerte garantiza que las operaciones respeten los tipos definidos, evitando errores de tipo en tiempo de ejecución. Esto suele implicar un chequeo de tipos más riguroso (ya sea en tiempo de compilación o en tiempo de ejecución) para asegurar que no se violen las reglas de tipo.
  - Por ejemplo: Python, Java y Rust, no permiten conversiones automáticas entre tipos incompatibles
- **Sistema de tipos débil**:  
  Un lenguaje con un sistema de tipos débil permite conversiones automáticas entre tipos de datos distintos, incluso si no son directamente compatibles. Un lenguaje con un sistema de tipos débil permite que las operaciones se realicen incluso si los tipos no coinciden, lo que puede llevar a errores en tiempo de ejecución o comportamientos inesperados. Esto suele estar relacionado con lenguajes que no chequean tipos estrictamente o que permiten manipulación directa de memoria.
  - Por ejemplo: JavaScript y PHP permiten conversiones automáticas entre tipos incompatibles. O también C, que permite manipulaciones de bajo nivel que pueden violar la seguridad de tipos.
  
## Inciso 3
- **Tipado estático**:  
    Los tipos de las variables se determinan y verifican en tiempo de compilación, antes de que el programa se ejecute, y para esto se puede exigir que:
    - Se puedan utilizar tipos de datos predefinidos
    - Todas las variables se declaren con un tipo asociado
    - Todas las operaciones se especifican indicando los tipos de los operandos requeridos y el tipo del resultado  
  
    El objetivo es detectar errores de tipo antes de la ejecución, lo que mejora la seguridad y el rendimiento.
    - Por ejemplo: Java y Rust requieren que los tipos de las variables se declaren explícitamente en tiempo de compilación, y estos tipos no pueden cambiar durante la ejecución.
- **Tipado dinámico**:  
    Los tipos de las variables se determinan en tiempo de ejecución, durante la ejecución del programa. No es necesario declarar los tipos de las variables explícitamente, y una variable puede cambiar de tipo durante la ejecución del programa. Los errores de tipo solo se detectan cuando el programa se ejecuta y se intenta realizar una operación incompatible.
    - Por ejemplo: en Python y JavaScriptlos los tipos de las variables se determinan en tiempo de ejecución, y una variable puede cambiar de tipo durante la ejecución