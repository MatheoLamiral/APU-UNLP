# Punto 3

## Cadena dinámica

- Principal imprime  
  4  
  false  
  1  
- Proc2 imprime  
  1  
- Puno imprime  
  1 4   
  4   
  4    
  4    

|**Principal**|
|------------------------------------|
|Pto retorno (*1)|
|EE()|
|ED()|
|i = ~~3~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~1~~ ~~2~~ ~~3~~ 4 |
|estado = ~~true~~ false|
|z = ~~0~~ 1 |
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
|a = 1|
|vec(1) = 4 |
|vec(2) = 4 |
|vec(3) = 4 |
|vec(4) = 4 |
|z = ~~1~~ 4|
|Valor retorno|

## Cadena estática

- Principal imprime  
  4  
  false  
  1  
- Proc2 imprime  
  1  
- Puno imprime   
  1 15      
  6  
  9   
  12  
  15    

|**Principal**|
|------------------------------------|
|Pto retorno (*1)|
|EE()|
|ED()|
|i = ~~3~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ ~~1~~ ~~2~~ ~~3~~ 4 |
|estado = ~~true~~ false|
|z = ~~0~~ ~~1~~ ~~2~~ ~~3~~ ~~4~~ 1 |
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
|a = 1|
|vec(1) = 6 |
|vec(2) = 9 |
|vec(3) = 12 |
|vec(4) = 15 |
|z = ~~1~~ ~~6~~ ~~9~~ ~~12~~ 15|
|Valor retorn ~~3~~ ~~6~~ ~~9~~ 12|
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
|------------------------------------|
|**f**|
|Pto retorno (*7)|
|EE(*1)|
|ED(*3)|
|Valor retorno|