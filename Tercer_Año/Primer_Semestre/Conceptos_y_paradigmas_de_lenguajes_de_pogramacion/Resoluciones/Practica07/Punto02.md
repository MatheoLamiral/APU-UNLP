# Ejercicio 2

## Tipo de dato
Un tipo de dato es un atributo o categoría que define el conjunto de valores que una variable puede tomar y las operaciones que se pueden realizar sobre esos valores. Nos permiten organizar los datos a través del concepto de tipo y clasificarlos de acuerdo con diferentes categorías. Tienen un comportamiento semántico o sentido.

## Tipo predefinido elemental
Es un tipo de dato que viene incorporado nativamente en el lenguaje de programación. Estos tipos no requieren definición adicional por parte del programador y suelen estar optimizados para el hardware subyacente y son una abstracción de él. 
- Por ejemplo:
  - Enteros: `int` , `integer` 
  - Reales: `float`, `double` 
  - Booleanos: `bool`, `boolean` 
  - Caractéres: `char`

## Tipo definido por el usuario
Los lenguajes de programación permiten al programador especificar agrupaciones de objetos de datos elementales (o tipos de datos predefinidos). Esto se logra mediante la representación de una serie de constructores que permiten definir lo que denominamos tipos de datos definidos por el usuario. Separan la especificación de la implementación. Se definen los tipos que el problema necesita, a partir de datos predefinidos o combinando estructuras de datos existentes.
- Por ejemplo:
  - Clases:
    ```Java
    public class Persona {
        String nombre;
        int edad;
        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }
    }
    ```
  - Estructura:
    ```C
    struct Punto {
        int x;
        int y;
    };
    ```
  - Enumeración:
    ```C
    enum Dia { Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo };
    ```