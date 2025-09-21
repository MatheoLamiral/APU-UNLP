# Ejercicio 14

## Características de los lenguajes basados en scripts
- Combinar Programas:
    - Los lenguajes script de propósito general (Perl, Python) suelen conocerse como glue-languages.
    - Se diseñaron para “pegar” programas existentes a fin de construir un sistema mas grande.
    - Se utilizan como lenguajes de extensión, ya que permiten al usuario adaptar o extender las funcionalidad de las herramientas script
- Desarrollo y evolución rápida:
    - Algunos “script” se escriben y ejecutan una única vez como una secuencia de comandos.
    - En otros casos se utilizan más frecuentemente por lo que deben ser fácilmente adaptados a nuevos requerimientos. Esto implica que deben ser fáciles de escribir y con una sintaxis concisa.
    - Asociado a editores livianos
- Interpretados:
    - La eficiencia no es un requisito esencial para los scripts. Sin embargo debe ser considerado al combinar programas.
    - La velocidad de ejecución de los script no es de importancia crítica. Los gastos generales de interpretación y de comprobación dinámica se puede tolerar.

## Algunos lenguajes que utilizan scripts
- Python
- Perl
- Ruby
- JavaScript
- Bash

## Tipado de datos de los lenguajes basados en scripts
- Dado la falta de declaraciones, muchos LBS incorporan tipificación dinámica.
    - Cuando se utilizan como glue-languages puede necesitarse intercambiar datos de distinto tipo entre distintos subsistemas y estos pueden ser incompatibles. Por esto, si el LBS tiene un sistema de tipos simple podría ser demasiado inflexible y por otro lado uno muy completo atentaría contra un rápido desarrollo y evolución del sistema.
    - Por lo general los tipos no necesitan ser declarados (aunque esto los haga más difíciles)