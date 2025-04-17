# Ejercicio 3
## Variable estática
Su tiempo de vida abarca toda la ejecución del programa. Se asigna al inicio y se libera al final. Conserva su  valor entre llamadas si es local. Tiene un l-valor constante, ya que está asociada a una dirección fija durante todo el programa
- Ejemplo en C
 ```C
    void contar()
    {
        int x = 0;
        x++;
        printf("%d\n", x);
    }
 ```
- Ejemplo en ADA
  - En ADA no hay variables estáticas
## Variable automática o semiestática
Es una variable que se crea automáticamente al entrar a un bloque (por ejemplo, una función) y se destruye al salir. Tiene un l-valor válido solo mientras la variable está activa, ya que su dirección de memoria puede reutilizarse.
- Ejemplo en C
 ```C
    void funcion ()
    {
        int x = 10;
    }
 ```
- Ejemplo en ADA
 ```ADA
    procedure Prueba is 
        x : Integer := 10;
    begin
        null;
    end Prueba; 
 ```
## Variable dinámica
Es una variable cuya memoria se reserva en tiempo de ejecución, usando mecanismos como malloc en C o new en Ada.  El puntero que se utiliza tiene un l-valor (es una dirección válida), pero el objeto apuntado también tiene su propia dirección.
- Ejemplo en C
 ```C
    int * p = (int*)malloc(sizeof(int));
    *p = 5;
 ```
- Ejemplo en ADA
```ADA
   type PtrInt is acces Integer;
   p : PtrInt := Integer '(5)'
 ```
## Variable semidinámica
Se refiere a estructuras cuyos atributos pueden ser definidos en tiempo de ejecución, pero sin usar punteros directamente. Tiene l-valor, y aunque su contenido puede variar, su ubicación en memoria se puede determinar.
- Ejemplo en C
  - No existe un equivalente claro en C sin usar memoria dinámica
- Ejemplo en ADA
```ADA
    type Vactor is array (Positive range <>) of Integer;
    V : Vector(1..N) -- N definido en tiempo de ejecución
```



  