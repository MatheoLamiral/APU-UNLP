# Ejercicio 4

## Tipos de expresiones que se pueden escribir en Java
- **Literales**
  - Descripción: Valores directos que representan constantes.
  - Ejemplos: 5 (entero), 3.14 (flotante), "Hola" (cadena), true (booleano), null (referencia nula).
- **Expresiones con variables**
  - Descripción: Uso de identificadores para acceder a valores almacenados.
  - Ejemplo: x (donde x es una variable declarada como int x = 5;).
- **Expresiones aritméticas**
  - Descripción: Operaciones matemáticas que combinan valores numéricos.
  - Ejemplos: x + 3, 5 * y, a / b - 2.
- **Expresiones relacionales y lógicas**
  - Descripción: Comparaciones y operaciones booleanas.
  - Ejemplos: x > 5, a == b, x < 10 && y > 0, !condicion.
- **Expresiones de asignación**
  - Descripción: Asignan un valor y devuelven ese valor como resultado.
  - Ejemplo: x = 10 (asigna 10 a x y evalúa a 10).
- **Expresiones de invocación de métodos**
  - Descripción: Llamadas a métodos que devuelven un valor.
  - Ejemplo: Math.sqrt(16) (devuelve 4.0), objeto.metodo().
- **Expresiones de acceso a campos o elementos**
  - Descripción: Acceso a propiedades de objetos o elementos de arreglos.
  - Ejemplos: objeto.campo, arreglo[2].
- **Expresiones de creación de objetos**
  - Descripción: Instanciación de clases o arreglos.
  - Ejemplos: new String("Hola"), new int[5].
- **Expresiones condicionales (operador ternario)**
  - Descripción: Forma compacta de una condición que devuelve un valor.
  - Ejemplo: x > 0 ? 1 : -1 (devuelve 1 si x > 0, -1 si no).  
- **Expresiones lambda (desde Java 8)**
  - Descripción: Expresiones funcionales para interfaces con un solo método.
  - Ejemplo: x -> x * 2 (una función que duplica un número).
Nota: En Java, estructuras como if, while o for son statements, no expresiones, porque no producen valores directamente
## Facilidades provistas por Java para la organización del programa
1. **Clases y Objetos**
- Descripción: Java organiza el código en clases, que son plantillas para crear objetos. Todo el código debe residir dentro de una clase, promoviendo la encapsulación y la modularidad.
- Ejemplo:
  ```Java
    public class Persona {
        String nombre;
        int edad;

        public Persona (String unNombre, int unaEdad){
            this.nombre = unNombre;
            this.edad = unaEdad;
        }
    }
  ```
- Beneficio: Agrupa datos y comportamientos relacionados, facilitando la reutilización y el mantenimiento.
2. **Paquetes (Packages)**
- Descripción: Los paquetes permiten agrupar clases relacionadas en un espacio de nombres, evitando conflictos y organizando el código en módulos jerárquicos.
- Ejemplo:
  ```Java
    package com.ejemplo.modelos;

    public class Ejemplo {
        //código de la clase
    }
  ```
- Uso: import com.ejemplo.modelos.Empleado; para acceder desde otro archivo.
- Beneficio: Estructura proyectos grandes (ej. java.util, java.io) y mejora la legibilidad.
3. **Modificadores de acceso**
- Descripción: Java ofrece modificadores como public, private, protected y el acceso por defecto (package-private) para controlar la visibilidad de campos, métodos y clases.
- Ejemplo:
  ```Java
    public class Persona {
        private String nombre; // solo accesible dentro de la clase
        private int edad; // solo accesible dentro de la clase

        public Persona (String unNombre, int unaEdad){
            this.nombre = unNombre;
            this.edad = unaEdad;
        }
    }
  ```
- Beneficio: Fomenta la encapsulación y protege la implementación interna.
4. **Herencia**
- Descripción: Permite que una clase herede campos y métodos de otra, organizando jerarquías de código reutilizable.
- Ejemplo:
  ```Java
    public class Animal{
        private String nombre;
    }

    public class Perro extends Animal {
        void ladrar () {
            Sistem.out.println("Guau");
        }
    }
  ```
- Beneficio: Reduce duplicación y organiza relaciones entre entidades.
5. **Interfaces**
- Descripción: Define contratos que las clases deben implementar, separando la especificación de la implementación.
- Ejemplo:
  ```Java
    public interface Volador{
        void volar();
    }

    public class Pájaro extends Volador {
        void volar() {
            Sistem.out.println("Volando");
        }
    }
  ```
- Beneficio: Promueve el diseño modular y la interoperabilidad.
6. **Clases Abstractas**
- Descripción: Clases que no se pueden instanciar directamente, usadas como base para otras clases con métodos abstractos o concretos.
- Ejemplo:
  ```Java
    public abstract class Vehiculo{
        abstract void mover();
    }

    public class Auto extends Vehiculo {
        void mover()) {
            Sistem.out.println("El auto se muevo");
        }
    }
  ```
