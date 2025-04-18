# Ejercicio 5 - ADA

## Tipos de datos 
Ada es un lenguaje fuertemente tipado. Esto significa que no permite conversiones implícitas entre tipos distintos, lo cual mejora la seguridad y confiabilidad del código. Los tipos de datos se dividen en varias categorías:
- Tipos escalares: enteros, reales, booleanos y caracteres.
- Tipos derivados: se pueden crear nuevos tipos a partir de otros ya existentes, con sus propias restricciones.
- Subtipos: permiten definir rangos específicos dentro de un tipo base.
- Tipos enumerados: definidos por el usuario, útiles para representar conjuntos finitos de valores.  
  
Gracias a este sistema de tipos, Ada detecta muchos errores durante la compilación, ayudando a evitar fallos en tiempo de ejecución.

## Tipos abstractos de datos - Paquetes 
Ada utiliza paquetes (packages) como mecanismo principal para definir tipos abstractos de datos. Un paquete encapsula tanto la declaración de tipos y operaciones (interfaz pública) como su implementación interna (parte privada). Esta separación permite:
- Ocultar los detalles de implementación.
- Reutilizar código de manera estructurada.
- Mejorar el mantenimiento y la legibilidad.

Los paquetes promueven una programación modular y organizada, facilitando la construcción de aplicaciones grandes y complejas.

## Estructuras de datos
Ada ofrece varios mecanismos para la construcción de estructuras de datos complejas:
- Arrays: permiten el almacenamiento de colecciones indexadas de elementos.
- Registros: agrupan varios campos de distintos tipos bajo un mismo nombre, similar a estructuras en otros lenguajes.
- Tipos de acceso (punteros): permiten la creación y manipulación de estructuras dinámicas como listas o árboles.
- Tipos definidos por el usuario: permiten construir tipos personalizados adaptados a las necesidades del programa.

Estas herramientas proporcionan flexibilidad para implementar estructuras clásicas como pilas, colas, listas enlazadas, entre otras.

## Manejo de excepciones 
Ada incluye un sistema robusto para el manejo de errores mediante excepciones. Este mecanismo permite capturar y controlar errores durante la ejecución del programa sin interrumpir su flujo de manera abrupta.  
El lenguaje ofrece excepciones predefinidas para errores comunes (como violaciones de rango o errores de tipo), y también permite que el programador defina sus propias excepciones personalizadas.  
El manejo de excepciones en Ada mejora la confiabilidad y estabilidad del software, especialmente en sistemas críticos.  

## Manejo de concurrencia
Ada se destaca por su soporte nativo para la programación concurrente mediante el uso de tasks. Cada task puede ejecutarse de forma independiente y simultánea con otras tareas. Para coordinar la interacción entre tareas, Ada ofrece mecanismos como:
- Entries y rendezvous: puntos de sincronización entre tareas.
- Objetos protegidos (protected objects): permiten el acceso seguro a recursos compartidos entre múltiples tareas, evitando condiciones de carrera.

Gracias a estas características, Ada es ideal para sistemas embebidos, tiempo real y aplicaciones que requieren ejecución paralela y sincronización precisa.
