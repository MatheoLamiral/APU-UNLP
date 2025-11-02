# Ejercicio 7

### Verdadero o Falso

- Un trigger se ejecuta únicamente cuando se inserta una fila en una tabla.
  - **Falso**, se activa cuando ocurre en una tabla un evento en particular, como un UPDATE, INSERT o DELETE.
- Un trigger puede ejecutarse antes o después de la operación, esto es definido automáticamente según el tipo de la operación (UPDATE, INSERT o DELETE)
  - **Falso**, si se ejecuta antes o despues lo determina BEFORE o AFTER
- Todo trigger debe asociarse a una tabla en concreto. 
  - **Verdadero**, ya que por definición un trigger es un objeto con nombre dentro de una base de datos el cual se asocia con una tabla 
- NEW y OLD son palabras clave que permiten acceder a los valores de las filas afectadas y se pueden usar ambos independientemente de la operación utilizada.
  - **Falso**, OLD se refiere a un registro existente que va a borrarse o que va a actualizarse antes de que esto ocurra. NEW se refiere a un registro nuevo que se insertará o a un registro modificado luego de que ocurre la modificación
- FOR EACH ROW en un trigger se usa para indicar que el trigger se ejecutará una vez por cada fila afectada por la operación.
  - **Verdadero**, 
