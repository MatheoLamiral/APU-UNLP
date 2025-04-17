# Ejercicio 2

## Qué significa compilar un programa?
Compilar un programa significa tomar todo el código fuente que se escribe, y traducirlo a lenguaje de máquina   
## Pasos para compilar un programa
### Etapa de análisis   
- Es la etapa más vinculada al código fuente. Se divide en sub - etapas:  
  - **Análisis léxico (Program Scanner)**
    - Realiza el análisis a nivel de palabra (Lexema)  
    - Divide el programa en sus elementos/categorías: identificadores, delimitadores, símbolos especiales, operadores, números, palabras clave, palabras reservadas, comentarios, etc.  
    - Analiza el tipo de cada uno para ver si son TOKENS válidos  
    - Lleva una tabla para la especificación del analizador léxico. Incluye cada categoría, el conjunto de atributos y acciones asociadas  
    - El resultado de este paso será el descubrimiento de los items léxicos o tokens y detección de errores.  
  - **Análisis sintáctico (Program Parser)**
    - El análisis se realiza a nivel de sentencia/ estructuras.
    - Usa los tokens del analizador léxico
    - Tiene como objetivo encontrar las estructuras presentes en su entrada.  
    - Se alterna/interactúa con el análisis léxico y análisis semántico.  
    - Se usa una gramática para construir el "árbol sintáctico" / "árbol derivación" del programa  
    - El objetivo principal de un árbol de derivación es representar una sentencia del lenguaje y validar de esta forma que pertenece o no a la gramática  
  - **Análisis Semántico (Programa de semántica estática)**
    - Deben pasar correctamente antes, Scanner y Parser
    - Es la fase modular y una de las más importantes  
    - Procesa las estructuras sintácticas reconocidas por el analizador sintáctico 
    - Realiza la comprobación de tipos, duplicados, etc.
    - Es el nexo entre etapas inicial y final del compilador (Análisis y Síntesis)  
### Código intermedio
- Puede generarse código intermedio, es decir, realizar la transformación del "código fuente" en una representación de "código intermedio" para una máquina abstracta  
  
### Etapa de síntesis   
- Es la parte casi final   
  - Construye el programa ejecutable y genera el código necesario  
  - Se genera un Programa objeto completo  
  - Optativamente se realiza una etapa de optimización  
  - El loader lo carga en memoria   
  - Se genera el código final   

## En qué paso interviene la semántica y cuál es su importancia dentro de la compilación?
La **semántica** interviene en la etapa de análisis, particularmente en el **análisis semántico**. Este paso es clave porque valida que el código tenga sentido y sea lógico. No alcanza con que esté bien escrito (sintácticamente); también tiene que estar correctamente estructurado en cuanto a su significado.  