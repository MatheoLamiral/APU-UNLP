# Ejercicio 10

## Qué es una ligadura?
Una ligadura (binding) es la asociación entre un nombre (identificador) y una entidad del programa, como un valor, tipo de dato, dirección de memoria, procedimiento o estructura. Es un concepto fundamental en la semántica de un programa porque determina cómo y cuándo se resuelven los nombres utilizados.  
- Por ejemplo:  
  - Asignar un valor a una variable (`x = 5`) implica una ligadura entre el nombre `x` y el valor `5`.  
  - Declarar `int x`; en C crea una ligadura entre `x` y el tipo `int`.  

## Importancia de la ligadura 
- Permite dar significado a los nombres dentro de un programa.  
- Afecta el comportamiento, alcance, reutilización y rendimiento del código.  
- Define cuándo y cómo se conoce la relación entre identificadores y entidades, lo cual influye directamente en la semántica (significado) del programa.  

## Ligadura estática
- Se realiza en tiempo de compilación.  
- Es predecible y eficiente, ya que se conoce antes de ejecutar el programa.  
- Se asocia con lenguajes fuertemente tipados o compilados, como C, Java o Pascal.  
- Ejemplo en C:
  - `int x = 10;`
  - la variable `x` está ligada a su tipo y ubicación en tiempo de compilacion

## Ligadura dinámica
- Se realiza durante la ejecución del programa.  
- Permite mayor flexibilidad, pero puede ser menos eficiente y más difícil de depurar.  
- Es común en lenguajes interpretados o con tipado dinámico, como Python, JavaScript o Ruby.  
- Ejemplo en Python:
  - ```Python
        def saludar():
            print("hola")
        f = saludar 
    ```
  - `f` se liga dinámicamente a la función `saludar` en tiempo de ejecución 

## Comparacion de ligadura estática y ligadura dinámica
| Característica | Ligadura Estática | Ligadura Dinámica |
| --- | --- | --- |
| Momento de resolución | La asociación entre nombres y entidades se establece durante la compilación. El compilador resuelve los tipos, direcciones y estructuras antes de que el programa corra. | La asociación se realiza mientras el programa se está ejecutando. Esto permite decidir qué entidad corresponde a un nombre en tiempo real. |
| Rendimiento | Al estar todo definido de antemano, el programa puede ejecutarse más rápido y consumir menos recursos en tiempo de ejecución. | Puede haber una sobrecarga en tiempo de ejecución ya que el sistema debe resolver asociaciones dinámicamente. |
| Flexibilidad | Menor flexibilidad. Las decisiones están fijadas al momento de compilar, lo que limita la capacidad de modificar comportamientos en tiempo de ejecución. | Mayor flexibilidad. Se pueden modificar asociaciones de manera dinámica, como reasignar funciones, tipos o comportamientos. |
| Verificación de errores | Los errores pueden detectarse en tiempo de compilación, lo que facilita encontrar problemas antes de ejecutar el programa. | Los errores pueden surgir en tiempo de ejecución, haciendo que algunos fallos solo se detecten al correr el código. |
| Ejemplo de lenguaje | Lenguajes como C, C++ y Java utilizan principalmente | Lenguajes como Python, JavaScript y Ruby permiten |
|  | ligadura estática para tipos, variables y métodos. | cambiar o asociar entidades a nombres durante la ejecución. |