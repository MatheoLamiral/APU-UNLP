# Ejercicio 2
## Inciso a
- Los lenguajes y sus versiones implementan diversas estrategias de inicialización:  
     1. Inicialización por defecto:  
         - Enteros se inicializan en 0. 
         - Caracteres en blanco. 
         - Funciones en VOID, etc. 
     2. Inicialización en la declaración:  
         - Ejemplos:  
         - C: int i = 0, j = 1. 
         - ADA: I, J INTEGER: = 0 (opcional). 
     3. Estrategia Ignorar el problema:  
         - Toma como valor inicial lo que hay en memoria (la cadena de bits asociados al área de almacenamiento).
         - Puede llevar a errores y requiere chequeos adicionales. 

## Inciso b

| Lenguaje | Inicialización por defecto | Inicialización por declaración | Ignorar el problema |
| --- | --- | --- | --- |
| Java | SI | SI | NO |
| C | SI (solo para variables globales y estáticas) | SI | SI |
| Python | NO | SI | NO |
| Ruby | NO | SI | NO |