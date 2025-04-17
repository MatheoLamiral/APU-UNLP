# Ejercicio 6

## Código en Pascal
```Pascal
    Procedure ordenar_arreglo(var arreglo: arreglo_de_caracteres;cont:integer);
    var
        i:integer; ordenado:boolean;
        aux:char;
    begin
        repeat
            ordenado:=true;
            for i:=1 to cont-1 do
                if ord(arreglo[i])>ord(arreglo[i+1])then 
                begin
                    aux:=arreglo[i];
                    arreglo[i]:=arreglo[i+1];
                    arreglo[i+1]:=aux; ordenado:=false
                end;
        until ordenado;
    end;
```
## Código equivalente en python
```Python
    def ordenar_arreglo(arreglo, cont):
    ordenado = False
    while not ordenado:
        ordenado = True
        for i in range(cont - 1):
            if ord(arreglo[i]) > ord(arreglo[i + 1]):
                aux = arreglo[i]
                arreglo[i] = arreglo[i + 1]
                arreglo[i + 1] = aux
                ordenado = False
```
## Explicación de la sintaxis y semántica del código en Python
### Arreglos (listas en Python)
- **Sintaxis**: Se usan listas con corchetes `[]`.
  - Ejemplo: `arreglo = ['d', 'b', 'a']`
- **Semántica**: Las listas son estructuras dinámicas y ordenadas que permiten modificar sus elementos accediendo por índice.
  - `arreglo[i]` accede al elemento en la posición `i`.

### def (definición de función)
- **Sintaxis**: `def nombre_funcion(parámetros):`
- **Semántica**: Define una función. En este caso, la función toma una lista (`arreglo`) y un entero (`cont`) como parámetros.
- A diferencia de Pascal, donde se usa procedure, Python utiliza `def` para funciones que pueden (o no) retornar valores.

### while (estructura de repetición condicional)
- **Sintaxis**: `while condición:`
- **Semántica**: Repite el bloque de instrucciones mientras la condición sea verdadera.
- En este caso: se repite hasta que ordenado sea True.

### for (estructura de repetición con contador)
- **Sintaxis**: `for variable in range(n):`
- **Semántica**: Itera n veces.
- `range(cont - 1)` genera índices desde 0 hasta cont - 1, lo cual permite acceder a `arreglo[i]` y `arreglo[i+1]` sin error.

### if (estructura condicional)
- **Sintaxis**: `if condición:`
- **Semántica**: Ejecuta el bloque si la condición es verdadera.
- Se usa `ord(arreglo[i])` para comparar el valor ASCII del carácter.

## Asignación de variables
- En Python no se declaran los tipos de las variables (tipado dinámico).
- `aux = arreglo[i]` crea la variable y le asigna valor sin declarar tipo.
- Esto difiere de Pascal, donde hay que declarar tipo y nombre previamente (`aux: char;`).

