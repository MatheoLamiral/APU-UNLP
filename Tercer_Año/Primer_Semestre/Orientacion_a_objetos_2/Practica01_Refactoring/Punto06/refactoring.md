# Refactoring ArbolBinario

### smell: nombre de método poco explicativo `setDerecha`
1. Aplico **Rename method** a `setDerecha(ArbolBinario hijoDerecho)` cambiandolo a `setHijoDerecho(ArbolBinario hijoDerecho)`
### Smell: Duplicate code y comportamiento similar en los métodos `recorrerPreOrden`, `recorrerInOrden` y `recorrerPostOrden`
1. Aplico **Extract subclass**
   1. Creo las clases `ArbolPreOrden`, `ArbolInOrden` y `ArbolPostOrden` que extienden de `ArbolBinario`
   2. Creo los constructores de las nuevas clases que reciben el valor
3. Muevo los métodos `recorrerPreOrden`, `recorrerInOrden` y `recorrerPostOrden` a sus respectivas clases
4. Reemplazo `resultado += valor + " - "` por `resultado += this.getValor() + " - "` en cada uno de los métodos de las subclases 
5. Hago que la clase `ArbolBinario` sea abstracta
6. Creo el método abstracto `recorrer` en la clase `ArbolBinario` que será implementado por las subclases
7. Aplico **Rename method** a `recorrerPreOrden` cambiandolo a `recorrido()` para que implemente el método abstracto 
   1. Reemplazo las llamadas recursivas `this.getHijoIzquierdo().recorrerPostorden()` por `this.getHijoIzquierdo().recorrido()`
   2. Reemplazo las llamadas recursivas `this.getHijoDerecho().recorrerPostorden()` por `this.getHijoDerecho().recorrido()`
8. Aplico **Rename method** a `recorrerInOrden` cambiandolo a `recorrido()` para que implemente el método abstracto
   1. Reemplazo las llamadas recursivas `this.getHijoIzquierdo().recorrerInOrden()` por `this.getHijoIzquierdo().recorrido()`
   2. Reemplazo las llamadas recursivas `this.getHijoDerecho().recorrerInOrden()` por `this.getHijoDerecho().recorrido()`
9. Aplico **Rename method** a `recorrerPostOrden` cambiandolo a `recorrido()` para que implemente el método abstracto
   1. Reemplazo las llamadas recursivas `this.getHijoIzquierdo().recorrerPreOrden()` por `this.getHijoIzquierdo().recorrido()`
   2. Reemplazo las llamadas recursivas `this.getHijoDerecho().recorrerPreOrden()` por `this.getHijoDerecho().recorrido()`

### Smell: Consultas por null en las implementaciones de los métodos `recorrido()` de las subclases
1. Aplico **Introduce Null Object**
   1. Creo la clase `ArbolNulo` que extiende de `ArbolBinario`
      1. Creo el constructor de `ArbolNulo` vacío que no recibe ningún valor y setea `valor` en -1
      2. Implemento el método `recorrido()` en `ArbolNulo` para que retorne una cadena vacía
      3. Sobreescribo el método `setValor()` para que sea un método vacío 
      4. Sobreescribo el método `getHijoIzquierdo()` para que retorne `this`
      5. Sobreescribo el método `getHijoDerecho()` para que retorne `this`
      6. Sobreescribo el método `setHijoIzquierdo()` para que sea un método vacio
      7. Sobreescribo el método `setHijoDerecho()` para que sea un método vacio
   2. Elimino las consultas por null en los métodos `recorrido()` de las subclases
   3. Reemplazo en el constructor de `ArbolBinario` la inicialización de los hijos por `new ArbolNulo()`

# Refactoring ArbolBinarioTest
### En testUnSoloNodo
1. Reemplazo `ArbolBinario arbol = new ArbolBinario(10);` por:
   1. `ArbolBinario arbolPreOrd = new ArbolPreOrden(10);`
   2. `ArbolBinario arbolInOrd = new ArbolInOrden(10);`
   3. `ArbolBinario arbolPosOrd = new ArbolPostOrden(10);`
