# Ejercicio 9

## C  
- **Semántica**:  
  - Finaliza anticipadamente la ejecución de bucles (for, while, do-while) o de una sentencia switch.  
- **Características principales**:  
  - Solo puede usarse dentro de bucles o sentencias switch.  
  - Al ejecutarse, el flujo del programa continúa con la siguiente instrucción fuera del bloque.  
  - No admite argumentos ni niveles adicionales.  
  
## PHP  
- **Semántica**:  
  - Termina la ejecución de bucles (for, while, foreach, do-while) o de una sentencia switch.  
  - Admite un argumento opcional numérico que indica cuántos niveles de bucles anidados se deben romper.  
- **Características principales**:  
  - Puede especificarse el número de estructuras a salir con un número (break 2;).  
  - Es útil en estructuras de control anidadas.  
  
## JavaScript 
- **Semántica**:  
  - Detiene la ejecución de un bucle (for, while, do-while, for...of, for...in) o una sentencia switch.  
  - Puede utilizarse con etiquetas (labels) para romper estructuras etiquetadas.  
- **Características principales**:  
  - Compatible con etiquetas para salir de estructuras anidadas.  
  - No acepta un número de niveles como en PHP.  
  - Solo puede romper una estructura a la vez si no se usan etiquetas.  

## Ruby  
- **Semántica**:  
  - Sale de un bucle (while, until, for, loop) o de un bloque (each, times, etc.).  
- **Características principales**:  
  - Puede devolver un valor al bloque o método que lo contiene.  
  - No rompe múltiples niveles como PHP.  
  - Se puede combinar con mecanismos como catch/throw para estructuras de control más complejas.  
