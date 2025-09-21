# Ejercicio 10

## Map en JavaScript
La instrucción `map` en JavaScript es un método de los arrays que permite crear un nuevo array aplicando una función a cada elemento del array original, sin modificar el array original.

- Ejemplo:
```JavaScript
const numeros = [1, 2, 3];
const dobles = numeros.map(x => x * 2); // [2, 4, 6]
```
- Alternativas a map:
- `forEach`: Ejecuta una función para cada elemento, pero no retorna un nuevo array.
- `for/for...of`: Permiten recorrer y transformar manualmente los elementos.
- `reduce`: Puede usarse para transformar arrays, aunque es más general y flexible.
- `filter`: Retorna un nuevo array con los elementos que cumplen una condición (no transforma, solo filtra).