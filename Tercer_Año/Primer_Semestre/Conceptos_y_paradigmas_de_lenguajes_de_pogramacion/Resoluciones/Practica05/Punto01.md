# Ejercicio 1

## Modelo de registro de activación
| Head (prog principal)     |
|---------------------------|
| Pto retorno               |
| EE (enlace estático)      |
| ED (enlace dinámico)      |
| Variables...              |
| ...                       |
| Parámetros ...            |
| ….                        |
| Procedimientos ….         |
| ….                        |
| Funciones ...             |
| ….                        |
| Valor de retorno          |

## Utilidad del registro de activación
- Sirve para gestionar las invocaciones de funciones en un programa, almacenando temporalmente información esencial como parámetros, variables locales y la dirección de retorno, lo que permite al sistema ejecutar funciones en el orden correto, soportar recursión, mantener los ámbitos aislados y regresar al punto correcto tras completar cada llamada, asegurando así el funcionamiento eficiente y ordenado del código.
## Partes de un registro de activación
### Head (programa principal)
- Es la parte inicial del registro de activación y generalmente contiene
  - current (dirección base del registro de activación de la unidad que se esté ejecutando actualmente)
  - free (próxima dirección libre en la pila)
### Punto de retorno
- Cuando una rutina llama a otra y esta ultima termina, el punto de retorno es la dirección de memoria donde continúa la ejecución.
### Enlace estático
- Puntero a la dirección base del registro de activación de la rutina que estáticamente la contiene (bloque donde está definido).
### Enlace dinámico
- Puntero a la dirección base del registro de activación de la rutina llamadora.
### Variables
- Se enumeran las variables que conforman la unidad y se van reemplazando los valores de acuerdo a la ejecución del programa.
### Parámetros
- Contiene los valores de los parámetros pasados al procedimiento o función en el momento de la llamada.
### Procedimientos y Funciones
- Se enumeran los identificadores de los procesos y funciones que contiene la unidad.
### Valor de retorno
- Los valores retornados por las funciones que desde esta unidad se llamen a ejecutar deberán ser escritos en esta dirección de memoria.