2. Reemplazo `arbol.recorrerPreOrden();` por `arbolPreOrd.recorrido();`
3. Reemplazo `arbol.recorrerInOrden();` por `arbolInOrd.recorrido();`
4. Reemplazo `arbol.recorrerPostOrden();` por `arbolPosOrd.recorrido();`

### En testSoloHijoIzquierdo
1. Reemplazo `ArbolBinario arbol = new ArbolBinario(10);` por:
   1. `ArbolBinario arbolPreOrd = new ArbolPreOrden(10);`
   2. `ArbolBinario arbolInOrd = new ArbolInOrden(10);`
   3. `ArbolBinario arbolPosOrd = new ArbolPostOrden(10);`
2. Reemplazo `arbol.setHijoIzquierdo(new ArbolBinario(5));` por:
   1. `arbolPreOrd.setHijoIzquierdo(new ArbolPreOrden(5));`
   2. `arbolInOrd.setHijoIzquierdo(new ArbolInOrden(5));`
   3. `arbolPosOrd.setHijoIzquierdo(new ArbolPostOrden(5));`
3. Reemplazo `arbol.recorrerPreOrden();` por `arbolPreOrd.recorrido();`
4. Reemplazo `arbol.recorrerInOrden();` por `arbolInOrd.recorrido();`
5. Reemplazo `arbol.recorrerPostOrden();` por `arbolPosOrd.recorrido();`

### En testSoloHijoDerecho
1. Reemplazo `ArbolBinario arbol = new ArbolBinario(10);` por:
   1. `ArbolBinario arbolPreOrd = new ArbolPreOrden(10);`
   2. `ArbolBinario arbolInOrd = new ArbolInOrden(10);`
   3. `ArbolBinario arbolPosOrd = new ArbolPostOrden(10);`
2. Reemplazo `arbol.setHijoDerecho(new ArbolBinario(15));` por:
   1. `arbolPreOrd.setHijoDerecho(new ArbolPreOrden(15));`
   2. `arbolInOrd.setHijoDerecho(new ArbolInOrden(15));`
   3. `arbolPosOrd.setHijoDerecho(new ArbolPostOrden(15));`
3. Reemplazo `arbol.recorrerPreOrden();` por `arbolPreOrd.recorrido();`
4. Reemplazo `arbol.recorrerInOrden();` por `arbolInOrd.recorrido();`
5. Reemplazo `arbol.recorrerPostOrden();` por `arbolPosOrd.recorrido();`

### En testArbolCompletoTresNodos
1. Reemplazo `ArbolBinario arbol = new ArbolBinario(10);` por:
   1. `ArbolBinario arbolPreOrd = new ArbolPreOrden(10);`
   2. `ArbolBinario arbolInOrd = new ArbolInOrden(10);`
   3. `ArbolBinario arbolPosOrd = new ArbolPostOrden(10);`
2. Reemplazo `arbol.setHijoIzquierdo(new ArbolBinario(5));` por:
   1. `arbolPreOrd.setHijoIzquierdo(new ArbolPreOrden(5));`
   2. `arbolInOrd.setHijoIzquierdo(new ArbolInOrden(5));`
   3. `arbolPosOrd.setHijoIzquierdo(new ArbolPostOrden(5));`
3. Reemplazo `arbol.setHijoDerecho(new ArbolBinario(15));` por:
   1. `arbolPreOrd.setHijoDerecho(new ArbolPreOrden(15));`
   2. `arbolInOrd.setHijoDerecho(new ArbolInOrden(15));`
   3. `arbolPosOrd.setHijoDerecho(new ArbolPostOrden(15));`
4. Reemplazo `arbol.recorrerPreOrden();` por `arbolPreOrd.recorrido();`
5. Reemplazo `arbol.recorrerInOrden();` por `arbolInOrd.recorrido();`
6. Reemplazo `arbol.recorrerPostOrden();` por `arbolPosOrd.recorrido();`

### En testArbolConVariosNiveles
1. Reemplazo `ArbolBinario arbol = new ArbolBinario(10);` por:
   1. `ArbolBinario arbolPreOrd = new ArbolPreOrden(10);`
   2. `ArbolBinario arbolInOrd = new ArbolInOrden(10);`
   3. `ArbolBinario arbolPosOrd = new ArbolPostOrden(10);`
