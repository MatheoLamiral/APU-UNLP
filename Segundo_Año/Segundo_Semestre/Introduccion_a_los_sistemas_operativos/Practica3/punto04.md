`$1`, `$2`, `$3` … representan los parámetros posicionales pasados al script, `$1` es el primer parámetro, `$2` el segundo y así sucesivamente. (`$0` contiene la invocación al script).
Variables especiales:
- `$#`: contiene el número de parámetros pasados al script
- `$*`: representa todos los parámetros pasados al script como una sola cadena
- `$?`: contiene el valor de retorno del último comando ejecutado
- `$HOME`: contiene la ruta del directorio home del usuario actual