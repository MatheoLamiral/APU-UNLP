# Ejercicio 5

## Asociación del else con el if correspondiente en C
Cada `else` se empareja con la instrucción `if` solitaria más próxima buscando de derecha a izquierda, cada else se empareja para cerrar el último if abierto
## Asociación del else con el if correspondiente en Delphi
Se comporta igual que C
## Asociación del else con el if correspondiente en ADA
Se comporta igual que C
## Asociación del else con el if correspondiente en Python
- Incorpora `elif` si hay más de 2 opciones y 
- mantiene el `else` que le corresponde al ultimo `elif`
- Ambos son opcionales y se corresponden por identanción
    ```Python
        if (hora >= 4 and hora <= 12):
            print 'Buenos dias'
        elif (hora > 12 and hora < 20):
            print 'Buenas tardes'
        else:
            print 'Buenas noches'
    ``` 
- elimina la ambiguedad y dá legibilidad
