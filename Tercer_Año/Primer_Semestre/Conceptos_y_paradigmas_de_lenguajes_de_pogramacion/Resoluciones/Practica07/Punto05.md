# Ejercicio 5

## Inciso 1
C permite tomar el l-valor de las variables, lo que permite modificar el contenido de esa ubicación mediante asignaciones u otras operaciones. Esto puede llegar a ser riesgoso ya que estamos trabajando a muy bajo nivel
```C
  int main(){
      int x = 10;
      int *ptr;
      ptr = &x; //toma el l-valor de la variable x y se lo asigna a ptr
      printf("valor de x: %d\n", x); //imprime 10
      *ptr = 20;
      printf("valor de x: %d\n", x); //imprime 20
      return 0;
  }
```
## Inciso 2
En el manejo de punteros existen algunos problemas:
1. **Violación de tipos**: ocurre cuando un puntero se usa para acceder a datos de un tipo diferente al esperado, lo que puede llevar a comportamientos no deseados o errores 
    ```C
    int x = 10
    float y = 3.7
    int*p = &x
    p++ //p apunta a la posición siguiente, es float
    p+=x //incrementa a y como entero 
    printf('%f',y) //imprime 13.0
    ```
2. **Referencias sueltas – referencias dangling**: es un puntero que contiene una dirección de una variable dinámica que fue desalocada, si luego se utiliza el puntero provocará un error
    ```C
    int *p = malloc(sizeof(int));
    *p = 5;
    free(p); //desaloca la memoria
    printf("%d\n", *p) //p ya no apunta a una celda de memoria válida (referencia suelta)
    ```
3. **Punteros no inicializados**: un puntero no inicializado contiene una dirección aleatoria, lo que puede provocar accesos a zonas de memoria que contienen basura si no se inicializan 
   ```C
    int *p;
    *p = 5; //p no se encuentra inicializado, por ende no apunta a una celda de memoria válida 
    ```
4. **Punteros y uniones discriminadas**: el uso de punteros con uniones discriminadas puede permitir accesos indebidos a memoria si no se verifica correctamente el tipo de datos almacenado, accediendo a campos incorrectos
   ```Java
    union ejemplo{ 
    int int_var 
    int* int_ref}
   ```
   En el caso de C, este es el mismo efecto que causa la aritmética de punteros. Para resolver este problema asociado con los punteros Java elimina la noción de puntero explicito completamente.
5. **Alias**: cuando dos o más punteros comparten un alias (apuntan a la misma dirección), una modificación a través de uno afecta a todos los demás, y esto puede llegar a causar efectos secundarios inesperados 
   ```C
    int* p1 
    int* p2 
    int x p1 = &x 
    p2 = &x
   ```
   - p1 y p2 son punteros
   - p1 y x son alias
   - p2 y x también lo son
6. **Liberación de memoria, objetos perdidos**: si los objetos en la heap dejan de ser accesibles (porque ninguna variable en la pila los apunta directa o indirectamente), se convierten en basura y la memoria no se libera
    ```C
    int *p = malloc(sizeof(int));
    p = null; // objeto perdido, memoria no liberada, no se ejecuta free(p)
    ```