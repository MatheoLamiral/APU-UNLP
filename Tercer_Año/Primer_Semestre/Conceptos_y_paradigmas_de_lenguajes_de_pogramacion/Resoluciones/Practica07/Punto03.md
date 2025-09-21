# Ejercicio 3

## Inciso 1
### Producto cartesiano
El producto cartesiano de n conjuntos A1, A2,...,An, denotado A1xA2x...xAn, es un conjunto cuyos elementos están ordenados n-tuplas (a1, a2,...,an), donde cada ak pertenece a Ak. Se utiliza para definir registros.
### Correspondencia finita,
Es una función de un conjunto finito de valores de un tipo de dominio (DT) en valores de un tipo del dominio, o conjunto resultante (RT). Se utiliza para definir arreglos.
### Union y unión discriminada
La unión/union discriminada de dos o mas tipos define un tipo como la disjunción de los tipos dados. Permite manipular diferentes tipos en distinto momento de la ejecución y el chequeo debe hacerce en ejecución. La declaración es muy similar a la del producto cartesiano. La diferencia es que sus campos son mutuamente excluyentes.  
Unión discriminada agrega un discriminante para indicar la opción elegida. Si tenemos la unión discriminada de dos conjuntos S y T, y aplicamos el discriminante a un elemento **e** perteneciente a la unión discriminada devolverá S o T. El elemento **e** debe manipularse de acuerdo al valor del discriminante.
- Problemas:
    - El discriminante y las variantes pueden manejarse independientemente uno de otros.
    - La implementación del lenguaje puede ignorar los chequeos.
    - Puede omitirse el discriminante, con lo cual aunque se quisiera no se puede chequear.
### Recursivos
Un tipo de dato recursivo T se define como una estructura que puede contener componentes del tipo T. Define datos agrupados cuyo tamaño puede crecer arbitrariamente y cuya estructura puede ser arbitrariamente compleja. Los lenguajes de programación convencionales soportan la implementación de los tipos de datos recursivos a través de los punteros. Un **puntero** es una referencia a un objeto y una **variable puntero** es una variable cuyo r-valor es una referencia a un objeto.

## Inciso 2

- Java
  ```Java
  class Persona {
    String nombre;
    String apellido;
    int edad;
  }
  ```
  - Producto cartesiano
- C
  ```c
  typedef struct _nodoLista {
    void *dato;
    struct _nodoLista *siguiente
  } nodoLista;
  typedef struct _lista {
    int cantidad;
    nodoLista *primero
  } Lista;
  ```
  - Recursivo
  - Producto cartesiano
- C
  ```c
  union codigo {
    int numero;
    char id;
  };
  ```
  - Unión
- Ruby
  ```Ruby
    hash = {
        uno: 1,
        dos: 2,
        tres: 3,
        cuatro: 4
    }
  ```
  - Correspondencia finita
- PHP
  ```php
  function doble($x) {
    return 2 * $x;
  }
  ```
  - Correspondencia finita
- Python
  ```python
   tuple = ('physics','chemistry', 1997, 2000)
  ```
  - Producto cartesiano
  - Correspondencia finita
- Haskell
  ```haskell
    data ArbolBinarioInt =
    Nil |
    Nodo int
    (ArbolBinarioInt dato)
    (ArbolBinarioInt dato)
  ```
  - Ayuda para interpretar: ‘ArbolBinarioInt’ es un tipo de dato que puede ser Nil (“vacío”) o un Nodo con un dato número entero (int) junto a un árbol como hijo izquierdo y otro árbol como hijo derecho
  - Recursión
  - Unión
- Haskell
  ```haskell
    data Color = Rojo | Verde | Azul
  ```
  - Ayuda para interpretar: ‘Color’ es un tipo de dato que puede ser Rojo, Verde o Azul.
  - Unión

