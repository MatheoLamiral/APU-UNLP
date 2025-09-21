# Punto 3

## Inciso a 

- Principal imprime  
  3  
  false  
  1  
- Proc2 imprime  
  1  
- Puno imprime  
  1 9  
  5  
  7  
  9  

|**Principal**|
|------------------------------------|
|Pto retorno (*1)|
|EE()|
|ED()|
|k = ~~3~~ ~~1~~ ~~2~~ ~~3~~ ~~1~~ ~~2~~ 3 |
|est = ~~true~~ false|
|z = ~~0~~ ~~1~~ ~~2~~ ~~3~~ 1|
|Procedure Puno|
|Procedure Proc2|
|Function f|
|Valor retorno|
|------------------------------------|
|**Proc2**|
|Pto retorno (*2)|
|EE(*1)|
|ED(*1)|
|f = 1|
|Valor retorno|
|------------------------------------|
|**Puno**|
|Pto retorno (*3)|
|EE(*1)|
|ED(*2)|
|c = 1|
|vec(1) = 5 |
|vec(2) = 7 |
|vec(3) = 9 |
|z = ~~1~~ ~~5~~ ~~7~~ 9|
|Valor retorno ~~2~~ ~~4~~ 6|
|------------------------------------|
|**f**|
|Pto retorno (*4)|
|EE(*1)|
|ED(*3)|
|Valor retorno|
|------------------------------------|
|**f**|
|Pto retorno (*5)|
|EE(*1)|
|ED(*3)|
|Valor retorno|
|------------------------------------|
|**f**|
|Pto retorno (*6)|
|EE(*1)|
|ED(*3)|
|Valor retorno|

## Inciso b
- Punto de retorno
    - Cuando una rutina llama a otra y esta ultima termina, el punto de retorno es la dirección de memoria donde continúa la ejecución
- Link dinámico
    - Puntero a la dirección base del registro de activación de la rutina llamadora
- Link estático
    - Puntero a la dirección base del registro de activación de la rutina que estáticamente la contiene
- Variables
  - Se enumeran las variables que conforman la unidad y se van reemplazando los valores de acuerdo a la ejecución del programa
- Procesos y funciones
  - Se enumeran los identificadores de los procesos y funciones que contiene la unidad
- Valor de retorno
  - Los valores retornados por las funciones que desde esta unidad se llamen a ejecutar deberán ser escritos en esta dirección de memoria.