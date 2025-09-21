# Ejercicio 7

## En cuanto a las versiones estándar
- En ADA el índice del bucle for es una constante implícita dentro del cuerpo del bucle. Por lo tanto, cualquier intento de modificarlo (como ocurre dentro del procedimiento A, que hace i := i + 1) no está permitido. Esto generaría un error en tiempo de compilación. ADA protege fuertemente la integridad del control de bucles. 
- En Pascal, en las versiones estándar más antiguas, el índice del for es una variable ordinaria. Esto significa que sí se puede modificar desde dentro del bucle o desde fuera si el bucle utiliza como índice una variable accesible por un proceso o funcion que se llame desde el bucle, como ocurre en el procedimiento A. Este comportamiento puede provocar efectos no deseados o bucles infinitos, ya que el valor del índice cambia de forma no controlada por el bucle.

## En cuanto a las versiones actuales
- ADA mantiene su comportamiento mientras que en cuanto a Pascal, Free Pascal permite compilar el código, pero emite una advertencia si se modifica el índice del bucle. En tiempo de ejecución, el comportamiento puede ser impredecible y cambiar dependiendo del compilador. Delphi moderno impone restricciones más fuertes, el índice del for es de solo lectura, y modificarlo dentro del bucle puede dar un error en compilación o advertencia severa.