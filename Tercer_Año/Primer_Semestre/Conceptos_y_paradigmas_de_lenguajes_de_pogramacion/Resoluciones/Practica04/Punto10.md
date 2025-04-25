# Ejercicio 10
1. C: `int c;`
- Alcance: Sí, todo el procedimiento. Declarada al inicio, es visible en todo el bloque del procedimiento (no hay procedimientos anidados en C).
- Tiempo de vida: Sí, toda la ejecución del procedimiento. Es automática: se crea al entrar y se destruye al salir.
2. Pascal: `var c : integer;`
- Alcance: Sí, todo el procedimiento. Declarada en var, es visible desde begin hasta end (sin procedimientos anidados).
- Tiempo de vida: Sí, toda la ejecución del procedimiento. Es automática: existe mientras el procedimiento se ejecuta.
3. Ada: `c : integer;`
- Alcance: Sí, todo el procedimiento (salvo ocultamiento). Declarada al inicio, es visible hasta el end, a menos que un bloque declare la oculte (no mencionado).
- Tiempo de vida: Sí, toda la ejecución del procedimiento. Es automática: se crea al entrar y se destruye al salir.

  