- Beneficio: Organiza jerarquías con comportamiento compartido.
7. **Métodos y constructores**
- Descripción: Los métodos organizan la lógica en bloques reutilizables, y los constructores inicializan objetos de manera estructurada.
- Ejemplo:
  ```Java
    public class Libro {
        private String titulo;

        public Libro (String unTitulo){
            this.titulo = unTitulo;
        }

        public String getTitulo (){
            return this.titulo;
        }
    }
  ```
- Beneficio: Divide el código en unidades funcionales claras.
8. **Excepciones**
- Descripción: El manejo de excepciones con try, catch, throw y throws organiza la gestión de errores.
- Ejemplo:
  ```Java
    public void dividir (int a, int b) throws ArithmeticException{
        if (b==0) throw new ArithmeticException ("División por cero");
        System.out.println(a/b);
    }
  ```
- Beneficio: Separar la lógica normal del manejo de casos excepcionales.
9. **Enumeraciones (Enums)**
- Descripción: Define conjuntos de constantes con un tipo seguro, organizando valores predefinidos.
- Ejemplo:
  ```Java
    public enum Dia{
        LUNES, MARTES, MIERCOLES
    }
  ```
- Beneficio: Mejora la claridad y evita errores con valores mágicos.
10.  **Anotaciones (Annotations)**
- Descripción: Metadatos que organizan y configuran el comportamiento del código (ej. @Override, @Deprecated).
- Ejemplo:
  ```Java
    @Override
    public String toString(){
        return "Objeto personalizado";
    }
  ```
- Beneficio: Facilita la configuración y el mantenimiento.
11. **Estructura de archivos y convención de nombres**
- Descripción: Java exige que el nombre de la clase pública coincida con el nombre del archivo (ej. Clase.java) y sigue convenciones como CamelCase.
- Ejemplo: 
  - Archivo Persona.java contiene public class Persona.
- Beneficio: Estandariza la organización física del proyecto.

## Atributos que posee y no posee Java
1. **Simplicidad**
- Posee: Parcialmente
- Justificación: Java tiene una sintaxis estructurada y explícita, pero no es tan simple como lenguajes como Python o Lua. Requiere declaraciones de tipos, modificadores de acceso y una estructura de clases obligatoria, lo que añade complejidad inicial (ej. public class Hola { public static void main(String[] args) { System.out.println("Hola"); } }). Aunque es más simple que  ++ al eliminar punteros manuales y gestión de memoria, su verbosidad lo aleja de la simplicidad máxima.
2. **Legibilidad**
- Posee: Sí
- Justificación: La sintaxis de Java es clara y estructurada, con nombres descriptivos y convenciones como CamelCase que facilitan la lectura (ej. getNombre()). Los bloques delimitados por llaves {} y la obligatoriedad de clases hacen que el código sea predecible y fácil de seguir, aunque puede ser más verboso que lenguajes como Ruby.
3. **Claridad en los bindings**
- Posee: Sí
- Justificación: Java usa tipado estático, lo que significa que los bindings (asociaciones entre variables y tipos) se definen en tiempo de compilación y son explícitos (ej. int x = 5;). Esto evita ambigüedades y errores en tiempo de ejecución relacionados con tipos, ofreciendo claridad y seguridad en cómo se vinculan los datos.
4. **Confiabilidad**
- Posee: SíJustificación: Java está diseñado para ser confiable, con características como manejo de excepciones robusto (try-catch), tipado estático, y una máquina virtual (JVM) que previene errores comunes como desbordamientos de memoria o punteros nulos no controlados. Es ampliamente usado en sistemas críticos (ej. bancarios), lo que demuestra su fiabilidad.
5. **Soporte**
- Posee: Sí
- Justificación: Java cuenta con un soporte excepcional: respaldado por Oracle, tiene una comunidad masiva, documentación extensa y un ecosistema de bibliotecas (ej. Spring, Hibernate). Además, su compatibilidad multiplataforma y actualizaciones regulares aseguran soporte continuo para desarrolladores.
6. **Abstracción**
- Posee: Sí
- Justificación: Java ofrece potentes mecanismos de abstracción a través de clases, interfaces y clases abstractas (ej. interface Comparable { int compareTo(T o); }). La programación orientada a objetos y las características modernas como lambdas (desde Java 8) permiten modelar problemas complejos sin exponer detalles de bajo nivel.
7. **Ortogonalidad**
- Posee: Parcialmente
- Justificación: Java no es completamente ortogonal debido a restricciones en su diseño. Por ejemplo, no todos los tipos pueden usarse uniformemente (los primitivos como int no son objetos, a diferencia de Integer), y estructuras como switch no aceptan todos los tipos (solo enteros, cadenas desde Java 7). Aunque es consistente en muchas áreas, estas excepciones limitan su ortogonalidad frente a lenguajes como Scheme.
8. **Eficiencia** 
- Posee: Sí, pero con matices
- Justificación: Java es eficiente gracias a la JVM y el compilador JIT (Just-In-Time), que optimizan el código en tiempo de ejecución, alcanzando un rendimiento cercano a lenguajes compilados como C++. Sin embargo, no iguala la eficiencia de bajo nivel de C++ o Rust debido a la sobrecarga de la JVM y el garbage collector, lo que lo hace menos ideal para aplicaciones de tiempo real extremadamente críticas.