3. Reemplazo `ArbolBinario n5 = new ArbolBinario(5);` por:
   1. `ArbolBinario n5PreOrd = new ArbolPreOrden(5);`
   2. `ArbolBinario n5InOrd = new ArbolInOrden(5);`
   3. `ArbolBinario n5PosOrd = new ArbolPostOrden(5);`
4. Reemplazo `ArbolBinario n15 = new ArbolBinario(15);` por:
    1. `ArbolBinario n15PreOrd = new ArbolPreOrden(15);`
    2. `ArbolBinario n15InOrd = new ArbolInOrden(15);`
    3. `ArbolBinario n15PosOrd = new ArbolPostOrden(15);`
5. Reemplazo `ArbolBinario n3 = new ArbolBinario(3);` por:
    1. `ArbolBinario n3PreOrd = new ArbolPreOrden(3);`
    2. `ArbolBinario n3InOrd = new ArbolInOrden(3);`
    3. `ArbolBinario n3PosOrd = new ArbolPostOrden(3);`
6. Reemplazo `ArbolBinario n7 = new ArbolBinario(7);` por:
   1. `ArbolBinario n7PreOrd = new ArbolPreOrden(7);`
   2. `ArbolBinario n7InOrd = new ArbolInOrden(7);`
   3. `ArbolBinario n7PosOrd = new ArbolPostOrden(7);`
7. Reemplazo `ArbolBinario n12 = new ArbolBinario(12);` por:
    1. `ArbolBinario n12PreOrd = new ArbolPreOrden(12);`
    2. `ArbolBinario n12InOrd = new ArbolInOrden(12);`
    3. `ArbolBinario n12PosOrd = new ArbolPostOrden(12);`
8. Reemplazo `ArbolBinario n18 = new ArbolBinario(18);` por:
    1. `ArbolBinario n18PreOrd = new ArbolPreOrden(18);`
    2. `ArbolBinario n18InOrd = new ArbolInOrden(18);`
    3. `ArbolBinario n18PosOrd = new ArbolPostOrden(18);`
9. Reemplazo `arbol.setHijoIzquierdo(n5);` por:
    1. `arbolPreOrd.setHijoIzquierdo(n5PreOrd);`
    2. `arbolInOrd.setHijoIzquierdo(n5InOrd);`
    3. `arbolPosOrd.setHijoIzquierdo(n5PosOrd);`
10. Reemplazo `arbol.setDerecha(n15);` por:
    1. `arbolPreOrd.setHijoDerecho(n15PreOrd);`
    2. `arbolInOrd.setHijoDerecho(n15InOrd);`
    3. `arbolPosOrd.setHijoDerecho(n15PosOrd);`
11. Reemplazo `n5.setHijoIzquierdo(n3);` por:
    1. `n5PreOrd.setHijoIzquierdo(n3PreOrd);`
    2. `n5InOrd.setHijoIzquierdo(n3InOrd);`
    3. `n5PosOrd.setHijoIzquierdo(n3PosOrd);`
12. Reemplazo `n5.setHijoDerecho(n7);` por:
    1. `n5PreOrd.setHijoDerecho(n7PreOrd);`
    2. `n5InOrd.setHijoDerecho(n7InOrd);`
    3. `n5PosOrd.setHijoDerecho(n7PosOrd);`
13. Reemplazo `n15.setHijoIzquierdo(n12);` por:
    1. `n15PreOrd.setHijoIzquierdo(n12PreOrd);`
    2. `n15InOrd.setHijoIzquierdo(n12InOrd);`
    3. `n15PosOrd.setHijoIzquierdo(n12PosOrd);`
14. Reemplazo `n15.setHijoDerecho(n18);` por:
    1. `n15PreOrd.setHijoDerecho(n18PreOrd);`
    2. `n15InOrd.setHijoDerecho(n18InOrd);`
    3. `n15PosOrd.setHijoDerecho(n18PosOrd);`
4. Reemplazo `arbol.recorrerPreOrden();` por `arbolPreOrd.recorrido();`
5. Reemplazo `arbol.recorrerInOrden();` por `arbolInOrd.recorrido();`
6. Reemplazo `arbol.recorrerPostOrden();` por `arbolPosOrd.recorrido();`
