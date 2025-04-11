- Se pueden declarar de 2 formas:  
    - `function nombre { block }`  
    - `nombre() { block }`  
- Con la sentencia `return`  se retorna un valor entre 0 y 255   
- El valor de retorno se puede evaluar mediante la variable `$?`
- Reciben argumentos en las variables `$1`, `$2`, etc.
- Las variables no inicializadas son reemplazadas por un valor nulo o 0, según el contexto de evaluación.
- Por defecto las variables son globales.
- Una variable local a una función se define con `local` 
    - `test() { local variable }`   
- Las variables de entorno son heredadas por los procesos hijos.
- Para exponer una variable global a los procesos hijos se usa el comando `export`:
-   ```bash
        export VARIABLE_GLOBAL="Mi var global"
        comando
        # comando verá entre sus variables de 
        # entorno a VARIABLE_GLOBAL
    ```
