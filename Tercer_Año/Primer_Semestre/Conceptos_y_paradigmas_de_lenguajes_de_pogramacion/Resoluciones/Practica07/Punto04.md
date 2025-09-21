# Ejercicio 4

## Inciso 1
La mutabilidad de un dato se refiere a su capacidad de cambiar su valor interno después de haber sido creado. Por el contrario, un dato inmutable no puede ser modificado una vez creado, cualquier operación que parezca modificarlo en realidad crea un nuevo objeto.
- Ejemplo listas en Python (mutable)
  ```Python
  numeros = [1, 2, 3]
  numeros[0] = 100
  print(numeros)  # [100, 2, 3] → la lista fue modificada
  ```
- Ejemplo String en Python (inmutable)
  ```Python
  texto = "hola"
  texto[0] = "H"  # Error: 'str' object does not support item assignment
  ```
- Ejemplos en Ruby
  - Aclaración: En Ruby, todos los objetos son técnicamente mutables por defecto, pero se pueden volver inmutables con `freeze`
  - Ejemplo mutable
  ```Ruby
  saludo = "Hola"
  saludo << " mundo"
  puts saludo  # "Hola mundo"
  ```
  - Ejemplo inmutable
  ```Ruby
  mensaje = "Hola"
  mensaje.freeze
  mensaje << " mundo"  # Error: can't modify frozen String
  ```
## Inciso 2
No lo puedo afirmar ya que no tengo contexto de lo que pasa en memoria con ese dato, de si realmente el nuevo valor sobreescribe el valor anterior en el mismo esapcio de memoria, o si lo que ocurre es que se corta el enlace con el valor anterior y se carga el nuevo valor en otro espacio de memoria distinto